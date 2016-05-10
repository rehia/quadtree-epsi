package core;

import java.util.Random;

/**
 * Created by J on 30/04/2016.
 */
public class Dot {

    private int _x, _y;

    public Dot() {
        this(randInt(0, 100), randInt(0, 100));
    }

    public boolean equals(Dot d) {
        return d.getX() == this._x && d.getY() == this._y;
    }

    public String affiche() {
        return "dot : " + this._x + "," + this._y;
    }

    public Dot(int x, int y) {
        _x = x;
        _y = y;
    }

    private static int randInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    boolean isNotTwin(Dot dot) {
        return dot.getX() != _x || dot.getY() != _y;
    }

    public int getX() {
        return _x;
    }

    public int getY() {
        return _y;
    }

    // override method from arraylist to search by value and not by reference
    @Override
    public boolean equals(Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass()) {
            result = false;
        } else {
            Dot d = (Dot) object;
            if (this.getX() == d.getX() && this.getY() == d.getY()) {
                result = true;
            }
        }
        return result;
    }
}
