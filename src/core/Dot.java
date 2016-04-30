package core;

import java.util.Random;

/**
 * Created by J on 30/04/2016.
 */
public class Dot {

    private int _x, _y;

    public Dot() {
        _x = randInt(0, 100);
        _y = randInt(0, 100);
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public boolean isIn(int range) {
        return _x <= range && _y <= range;
    }
}
