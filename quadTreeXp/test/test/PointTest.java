/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import quadtreexp.Point;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Maxime
 */
public class PointTest {
    
    private Point point;
    
    @Before
    public void setUp() {
        point = new Point(5,10);
    }
    
    @Test
    public void PointTestX(){
        assertEquals(5, point.getX(), 0);
    }
    
    @Test public void PointTestY(){
        assertEquals(10, point.getY(), 0);
    }
    
    @Test public void translateMarcheSurX(){
        point.translate(5, 10);
        assertEquals(10, point.getX(), 0);
    }
    
    @Test public void translateMarcheSurY(){
        point.translate(5, 10);
        assertEquals(20, point.getY(), 0);
    }
}
