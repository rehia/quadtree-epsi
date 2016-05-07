import core.Dot;
import core.Grid;
import core.Repere;
import java.awt.Point;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by J on 30/04/2016.
 */
public class GridTest {

    private Grid initGrid() {
        Grid grid = new Grid();
        grid.addDot(new Dot());
        return grid;
    }
    private Grid initGridWith5Dots(){
        Grid grid = new Grid();
        grid.addDot(new Dot(56,56));
        grid.addDot(new Dot(0,1));
        grid.addDot(new Dot(0,2));
        grid.addDot(new Dot(0,3));
        grid.addDot(new Dot(0,4));
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
        Grid grid = initGridWith5Dots();
        assertEquals(true, grid.numberOfDots()<5);
    }
    
    @Test
    public void gridReturnFalseWhenDotNotInRange() {
        Grid grid = new Grid(25,new Repere(0,0));
        Dot dot = new Dot(26,26);
        assertEquals(false, grid.addDot(dot));
    }
    
    @Test
    public void SubgridIsHalfRange() {
        Grid grid = initGridWith5Dots();
        assertEquals(50, grid.getUpperLeft().getRange(),0);
    }
    
    
    @Test
    public void gridVentileWhen5Dots(){
        Grid grid = initGridWith5Dots();
        assertEquals(4, grid.getUpperLeft().numberOfDots());
    }
    
    
    
    @Test
    public void MasterGridStillVentileAfter5Dots(){
        Grid grid = initGridWith5Dots();
        grid.addDot(new Dot(54,2));
        assertEquals(1, grid.getUpperRight().numberOfDots());
    }
    


}