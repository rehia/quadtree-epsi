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
    
    public String affiche(){
        return "dot : " +this._x + ","+this._y;
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
}
