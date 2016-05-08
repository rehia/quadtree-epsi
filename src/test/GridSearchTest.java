
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
        grid.addDot(new Dot(0,1));
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
        assertEquals( new ArrayList<Dot>(), grid.findNeighbourhood());
    }

   
    
    

}
