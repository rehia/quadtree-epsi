package test;

import core.Dot;
import core.Grid;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by J on 30/04/2016.
 */
public class GridTest {

    private Grid initGrid() {
        Grid grid = new Grid();
        grid.addDotWithinRange(new Dot());
        return grid;
    }

    @Test
    public void gridHasOneDot() {
        Grid grid = initGrid();
        assertEquals(1, grid.numberOfDots());
    }

    @Test
    public void gridCanAddDotsWithinRange() {
        Grid grid = initGrid();
        Dot dot = new Dot();
        assertEquals(true, grid.addDotWithinRange(dot));
    }

    @Test
    public void gridCannotHaveTwinDots() {
        Grid grid = initGrid();
        Dot dot = new Dot(grid.getListOfDots().get(0).getX(), grid.getListOfDots().get(0).getY());
        assertEquals(false, grid.doesNotOwn(dot));
    }

    @Test
    public void gridSplitAtFiveDots() {
        Grid grid = initGrid();
        grid.addDotWithinRange(new Dot());
        grid.addDotWithinRange(new Dot());
        grid.addDotWithinRange(new Dot());
        grid.addDotWithinRange(new Dot());
        assertEquals(4, grid.numberOfDots());

    }

}