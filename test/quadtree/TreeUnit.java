/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadtree;

import java.lang.reflect.Method;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import quadtree.xp.*;

/**
 *
 * @author Hippolyte
 */
public class TreeUnit {
    
    QuadTree tree;
    
    public TreeUnit() 
    {
        tree = new QuadTree(50, 50);
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
    public void coordinateAreOK() 
    {
        Point point = new Point("Test", 10, 5);
        assertEquals(10, point.getX());
        assertEquals(5, point.getY());
    }
    
    @Test
    public void ShouldFindThePoint() 
    {
        tree = new QuadTree(50, 50);
        
        Point pointToTest = new Point("Test", 10, 5);
        tree.Push(pointToTest);
        
        assertTrue(tree.getRootNode().hasPoint(pointToTest));
    }
    
    @Test
    public void shouldNotSplit() 
    {
        tree = new QuadTree(50, 50);
        
        tree.Push(new Point("Test1", 10, 5));
        tree.Push(new Point("Test2", 20, 15));
        tree.Push(new Point("Test3", 30, 20));
        tree.Push(new Point("Test4", 40, 35));
        
        assertEquals(4, tree.getRootNode().countPoint());
    }
    
    @Test
    public void shouldSplit() 
    {
        tree = new QuadTree(50, 50);
        
        tree.Push(new Point("Test1", 10, 5));
        tree.Push(new Point("Test2", 15, 15));
        tree.Push(new Point("Test3", 10, 20));
        tree.Push(new Point("Test4", 30, 35));
        tree.Push(new Point("Test5", 40, 20));
        
        assertEquals(0, tree.getRootNode().countPoint());
    }
    
    @Test
    public void shouldStayOnParent() 
    {
        tree = new QuadTree(50, 50);
        
        tree.Push(new Point("Test1", 10, 5));
        tree.Push(new Point("Test2", 15, 15));
        tree.Push(new Point("Test3", 10, 20));
        tree.Push(new Point("Test4", 30, 35));
        tree.Push(new Point("Test5", 40, 20));
        
        tree.Push(new Point("OnLimit", 25, 25));
        
        assertEquals(1, tree.getRootNode().countPoint());
    }
    
    @Test
    public void pointIsOnLimit() 
    {
        tree = new QuadTree(50, 50);
        tree.getRootNode().CreateSides();
        
        assertTrue(tree.getRootNode().isOnLimit(new Point("OnLimit", 25, 10)));
    }
    
    @Test
   public void pointIsNotInbounds()
   {
       tree = new QuadTree(50, 50);
       
       assertTrue(tree.getRootNode().IsNotInBounds(new Point("NotInBound", 10, 60)));
   }
   
   @Test
   public void nodeHaveMoreThanThreePoints()
   {
       tree = new QuadTree(50, 50);
       tree.Push(new Point("Test1", 10, 5));
       tree.Push(new Point("Test2", 15, 5));
       tree.Push(new Point("Test3", 20, 5));
       tree.Push(new Point("Test4", 40, 5));
       
       assertTrue(tree.getRootNode().IsMoreThanThree());
   }
   
   @Test
   public void shouldCreateNewLeaves()
   {
       tree = new QuadTree(50, 50);
       tree.getRootNode().CreateSides();
       
       assertEquals(4, tree.getRootNode().getChildNodes().size()); 
   }
   
   @Test
   public void shouldFindNodeFromPoint()
   {
       tree = new QuadTree(50, 50);
       tree.Push(new Point("Test1", 10, 5));
       tree.Push(new Point("Test2", 30, 5));
       tree.Push(new Point("Test3", 15, 30));
       tree.Push(new Point("Test4", 35, 30));
       tree.Push(new Point("Test5", 40, 45));
       
       Point pointToFind = new Point("pointToFind", 5, 5);
       
       Node nodeWichContainPointToFind = tree.getRootNode().getChildNodes().get(Node.Side.TL);
       nodeWichContainPointToFind.getPoints().add(pointToFind);
       
       assertEquals(nodeWichContainPointToFind, tree.getRootNode().findNodeByPoint(pointToFind));
   }
}
