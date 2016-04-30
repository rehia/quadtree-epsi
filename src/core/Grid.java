package core;

import java.util.ArrayList;

/**
 * Created by J on 30/04/2016.
 */
public class Grid {
    private ArrayList<Dot> _listOfDots;
    private int _range;
    private Grid _upperLeft;
    private Grid _upperRight;
    private Grid _lowerLeft;
    private Grid _lowerRight;

    public Grid() {
        this(100);
    }

    private Grid(int range) {
        _range = range;
        _listOfDots = new ArrayList<>();
    }

    public int numberOfDots() {
        return _listOfDots.size();
    }

    public boolean addDotWithinRange(Dot dot) {
        if (numberOfDots() < 4) {
            return dot.isIn(_range) ? _listOfDots.add(dot) : dot.isIn(_range);
        } else {
            return split();
        }
    }

    private boolean split() {
        _upperLeft = new Grid();
        _upperRight = new Grid();
        _lowerLeft = new Grid();
        _lowerRight = new Grid();
        return false;
    }

    public boolean doesNotOwn(Dot dot) {
        for (Dot dotInList : _listOfDots) {
            return dotInList.isNotTwin(dot);
        }
        return true;
    }

    public ArrayList<Dot> getListOfDots() {
        return _listOfDots;
    }
}
