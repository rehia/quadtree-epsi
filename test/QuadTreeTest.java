/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;
import quadtree.QuadTreePoint;
import quadtree.QuadTree;
import utils.Constants;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author vsouche & asoyer
 */
public class QuadTreeTest {
    
    @Test
    public void shouldBeInNWZone() {
        
        QuadTree quadtree = new QuadTree(0,100,0,100);
        QuadTreePoint point = new QuadTreePoint(24, 68);
        
        String zone = quadtree.currentLocationPoint(point);
        
        assertEquals(zone, "NW");
    }
    
    @Test
    public void shouldBeInNEZone() {
        
        QuadTree quadtree = new QuadTree(0,100,0,100);
        QuadTreePoint point = new QuadTreePoint(55, 68);
        
        String zone = quadtree.currentLocationPoint(point);
        
        assertEquals(zone, "NE");
    }

    @Test
    public void shouldBeInSWZone() {
        
        QuadTree quadtree = new QuadTree(0,100,0,100);
        QuadTreePoint point = new QuadTreePoint(28, 44);
        
        String zone = quadtree.currentLocationPoint(point);
        
        assertEquals(zone, "SW");
    }
    
    @Test
    public void shouldBeInSEZone() {
        
        QuadTree quadtree = new QuadTree(0,100,0,100);
        QuadTreePoint point = new QuadTreePoint(56, 12);
        
        String zone = quadtree.currentLocationPoint(point);
        
        assertEquals(zone, "SE");
    }
    
    @Test
    public void shouldNeverGetAllPointsHigherThanMax() {
        
        QuadTree qt = new QuadTree(0,100,0,100);
        
        qt.generateBasedQuadTree(50);
        
        qt.ventile();
        qt.ventileAllSubQuadTree();
        
        assertTrue(qt.getAllPoints().size() <= Constants.MAX_POINT);
    }
    
    @Test
    public void shouldVentilleQuadtree() {
        QuadTree quadtree = new QuadTree(0,100,0,100);
        
        QuadTreePoint point1 = new QuadTreePoint(6, 20);
        QuadTreePoint point2 = new QuadTreePoint(5, 10);
        QuadTreePoint point3 = new QuadTreePoint(42, 25);
        QuadTreePoint point4 = new QuadTreePoint(3, 8);
        QuadTreePoint point5 = new QuadTreePoint(14, 32);
        
        //assertTrue(quadtree.ventileAllSubQuadTree());
    }
   
}
