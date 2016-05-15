/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import quadtreexp.Point;
import quadtreexp.Rectangle;

/**
 *
 * @author Maxime
 */
public class RectangleTest {
    private Rectangle rectangle;
    private Point point;
    int largeur, hauteur;
    
    @Before
    public void setUp() {
        point = new Point(50, 20);
        rectangle = new Rectangle(point, 10, 10);
    }
    
    @Test
    public void recupererCoordonneeXDeOrigine(){
        int origineX = rectangle.pointOrigine("X");
        assertEquals(50, origineX, 0);
    }
    
    @Test
    public void recupererCoordonneeYDeOrigine(){
        int origineY = rectangle.pointOrigine("Y");
        assertEquals(20, origineY, 0);
    }
    
    @Test
    public void leRectangleContientCePoint(){
        assertEquals(true, rectangle.contient(point));
    }
    
   

}
