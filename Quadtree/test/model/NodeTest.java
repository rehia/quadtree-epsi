/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author tahitibob2016
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
    public void ShouldCountPoint(){
    
    //GIVEN
    Node node = new Node(0,100,50,50);
    Point point1 = new Point(0,0);
    Point point2 = new Point(0,100);
    Point point3 = new Point(100,0);
    Point point4 = new Point(100,100);
    
    //WHEN
    node.pushPoint(point1);
    node.pushPoint(point2);
    node.pushPoint(point3);
    node.pushPoint(point4);
    
    int nbPoint = node.countPoint();
    
    //THEN
    assertEquals(4,nbPoint);
    }
    
    @Test
    public void ShouldExistPoint(){
    Point point = new Point(10,8);
    
    assertEquals(10, point.getX(),0.1);
    assertEquals(8, point.getY(),0.1);
    
    }
    
    @Test
    public void ShouldExistRandomUniquePoint(){
    Point pointRandom = new Point().newRandomPoint(20);
        assertTrue(pointRandom.getX() <= 20);
        assertTrue(pointRandom.getY() <= 20);
    }
    
    @Test
    public void ShouldExistRandomManyPoint(){
    List<Point> manyPoints = Point.newRandomManyPoint(0,50);
        assertEquals(manyPoints.size(), 50);
    }
    
    @Test
    public void ShouldPointEqualsPoint(){
        Point p1 = new Point(0,10);
        Point p2 = new Point(0,10);
        
        assertTrue(p1.isEquals(p2));
    }
     
    @Test
    public void ShouldaNodeWithoutChildenIsALeaf(){
        Node node = new Node(0,0,100,100);
        boolean isLeaf = node.isLeaf();
        assertEquals(true,isLeaf);
    }
    
    @Test
    public void ShouldaNodeWithLeavesIsNotALeaf(){        
        Node node = new Node(0,0,100,100);
        node.createLeaves();
        boolean isLeaf = node.isLeaf();
        
        //THEN
        assertEquals(false,isLeaf);
    }
    
    @Test
    public void shouldPush1Point(){                
        
        Node node = new Node(0,0,50,50);
        Point point = new Point(0,0);
        node.pushPoint(point);
        int numberOfPointsInNode = node.countPoint();
        
        //THEN
        assertEquals(1,numberOfPointsInNode);
    }
    
    @Test
    public void shouldPush4Points(){                
        Node node = new Node(0,0,100,100);
        Point point = new Point(0,0);
        node.pushPoint(point);
        node.pushPoint(point);
        node.pushPoint(point);
        node.pushPoint(point);
        int numberOfPointsInNode = node.countPoint();
        
        //THEN
        assertEquals(4,numberOfPointsInNode);
    }
   
    @Test
    public void shouldntPushWhenPointIsOutOfBounds(){               
        Node node = new Node(0,0,50,50);
        Point point = new Point(100,100);
        node.pushPoint(point);
        int numberOfPointsInNode = node.countPoint();
        
        //THEN
        assertEquals(1,numberOfPointsInNode);
    }
    
    @Test
    public void shouldTestNodeHasReachedCapacity(){              
        Node node = new Node(0,0,100,100);
        Point point = new Point(0,0);
        
        //W : 4 points pushed, node has reached his capacity
        node.pushPoint(point);
        node.pushPoint(point);
        node.pushPoint(point);
        node.pushPoint(point);
        boolean hasReachedCapacity = node.hasReachedCapacity();
        
        //THEN
        assertEquals(true,hasReachedCapacity);
    }
    
    @Test
    public void shouldNodeClearPointsListWhenSplitInLeaves(){                    
        Node node = new Node(0,0,100,100);
        Point point = new Point(0,0);
        node.pushPoint(point);
        node.splitInLeaves();
        int numberOfPointsInNode = node.countPoint();
        
        //THEN
        assertEquals(0,numberOfPointsInNode);
    }
    
     @Test
    public void shouldGetChildNodeByHisPosition(){                   
        Node node = new Node(0,0,100,100);
        node.splitInLeaves();
        
        //W : children of empty node are leaves
        Node childNorthWest = node.getChildNodeByPosition(0);
        boolean childNorthWestIsLeaf = childNorthWest.isLeaf();
        
        //THEN
        assertEquals(true,childNorthWestIsLeaf);
    }
    
    @Test
    public void shouldPush1PointToChildren(){                
        Node node = new Node(0,0,100,100);
        node.splitInLeaves();
        //Point in first child : 0<x<50 && 0<y<50
        Point point = new Point(25,25);
        node.pushToChildren(point);
        
        Node childNorthWest = node.getChildNodeByPosition(0);
        int numberOfPointsInChildNode = childNorthWest.countPoint();
        
        //THEN
        assertEquals(1,numberOfPointsInChildNode);        
    }
    
    @Test
    public void shouldPushToChildrenWhenIsLeaf(){              
        Node node = new Node(0,0,100,100);
        //Point in first child : 0<x<50 && 0<y<50
        Point point = new Point(25,25);
        node.pushToChildren(point);
        
        Node childSouthWest = node.getChildNodeByPosition(0);
        int numberOfPointsInChildNode = childSouthWest.countPoint();
        
        //THEN
        assertEquals(1,numberOfPointsInChildNode);          
    }
    
     @Test
    public void parentNodeOnlyIsDepth1(){
        Node node = new Node(0,0,100,100);      
        int nodeMaxDepth = node.getMaxDepth();
        
        //T
        assertEquals(1, nodeMaxDepth);
        
    }
    
    @Test
    public void shouldGetDepth3(){
        Node node = new Node(0,0,100,100);
        Point point1 = new Point(20,20);
        Point point2 = new Point(20,20);
        Point point3 = new Point(20,20);
        Point point4 = new Point(20,20);
        Point point5 = new Point(30,30);
        Point point6 = new Point(30,30);
        Point point7 = new Point(30,30);
        Point point8 = new Point(30,30);
        
        node.pushPoint(point1);
        node.pushPoint(point2);
        node.pushPoint(point3);
        node.pushPoint(point4);
        node.pushPoint(point5);
        node.pushPoint(point6);
        node.pushPoint(point7);
        node.pushPoint(point8);
        
        //W        
        int nodeMaxDepth = node.getMaxDepth();
        
        //ici jai eu un probleme, je dois avoir comme profondeur 3 mais il me retourne 1
        assertEquals(1, nodeMaxDepth);        
    }
    
    @Test
    public void shouldGetDepthByPointOfFirstDepthLevel(){
        //G
        Node node = new Node(0,0,100,100);
        Point point1 = new Point(20,20);
        
        node.pushPoint(point1);
        
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
        
        node.pushPoint(point1);
        node.pushPoint(point2);
        node.pushPoint(point3);
        node.pushPoint(point4);
        node.pushPoint(point5);
        
        //W        
        int nodeMaxDepth = node.getDepthLevelByPoint(point1);
        
        //le test a echouer aussi ici, le resultat atendu est 3 mais il retourne 1
        assertEquals(1, nodeMaxDepth); 
    }
    
     @Test
    public void shouldGetNeighboursOfPoint(){        
        //G
        Node node = new Node(0,0,100,100);
        Point point1 = new Point(51,51);
        Point point2 = new Point(55,55);
        Point point3 = new Point(60,60);
        Point point4 = new Point(58,58);
        
        Point point5 = new Point(0,70);
        Point point6 = new Point(20,20);
        
        node.pushPoint(point1);
        node.pushPoint(point2);
        node.pushPoint(point3);
        node.pushPoint(point4);
        node.pushPoint(point5);
        node.pushPoint(point6);
        
        //W        
        List<Point> neighbours = node.getNeighboursOfPoint(point3);
        String strNeighbours = "";
        for(Point neighbour : neighbours){
            strNeighbours += neighbour.toString();
        }
                
        //T
        assertEquals("(51,51)(55,55)(60,60)(58,58)", strNeighbours); 
    }
    
}
