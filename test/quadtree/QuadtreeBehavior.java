package quadtree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QuadtreeBehavior {
	
	private Quadtree quadtree;

	@Before
	public void before() {
		quadtree = new Quadtree(100, 100);		
	}

	@Test
	public void shouldPushPointToQuadtreeWhenInBounds() {
		Point point = new Point(10, 20);
		
		quadtree.push(point);
		
		assertTrue(quadtree.hasPoint(point));
	}

	@Test
	public void shouldNotPushPointToQuadtreeWhenOutOfBounds() {
		Point point = new Point(1000, 20);
		
		quadtree.push(point);
		
		assertFalse(quadtree.hasPoint(point));
	}

	@Test
	public void shouldPushPointToQuadtreeWhenOnBounds() {
		Point northEastPoint = new Point(100, 100);
		Point southWestPoint = new Point(0, 0);
		
		quadtree.push(northEastPoint);
		quadtree.push(southWestPoint);
		
		assertTrue(quadtree.hasPoint(northEastPoint));
		assertTrue(quadtree.hasPoint(southWestPoint));
	}
	
	@Test
	public void shouldHaveADepthOf1When1PointInQuadtree() {
		Point point = new Point(10, 20);
		
		quadtree.push(point);
		
		assertEquals(1, quadtree.depthOf(point));
	}
}
