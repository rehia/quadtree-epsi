/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Tuan Nguyen <tuan.nguyen at tuannguyen.epsi.fr>
 */
public class NodeTest {
    
    public NodeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void aNodeWithoutChildenIsALeaf(){
        //G
        Node node = new Node(0,0,100,100);
        
        //W
        boolean isLeaf = node.isLeaf();
        
        //T
        assertEquals(true,isLeaf);
    }
    
    @Test
    public void aNodeWithLeavesIsNotALeaf(){        
        //G
        Node node = new Node(0,0,100,100);
        node.createLeaves();
        
        //W
        boolean isLeaf = node.isLeaf();
        
        //T
        assertEquals(false,isLeaf);
    }
    
    @Test
    public void shouldPush1Point(){                
        //G
        Node node = new Node(0,0,100,100);
        Point point = new Point(0,0);
        
        //W
        node.push(point);
        int numberOfPointsInNode = node.countPoints();
        
        //T
        assertEquals(1,numberOfPointsInNode);
    }
    
    @Test
    public void shouldPush4Points(){                
        //G
        Node node = new Node(0,0,100,100);
        Point point = new Point(0,0);
        
        //W
        node.push(point);
        node.push(point);
        node.push(point);
        node.push(point);
        int numberOfPointsInNode = node.countPoints();
        
        //T
        assertEquals(4,numberOfPointsInNode);
    }
    
    @Test
    public void shouldntPushWhenPointIsOutOfBounds(){               
        //G
        Node node = new Node(0,0,100,100);
        Point point = new Point(200,200);
        
        //W
        node.push(point);
        int numberOfPointsInNode = node.countPoints();
        
        //T
        assertEquals(0,numberOfPointsInNode);
    }
}
