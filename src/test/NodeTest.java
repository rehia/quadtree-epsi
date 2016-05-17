package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import quad.Node;
import quad.Point;
public class NodeTest {

	private Node nodeParent;

	public void setUpWith3Points(){
		this.setUpWithOutPoint();
		
		Point point1 = new Point(5,10);
		Point point2 = new Point(0,10);
		Point point3 = new Point(15,49);
		
		this.nodeParent.addPoint(point1);
		this.nodeParent.addPoint(point2);
		this.nodeParent.addPoint(point3);
	}
	
	public void setUpWithOutPoint(){
		this.nodeParent = new Node(0,100,0,100,1);
		Node nw = new Node(0,50,0,50,2);
		this.nodeParent.addNode(nw);
		Node ne = new Node(50,100,0,50,2);
		this.nodeParent.addNode(ne);
		Node sw = new Node(0,50,50,100,2);
		this.nodeParent.addNode(sw);
		Node se = new Node(50,100,50,100,2);
		this.nodeParent.addNode(se);
	}
	
	@Test
	public void subdivisionForFirstTimeTest() {
		Node nodeParent = new Node(0,100,0,100,1);
		
		nodeParent.subdivision();
		
		assertEquals(4, nodeParent.getChildrens().size());		
	}
	
	@Test
	public void tryToAddPointToAChildThantCanInsertInAChildTest(){
		this.setUpWith3Points();
		
		boolean result = this.nodeParent.tryToGivePointToAChildren(new Point(5,10));
		
		assertEquals(true, result);
	}
	
	@Test
	public void tryToAddPointToAChildThatCantInsertInChildTest(){
		this.setUpWith3Points();
		
		boolean result = this.nodeParent.tryToGivePointToAChildren(new Point(0,0));
		
		assertEquals(false, result);
	}
	
	@Test
	public void ventilationLet1PointInParentTest(){
		this.setUpWith3Points();
		
		this.nodeParent.ventilation();
		
		assertEquals(1, this.nodeParent.getPoints().size());
	}
	
	@Test
	public void ventilationAdd2PointToFirstChildTest(){
		this.setUpWith3Points();
		
		this.nodeParent.ventilation();
		
		assertEquals(2, this.nodeParent.getChildrens().get(0).getPoints().size());
	}
	
	@Test
	public void addPointToParentTest(){
		this.setUpWithOutPoint();
		
		this.nodeParent.addPoint(new Point(0, 0));
		
		assertEquals(1, this.nodeParent.getPoints().size());
	}
	
	@Test
	public void addPointToFirstChildTest(){
		this.setUpWithOutPoint();
		
		this.nodeParent.addPoint(new Point(10, 20));
		
		assertEquals(0, this.nodeParent.getPoints().size());
	}
	
	@Test
	public void add5PointToParentNodeTest(){
		this.setUpWith3Points();
		
		this.nodeParent.addPoint(new Point(10, 20));
		
		this.nodeParent.addPoint(new Point(5, 20));
		
		assertNotEquals(5, this.nodeParent.getPoints().size());
	}

}
