/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadtree;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author CAR
 */
public class QuadtreeTest {
    
    public QuadtreeTest() {
    }

    @Test
    public void addPointToUnchildedNodeWithLessThan4Points(){
        Node node = new Node(new Point(0,0), 3, 3);
        node.getPoints().add(new Point(1,1));
        node.getPoints().add(new Point(1,2));
        node.getPoints().add(new Point(2,1));
        Quadtree tree = new Quadtree();
        tree.setGrid(node);
        tree.addPoint(new Point(2,2));
        assertTrue(node.getNodes().isEmpty());
        assertEquals(node.getPoints().size(), 4);
    }
    
    @Test
    public void addMoreThan4Points(){
        Node node = new Node(new Point(0,0), 4, 4);
        node.getPoints().add(new Point(1,1));
        node.getPoints().add(new Point(1,3));
        node.getPoints().add(new Point(3,1));
        node.getPoints().add(new Point(3,2));
        Quadtree tree = new Quadtree();
        tree.setGrid(node);
        tree.addPoint(new Point(2,3));
        assertEquals(node.getNodes().size(), 4);
        assertEquals(node.getPoints().size(), 2);
        assertEquals(node.getNodes().get(0).getPoints().size(), 1);
        assertEquals(node.getNodes().get(1).getPoints().size(), 1);
        assertEquals(node.getNodes().get(2).getPoints().size(), 1);
        assertEquals(node.getNodes().get(3).getPoints().size(), 0);
    }
    
    @Test
    public void getPointClosest(){
        Node node = new Node(new Point(0,0), 6, 6);
        Point point1 = new Point(1,1);
        Point point2 = new Point(1,2);
        Point point3 = new Point(4,1);
        Point point4 = new Point(5,2);
        Point point5 = new Point(2,2);
        Quadtree tree = new Quadtree();
        tree.setGrid(node);
        tree.addPoint(point1);
        tree.addPoint(point2);
        tree.addPoint(point3);
        tree.addPoint(point4);
        tree.addPoint(point5);
        ArrayList<Point> closests = tree.getClosests(1,1);
        assertEquals(closests.size(), 2);
        assertTrue(closests.contains(point2));
        assertTrue(closests.contains(point5));
        assertFalse(closests.contains(point1));
    }
    
    @Test
    public void getPointDeepness(){
        Node node = new Node(new Point(0,0), 6, 6);
        Point point1 = new Point(1,1);
        Point point2 = new Point(1,2);
        Point point3 = new Point(4,1);
        Point point4 = new Point(5,2);
        Point point5 = new Point(2,2);
        Quadtree tree = new Quadtree();
        tree.setGrid(node);
        tree.addPoint(point1);
        tree.addPoint(point2);
        tree.addPoint(point3);
        tree.addPoint(point4);
        tree.addPoint(point5);
        assertEquals(tree.getDeepness(1,2), 1);
    }
}
