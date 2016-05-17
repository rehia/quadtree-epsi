/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import quadtreexp.Arbre;
import quadtreexp.Point;
import quadtreexp.Rectangle;

/**
 *
 * @author Maxime
 */
public class ArbreTest {
    private Arbre arbre;
    private Rectangle rectangle;
    private Rectangle rectangleEnfant1;
    private Rectangle rectangleEnfant2;
    private Rectangle rectangleEnfant3;    
    private Point point;
    private Point point2;
    
    @Before
    public void setUp() {
        arbre = new Arbre(100,5);
        point = new Point(50,50);
        point = new Point(40,60);
        rectangle = new Rectangle(point, 30, 30);
        rectangleEnfant1 = new Rectangle(point, 4, 12);
        rectangleEnfant2 = new Rectangle(point2, 10, 20);
    }
    
    @Test
    public void miseAJourProfondeur(){
        arbre.setProfondeurMaximale(0);
        arbre.miseAJourProfondeur(1);
        assertEquals(1, arbre.getProfondeurMaximale(), 0);
    }
    
    @Test 
    public void miseAJourProfondeurEchec(){
        arbre.setProfondeurMaximale(3);
        arbre.miseAJourProfondeur(2);
        assertEquals(3, arbre.getProfondeurMaximale(), 0);   
    }

    @Test 
    public void liaisonSucces(){
        ArrayList<Rectangle> rectangles = new ArrayList<>();
        rectangles.add(rectangleEnfant1);
        rectangles.add(rectangleEnfant2);
        arbre.liaison(rectangles, rectangle);
        assertEquals(2, rectangle.getRectangles().size(), 0);
    }
    
    /*@Test
    public void ventilationSucces(){
        rectangle.ajouter(point);
        rectangle.ajouter(point2);
        arbre.ventilation(rectangle);
    }*/

}
