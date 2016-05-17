/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadtree;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author max
 */
public class Node {

    private static final int MAX_POINTS_PER_NODE = 4;
    private Rectangle zone;
    private int depth;
    private Node north_west;
    private Node north_east;
    private Node south_west;
    private Node south_east;
    private NodeState state;
    private List<Point> points;

    public Node() {
        this.state = NodeState.EMPTY;
        this.points = new ArrayList<>();
    }

    public Node(Rectangle zone, int depth) {
        this.state = NodeState.LEAF;
        this.zone = zone;
        this.depth = depth;
        this.north_east = new Node();
        this.north_west = new Node();
        this.south_east = new Node();
        this.south_west = new Node();
        this.points = new ArrayList<>();
    }

    public boolean isLeaf() {
        return this.state == NodeState.LEAF;
    }

    public void addPoint(Point p) {
        try {
            validatePoint(p);

            if (!this.pointExists(p)) {
                if (this.state == NodeState.NODE) {
                    if (!this.insertIntoQuadrant(p)) {
                        this.points.add(p);
                    }
                }
                else if (this.points.size() < MAX_POINTS_PER_NODE) {
                    this.points.add(p);
                }
                else {
                    this.split();
                    this.addPoint(p);
                }
            }
        } catch (PointOutOfBoundsException ex) {
            System.out.println(ex);
        }

    }

    private void validatePoint(Point p) throws PointOutOfBoundsException {
        if (p.getX() < this.zone.x || p.getX() > this.zone.x + this.zone.width || p.getY() < this.zone.y || p.getY() > this.zone.y + this.zone.height) {
            throw new PointOutOfBoundsException("The point is out of bounds : " + p);
        }
    }

    public boolean pointExists(Point p) {
        boolean existsInChildren = this.state == NodeState.EMPTY
                ? false
                : this.isLeaf()
                        ? false
                        : this.north_west.pointExists(p) || this.north_east.pointExists(p) || this.south_east.pointExists(p) || this.south_west.pointExists(p);

        return this.points.contains(p) || existsInChildren;
    }

    @Override
    public String toString() {
        String str = "State : " + this.state;
        str += "\nDepth : " + this.depth;
        str += "\nZone : " + this.zone.toString();
        str += "\nPoints : " + this.points.toString();
        if (!this.isLeaf()) {
            str += "\nNorth West : " + this.north_west.toString();
            str += "\nNorth East: " + this.north_east.toString();
            str += "\nSouth West : " + this.south_west.toString();
            str += "\nSouth East : " + this.south_east.toString();

        }

        return str;
    }

    private void split() {
        int halfHeight = this.zone.height / 2;
        int halfWidth = this.zone.width / 2;
        Rectangle NW = new Rectangle(this.zone.x, this.zone.y, halfWidth, halfHeight);
        Rectangle NE = new Rectangle(this.zone.x + halfWidth, this.zone.y, halfWidth, halfHeight);
        Rectangle SW = new Rectangle(this.zone.x, this.zone.y + halfHeight, halfWidth, halfHeight);
        Rectangle SE = new Rectangle(this.zone.x + halfWidth, this.zone.y + halfHeight, halfWidth, halfHeight);

        this.north_west = new Node(NW, this.depth + 1);
        this.north_east = new Node(NE, this.depth + 1);
        this.south_west = new Node(SW, this.depth + 1);
        this.south_east = new Node(SE, this.depth + 1);
        this.state = NodeState.NODE;
        List<Point> pointsToRemove = new ArrayList<>();
        for (Point p : this.points) {
            if (this.insertIntoQuadrant(p)) {
                pointsToRemove.add(p);
            }
        }

        for (Point p : pointsToRemove) {
            this.points.remove(p);
        }
    }

    private boolean insertIntoQuadrant(Point p) {
        boolean isInserted = false;
        int halfHeight = this.zone.height / 2;
        int halfWidth = this.zone.width / 2;

        final boolean isWithinWestSide = p.getX() < this.zone.x + halfWidth;
        final boolean isWithinNorthSide = p.getY() < this.zone.y + halfHeight;
        final boolean isWithinSouthSide = p.getY() > this.zone.y + halfHeight;

        if (isWithinWestSide) {
            if (isWithinNorthSide) {
                this.north_west.addPoint(p);
                isInserted = true;
            }
            else if (isWithinSouthSide) {
                this.south_west.addPoint(p);
                isInserted = true;
            }
        }
        else if (isWithinNorthSide) {
            this.north_east.addPoint(p);
            isInserted = true;
        }
        else if (isWithinSouthSide) {
            this.south_east.addPoint(p);
            isInserted = true;
        }

        return isInserted;
    }

    public int numberOfPoints() {
        int nb = this.points.size();

        if (!this.isLeaf()) {
            nb += this.north_west.numberOfPoints()
                    + this.north_east.numberOfPoints()
                    + this.south_west.numberOfPoints()
                    + this.south_east.numberOfPoints();
        }
        return nb;
    }

    public int getDepthOfPoint(Point p) {
        if (this.points.contains(p)) {
            return this.depth;
        }
        else if (this.north_west.pointExists(p)) {
            return this.north_west.getDepthOfPoint(p);
        }
        else if (this.north_east.pointExists(p)) {
            return this.north_east.getDepthOfPoint(p);
        }
        else if (this.south_west.pointExists(p)) {
            return this.south_west.getDepthOfPoint(p);
        }
        else if (this.south_east.pointExists(p)) {
            return this.south_east.getDepthOfPoint(p);
        }
        else {
            return 0;
        }
    }

    public List<Point> getNearestPoints(Point p) {
        List<Point> nearestPoints = new ArrayList<>();

        if (this.points.contains(p)) {
            nearestPoints = new ArrayList<>(this.points);
            nearestPoints.remove(p);
        }
        else if (this.north_west.pointExists(p)) {
            nearestPoints = this.north_west.getNearestPoints(p);
        }
        else if (this.north_east.pointExists(p)) {
            nearestPoints = this.north_east.getNearestPoints(p);
        }
        else if (this.south_west.pointExists(p)) {
            nearestPoints = this.south_west.getNearestPoints(p);
        }
        else if (this.south_east.pointExists(p)) {
            nearestPoints = this.south_east.getNearestPoints(p);
        }
        return nearestPoints;
    }
}
