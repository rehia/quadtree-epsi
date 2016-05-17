/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadtree;

import java.awt.Rectangle;
import java.util.List;
import java.util.Random;

/**
 *
 * @author max
 */
public class Grid {

    private static final int DEFAULT_SIZE = 100;
    private final int size;
    private Node root;

    public Grid() {
        this(DEFAULT_SIZE);
    }

    public Grid(int size) {
        Rectangle zone = new Rectangle(size, size);
        this.root = new Node(zone, 1);
        this.size = size;
    }

    public void populate(int numberOfPoints) {
        for (int i = 0; i < numberOfPoints; i++) {
            Point p = generateRandomPoint();
            while (this.root.pointExists(p)) {
                p = generateRandomPoint();
            }
            this.addPoint(p);
        }

    }

    private Point generateRandomPoint() {
        Random randomGenerator = new Random();
        int x = randomGenerator.nextInt(this.getSize());
        int y = randomGenerator.nextInt(this.getSize());
        return new Point(x, y);
    }

    @Override
    public String toString() {
        return this.root.toString();
    }

    public void addPoint(Point p) {
        this.root.addPoint(p);
    }

    public int getNumberOfPoints() {
        return this.root.numberOfPoints();
    }

    public int getDepthOfPoint(Point p) {
        return this.root.getDepthOfPoint(p);
    }

    public List<Point> getNearestPoints(Point p) {
        return this.root.getNearestPoints(p);
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    public boolean pointExist(Point p) {
        return this.root.pointExists(p);
    }
}
