/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;
import quadtree.QuadTreePoint;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author vsouche & asoyer
 */
public class QuadTreePointTest {
    
    @Test
    public void shouldReturnY() {
        QuadTreePoint point = new QuadTreePoint(5,7);
        
        assertEquals(point.getY(), 7);
    }
    
    @Test
    public void shouldReturnX() {
        QuadTreePoint y = new QuadTreePoint(5,7);
        
        assertEquals(y.getX(), 5);
    }
}
