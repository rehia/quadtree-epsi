/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadtree;

import static org.junit.Assert.*;
import org.junit.*;

public class PointTest {
    
    public PointTest(){
    }
    
    @Test
    public void shouldMovePoint(){
        Point point = new Point(1,2);
        point.translate(2, 3);
        assertEquals(point.getX(), 3);
        assertEquals(point.getY(), 5);
    }
}
