 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadtree.epsi;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author mathieu
 */
public class Node {

    private ArrayList<Node> sons;
    private ArrayList<Coordinates> points;
    private Coordinates origin;
    private int lenght;
    private int width;

    Node(Coordinates center, int lenght, int width) {
        this.origin = center;
        this.lenght = lenght;
        this.width = width;
        points = new ArrayList<>();
        sons = new ArrayList<>();

    }

    public ArrayList<Node> getSons() {
        return sons;
    }

    public Node getSon(int id) {
        return sons.get(id);
    }

    public void createSons() {
        int newL = lenght / 2;
        int newW = width / 2;
        int newCL = lenght / 2;
        int newCW = width / 2;
        if (lenght % newL != 0) {
            newCL++;
            newL++;
        } else {
            newCL--;
        }
        if (width % newW != 0) {
            newCW++;
            newW++;
        } else {
            newCW--;
        }
        sons.add(new Node(origin, newL, newW));
        sons.add(new Node(new Coordinates(getOriginX() + newCL, getOriginY()), lenght / 2, newW));
        sons.add(new Node(new Coordinates(getOriginX(), getOriginY() + newCW), newL, width / 2));
        sons.add(new Node(new Coordinates(getOriginX() + newCL, getOriginY() + newCW), lenght / 2, width / 2));
    }

    public int getWidthInSons(int idSon) {
        return this.sons.get(idSon).getWidth();
    }

    public Coordinates getOrigin() {
        return origin;
    }

    public Coordinates getOriginInSons(int idSon) {
        return sons.get(idSon).getOrigin();
    }

    public int getWidth() {
        return width;
    }

    public int getLenghtInSons(int idSon) {
        return this.sons.get(idSon).getLenght();
    }

    public int getOriginX() {
        return origin.getX();
    }

    public int getOriginY() {
        return origin.getY();
    }

    public ArrayList<Coordinates> getPoints() {
        return points;
    }

    public int getLenght() {
        return lenght;
    }

    void addPoint(Coordinates point) {
        if (points.size() < 4) {
            this.addPointInPoints(point);
        } else {
            createSons();
            for (Iterator<Coordinates> it = points.iterator(); it.hasNext();) {
                Coordinates pointer = it.next();

                if (points.contains(pointer) && !this.isOverlaping(pointer)) {
                    fillSons(pointer);
                    it.remove();
                }
            }

            if (!this.isOverlaping(point) || (this.isOverlaping(point) && points.size() == 4)) {
                fillSons(point);
            } else if (this.isOverlaping(point) && isInside(point) && points.size() < 4) {
                addPointInPoints(point);
            }
        }
    }

    public void fillSons(Coordinates point) {

        for (Node son : sons) {
            if (son.isInside(point)) {
                son.addPoint(point);
                break;

            }
        }
    }

    public Coordinates getPoint(int i) {
        return points.get(i);
    }

    public int getPointXInPoint(int i) {
        return points.get(i).getX();
    }

    public int getPointYInPoint(int i) {
        return points.get(i).getY();
    }

    private void addPointInPoints(Coordinates point) {
        points.add(point);
    }

    private boolean isInside(Coordinates point) {
        return (((point.getX() >= this.getOriginX()) && (point.getX() <= this.getOriginX() + lenght)) && ((point.getY() >= this.getOriginY()) && (point.getY() <= this.getOriginY() + width)));
    }

    private boolean isOverlaping(Coordinates pointer) {
        return (this.getLenght() % 2 == 0 && (pointer.getX() == this.getLenght() / 2 - 1 + this.getOriginX()) || (pointer.getY() == this.getWidth() / 2 - 1 + this.getOriginY()));
    }

    public int getPointDepth(Coordinates point) {
        int depth = 1;
        if (points.contains(point)) {
            return depth;
        } else {
            
            for (Iterator<Node> it = sons.iterator(); it.hasNext();) {
                Node son = it.next();
                int sonDepth = son.getPointDepth(point);
                if (son.isInside(point)&&sonDepth!=-1) {
                    depth+= sonDepth;
                    return depth;
                }
            }
        return-1;
        }
    }
}
