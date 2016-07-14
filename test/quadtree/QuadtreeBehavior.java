package quadtree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
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
	
	@Test
	public void shouldSplitQuadtreeWhenReachesMaxCapacity() {
		Point southWestPoint = new Point(10, 10);
		Point southEastPoint = new Point(60, 10);
		Point southEastPoint2 = new Point(90, 30);
		Point northEastPoint = new Point(60, 80);
		Point northWestPoint = new Point(10, 90);
		
		quadtree.push(southWestPoint);
		quadtree.push(southEastPoint);
		quadtree.push(southEastPoint2);
		quadtree.push(northEastPoint);
		quadtree.push(northWestPoint);
		
		assertTrue(quadtree.hasChildren());
	}
	
	@Test
	public void shouldSpreadQuadtreePointsWhenReachesMaxCapacity() {
		Point southWestPoint = new Point(10, 10);
		Point southEastPoint = new Point(60, 10);
		Point northEastPoint = new Point(60, 80);
		Point northEastPoint2 = new Point(90, 60);
		Point northWestPoint = new Point(10, 90);
		
		quadtree.push(southWestPoint);
		quadtree.push(southEastPoint);
		quadtree.push(northEastPoint2);
		quadtree.push(northEastPoint);
		quadtree.push(northWestPoint);
		
		assertTrue(quadtree.child(Cardinals.SW).hasPoint(southWestPoint));
		assertTrue(quadtree.child(Cardinals.NE).hasPoint(northEastPoint));
		assertTrue(quadtree.child(Cardinals.NE).hasPoint(northEastPoint2));
	}
	
	@Test
	public void shouldNotPushPointWhenAlreadyExists() {
		quadtree.push(new Point(10, 20));
		quadtree.push(new Point(10, 20));
		quadtree.push(new Point(10, 20));
		quadtree.push(new Point(10, 20));
		quadtree.push(new Point(10, 20));
		
		assertFalse(quadtree.hasChildren());
	}
}
