package core;

import java.util.ArrayList;

/**
 * Created by J on 30/04/2016.
 */
public class Grid {
    private ArrayList<Dot> _listOfDots;

    public Grid() {
        _listOfDots = new ArrayList<>();
        _listOfDots.add(new Dot());
    }

    public int numberOfDots() {
        return _listOfDots.size();
    }
}
