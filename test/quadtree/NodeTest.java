/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadtree;

import org.junit.Test;
import static org.junit.Assert.*;

public class NodeTest {
    
    public NodeTest() {
    }
    
    @Test
    public void shouldContain() {
        Point point = new Point(2,2);
        Node node1 = new Node(new Point(0,0), 3, 3);
        Node node2 = new Node(new Point(0,0), 2, 3);
        assertTrue(node1.contains(point));
        assertTrue(node2.contains(point));
    }
    
    @Test
    public void shouldNotContain() {
        Point point = new Point(2,2);
        Node node = new Node(new Point(3,3), 3, 3);
        assertFalse(node.contains(point));
    }
    
    @Test
    public void shouldCountPointsCorrectly(){
        Node node = new Node(new Point(0,0), 3, 3);
        Point intruder = new Point(2,2);
        node.getPoints().add(new Point(1,1));
        node.getPoints().add(new Point(1,2));
        node.getPoints().add(new Point(2,1));
        node.getPoints().add(intruder);
        int numberOfPointsExceptIntruder = node.getPointsBut(intruder).size();
        assertEquals(numberOfPointsExceptIntruder, 3);
    }
    
}
