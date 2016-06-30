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
    private Point point, point1, point2;
    int largeur, hauteur;
    
    @Before
    public void setUp() {
        point = new Point(50, 20);
        point1 = new Point(55,25);
        point2 = new Point(53,27);

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
    
    @Test
    public void ajoutDePointDansUnRectangle(){
        rectangle.ajouter(point1);
        rectangle.ajouter(point2);
        assertEquals(2, rectangle.getPoints().size(), 0);
    }
    
    @Test
    public void ajoutDeRectangleDansUnRectangle(){
        Rectangle rectangle1 = new Rectangle(point1,5,5);
        rectangle.ajouter(rectangle1);
        assertEquals(1, rectangle.getRectangles().size(), 0);
    }
    
    @Test
    public void leRectangleContientUnAutreRectangle(){
        Rectangle autreRectangle = new Rectangle(point1, 5, 7);
        rectangle.ajouter(autreRectangle);
        assertEquals(false, rectangle.neContientPasDeRectangles());
    }
    
    @Test
    public void nombreDePoints(){
        rectangle.ajouter(point1);
        rectangle.ajouter(point2);
        assertEquals(2, rectangle.nombreDePoints(), 0);
    }
    

}
