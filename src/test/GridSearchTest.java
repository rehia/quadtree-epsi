
import core.Dot;
import core.Grid;
import core.Repere;
import java.awt.Point;
import java.util.ArrayList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by J on 30/04/2016.
 */
public class GridSearchTest {

    private Grid initGrid() {
        Grid grid = new Grid();
        grid.addDot(new Dot(0, 1));
        return grid;
    }

    private Grid initGridWith5Dots() {
        Grid grid = new Grid();
        grid.addDot(new Dot(56, 56));
        grid.addDot(new Dot(0, 1));
        grid.addDot(new Dot(0, 2));
        grid.addDot(new Dot(0, 3));
        grid.addDot(new Dot(0, 4));
        return grid;
    }

    @Test
    public void returnEmptyListwhenNoDot() {
        Grid grid = new Grid();
        Dot dot = new Dot();
        assertEquals(new ArrayList<Dot>(), grid.findNeighbourhood(dot));
    }

    @Test
    public void returnAllOtherDotWhenGridNotSplitted() {
        Grid grid = new Grid();
        Dot dotCentral = new Dot(0, 1);
        Dot Neighbour1 = new Dot(1, 1);
        Dot Neighbour2 = new Dot(1, 2);
        grid.addDot(dotCentral);
        grid.addDot(Neighbour1);
        grid.addDot(Neighbour2);
        ArrayList dotsExpected = new ArrayList<Dot>();
        dotsExpected.add(Neighbour1);
        dotsExpected.add(Neighbour2);

        assertEquals(true, grid.findNeighbourhood(dotCentral).containsAll(dotsExpected));
    }

    @Test
    public void returnEmptyListwhenDotNotNotExists() {
        Grid grid = new Grid();
        Dot dot = new Dot(1, 2);
        grid.addDot(dot);
        assertEquals(new ArrayList<Dot>(), grid.findNeighbourhood(new Dot(1, 1)));
    }

    @Test
    public void returnTheDotsInTheSameSubGrid() {
        Grid grid = initGridWith5Dots();
        ArrayList dotsExpected = new ArrayList<Dot>();
        dotsExpected.add(new Dot(0, 1));
        dotsExpected.add(new Dot(0, 2));
        dotsExpected.add(new Dot(0, 3));

        assertEquals(dotsExpected, grid.findNeighbourhood(new Dot(0, 4)));
    }

}
