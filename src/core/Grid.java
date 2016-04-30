package core;

import java.util.ArrayList;

/**
 * Created by J on 30/04/2016.
 */
public class Grid {
    private ArrayList<Dot> _listOfDots;
    private int _range;

    public Grid() {
        this(100);
    }

    public Grid(int range) {
        _range = range;
        _listOfDots = new ArrayList<>();
        addDotWithinRange(new Dot());
    }

    public int numberOfDots() {
        return _listOfDots.size();
    }

    public boolean addDotWithinRange(Dot dot) {
        return dot.isIn(_range) ? _listOfDots.add(dot) : dot.isIn(_range);
    }
}
