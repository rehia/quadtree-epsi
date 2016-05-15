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
    public void CountPoint(){
    
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
    public void ExistPoint(){
    Point point = new Point(10,8);
    
    assertEquals(point.getX(),10);
    assertEquals(point.getY(),8);
    
    }
    
    
    
}
