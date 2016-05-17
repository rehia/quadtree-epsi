/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadtree;

/**
 *
 * @author max
 */
public class Point {

    private int x;
    private int y;

    public Point() {
        this.x = 0;
        this.y = 0;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        boolean isEqual = false;

        if (o != null && o.getClass() == Point.class) {
            Point p = (Point) o;
            isEqual = this.getX() == p.getX() && this.getY() == p.getY();
        }

        return isEqual;
    }

    @Override
    public String toString() {
        return "[ x = " + this.getX() + " , y = " + this.getY() + " ]";
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }
}
