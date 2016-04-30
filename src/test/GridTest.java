package test;

import core.Dot;
import core.Grid;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by J on 30/04/2016.
 */
public class GridTest {

    @Test
    public void gridHasOneDot() {
        Grid grid = new Grid();
        assertEquals(grid.numberOfDots(), 1);
    }

    @Test
    public void gridCanAddDotsWithinRange() {
        Grid grid = new Grid();
        Dot dot = new Dot();
        assertEquals(grid.addDotWithinRange(dot), true);
    }

}