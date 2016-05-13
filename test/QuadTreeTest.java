/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import quadtree.Point;
import quadtree.QuadTree;

/**
 *
 * @author vsouche & asoyer
 */
public class QuadTreeTest {
    
    @Test
    public void ShouldBeInNWZone() {
        
        QuadTree quadtree = new QuadTree(0,100,0,100);
        Point point = new Point(24, 68);
        
        String zone = quadtree.currentLocationPoint(point);
        
        assertEquals(zone, "NW");
    }
    
    @Test
    public void ShouldBeInNEZone() {
        
        QuadTree quadtree = new QuadTree(0,100,0,100);
        Point point = new Point(55, 68);
        
        String zone = quadtree.currentLocationPoint(point);
        
        assertEquals(zone, "NE");
    }

    @Test
    public void ShouldBeInSWZone() {
        
        QuadTree quadtree = new QuadTree(0,100,0,100);
        Point point = new Point(28, 44);
        
        String zone = quadtree.currentLocationPoint(point);
        
        assertEquals(zone, "SW");
    }
    
    @Test
    public void ShouldBeInSEZone() {
        
        QuadTree quadtree = new QuadTree(0,100,0,100);
        Point point = new Point(56, 12);
        
        String zone = quadtree.currentLocationPoint(point);
        
        assertEquals(zone, "SE");
    }
    
    @Test
    public void ShouldVentilleQuadtree() {
        QuadTree quadtree = new QuadTree(0,100,0,100);
        Point point1 = new Point(6, 20);
        Point point2 = new Point(5, 10);
        Point point3 = new Point(42, 25);
        Point point4 = new Point(3, 8);
        Point point5 = new Point(14, 32);
        
        assertTrue(quadtree.ventileAllSubQuadTree());
    }
   
}
