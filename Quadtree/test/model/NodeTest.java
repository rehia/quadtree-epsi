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
    
    @Test
    public void shouldTestNodeHasReachedCapacity(){              
        //G
        Node node = new Node(0,0,100,100);
        Point point = new Point(0,0);
        
        //W : 4 points pushed, node has reached his capacity
        node.push(point);
        node.push(point);
        node.push(point);
        node.push(point);
        boolean hasReachedCapacity = node.hasReachedCapacity();
        
        //T
        assertEquals(true,hasReachedCapacity);
    }
    
    @Test
    public void shouldNodeClearPointsListWhenSplitInLeaves(){                    
        //G
        Node node = new Node(0,0,100,100);
        Point point = new Point(0,0);
        node.push(point);
        
        //W 
        node.splitInLeaves();
        int numberOfPointsInNode = node.countPoints();
        
        //T
        assertEquals(0,numberOfPointsInNode);
    }
    
    @Test
    public void shouldGetChildNodeByHisPosition(){                   
        //G
        Node node = new Node(0,0,100,100);
        node.splitInLeaves();
        
        //W : children of empty node are leaves
        Node childNorthWest = node.getChildNodeByPosition(0);
        boolean childNorthWestIsLeaf = childNorthWest.isLeaf();
        
        //T
        assertEquals(true,childNorthWestIsLeaf);
    }
    
    @Test
    public void shouldPush1PointToChildren(){                
        //G
        Node node = new Node(0,0,100,100);
        node.splitInLeaves();
        //Point in first child : 0<x<50 && 0<y<50
        Point point = new Point(25,25);
        node.pushToChildren(point);
        
        //W
        Node childNorthWest = node.getChildNodeByPosition(0);
        int numberOfPointsInChildNode = childNorthWest.countPoints();
        
        //T
        assertEquals(1,numberOfPointsInChildNode);        
    }
    
    @Test
    public void shouldPushToChildrenWhenIsLeaf(){              
        //G
        Node node = new Node(0,0,100,100);
        //Point in first child : 0<x<50 && 0<y<50
        Point point = new Point(25,25);
        node.pushToChildren(point);
        
        //W
        Node childNorthWest = node.getChildNodeByPosition(0);
        int numberOfPointsInChildNode = childNorthWest.countPoints();
        
        //T
        assertEquals(1,numberOfPointsInChildNode);          
    }
    
    @Test
    public void shouldPushToChildrenWhenHasReachedCapacity(){             
        //G
        Node node = new Node(0,0,100,100);
        //1 point stay in node and 4 points in North West Child Node
        Point point1 = new Point(10,10);
        Point point2 = new Point(20,20);
        Point point3 = new Point(30,30);
        Point point4 = new Point(40,40);
        Point point5 = new Point(50,50);
        node.push(point1);
        node.push(point2);
        node.push(point3);
        node.push(point4);
        node.push(point5);
        
        //W
        Node childNorthWest = node.getChildNodeByPosition(0);
        int numberOfPointsInChildNode = childNorthWest.countPoints();
        
        //T
        assertEquals(4,numberOfPointsInChildNode);  
    }
    
    @Test
    public void parentNodeOnlyIsDepth1(){
        //G
        Node node = new Node(0,0,100,100);
        
        //W        
        int nodeMaxDepth = node.getMaxDepth();
        
        //T
        assertEquals(1, nodeMaxDepth);
        
    }
    
    @Test
    public void shouldGetDepth3(){
        //G
        Node node = new Node(0,0,100,100);
        Point point1 = new Point(20,20);
        Point point2 = new Point(20,20);
        Point point3 = new Point(20,20);
        Point point4 = new Point(20,20);
        Point point5 = new Point(30,30);
        Point point6 = new Point(30,30);
        Point point7 = new Point(30,30);
        Point point8 = new Point(30,30);
        
        node.push(point1);
        node.push(point2);
        node.push(point3);
        node.push(point4);
        node.push(point5);
        node.push(point6);
        node.push(point7);
        node.push(point8);
        
        //W        
        int nodeMaxDepth = node.getMaxDepth();
        
        //T
        assertEquals(3, nodeMaxDepth);        
    }
    
    @Test
    public void shouldGetDepthByPointOfFirstDepthLevel(){
        //G
        Node node = new Node(0,0,100,100);
        Point point1 = new Point(20,20);
        
        node.push(point1);
        
        //W        
        int nodeMaxDepth = node.getDepthLevelByPoint(point1);
        
        //T
        assertEquals(1, nodeMaxDepth);        
    }
    
    @Test
    public void shouldGetDepthByPointOfThirdDepthLevel(){        
        //G
        Node node = new Node(0,0,100,100);
        Point point1 = new Point(5,5);
        Point point2 = new Point(10,10);
        Point point3 = new Point(15,15);
        Point point4 = new Point(20,20);
        
        Point point5 = new Point(30,30);
        
        node.push(point1);
        node.push(point2);
        node.push(point3);
        node.push(point4);
        node.push(point5);
        
        //W        
        int nodeMaxDepth = node.getDepthLevelByPoint(point1);
        
        //T
        assertEquals(3, nodeMaxDepth); 
    }
    
    @Test
    public void shouldGetDepthByPointOf2ndDepthLevel(){        
        //G
        Node node = new Node(0,0,100,100);
        Point point1 = new Point(51,51);
        Point point2 = new Point(55,55);
        Point point3 = new Point(60,60);
        Point point4 = new Point(58,58);
        
        Point point5 = new Point(70,70);
        Point point6 = new Point(20,20);
        
        node.push(point1);
        node.push(point2);
        node.push(point3);
        node.push(point4);
        node.push(point5);
        node.push(point6);
        
        //W        
        int nodeMaxDepth = node.getDepthLevelByPoint(point6);
        
        //T
        assertEquals(2, nodeMaxDepth); 
    }
    
}
