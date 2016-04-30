package test;

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

}