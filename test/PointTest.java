/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import quadtree.Point;

/**
 *
 * @author vsouche & asoyer
 */
public class PointTest {
    
    @Test
    public void shouldReturnY() {
        Point point = new Point(5,7);
        
        assertEquals(point.getY(), 7);
    }
    
    @Test
    public void shouldReturnX() {
        Point y = new Point(5,7);
        
        assertEquals(y.getX(), 5);
    }
}
