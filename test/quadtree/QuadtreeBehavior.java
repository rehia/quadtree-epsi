package quadtree;

import static org.junit.Assert.*;

import java.util.List;

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
	public void shouldPushPointToQuadtreeWhenOnBoundsForRootQuadtree() {
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
	public void shouldNotSplitQuadtreeEachTimeQuadtreeReachesMaxCapacity() {
		Point southWestPoint = new Point(10, 10);
		Point southEastPoint = new Point(60, 10);
		Point northEastPoint = new Point(60, 80);
		Point northEastPoint2 = new Point(90, 60);
		Point northWestPoint = new Point(10, 90);
		Point northWestPoint2 = new Point(45, 75);
		
		quadtree.push(southWestPoint);
		quadtree.push(southEastPoint);
		quadtree.push(northEastPoint2);
		quadtree.push(northEastPoint);
		quadtree.push(northWestPoint);
		quadtree.push(northWestPoint2);

		assertTrue(quadtree.child(Cardinals.NW).hasPoint(northWestPoint));
		assertTrue(quadtree.child(Cardinals.NW).hasPoint(northWestPoint2));
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
	
	@Test
	public void shouldNotPushPointWhenAlreadyExistsInChildren() {
		quadtree.push(new Point(5, 5));
		quadtree.push(new Point(10, 10));
		quadtree.push(new Point(15, 15));
		quadtree.push(new Point(20, 20)); // Root + SW max capacity reached
		quadtree.push(new Point(60, 60)); // split Root
		quadtree.push(new Point(20, 20));
		
		assertFalse(quadtree.child(Cardinals.SW).hasChildren());
	}
	
	@Test
	public void shouldRemovePointsFromParentQuadtreeWhenPointsAreSpreadIntoChildren() {
		Point southWestPoint = new Point(10, 10);
		
		quadtree.push(southWestPoint);
		quadtree.push(new Point(60, 10));
		quadtree.push(new Point(90, 60));
		quadtree.push(new Point(60, 80));
		quadtree.push(new Point(10, 90));
		
		assertFalse(quadtree.hasPoint(southWestPoint));
	}
	
	@Test
	public void shouldHaveADepthOf0WhenPointDoesNotExistInQuadtree() {
		Point point = new Point(10, 10);
		
		assertEquals(0, quadtree.depthOf(point));
	}
	
	@Test
	public void shouldHaveAHigherDepthWhenPointsAreSpread() {
		Point southWestPoint = new Point(10, 10);
		
		quadtree.push(southWestPoint);
		quadtree.push(new Point(60, 10));
		quadtree.push(new Point(90, 60));
		quadtree.push(new Point(60, 80));
		quadtree.push(new Point(10, 90));
		
		assertEquals(2, quadtree.depthOf(southWestPoint));
	}
	
	@Test
	public void shouldNotSpreadPointsThatAreOnBounds() {
		quadtree.push(new Point(10, 10));
		quadtree.push(new Point(60, 10));
		quadtree.push(new Point(90, 60));
		quadtree.push(new Point(60, 80));
		
		Point center = new Point(50, 50);
		quadtree.push(center);

		Point origin = new Point(0, 0);
		quadtree.push(origin);
		
		assertTrue(quadtree.hasPoint(center));
		assertTrue(quadtree.hasPoint(origin));
	}
	
	@Test
	public void shouldBeNeighborsWhenInSameQuadtree() {
		Point point = new Point(10, 10);
		Point neighbor = new Point(60, 90);
		
		quadtree.push(point);
		quadtree.push(neighbor);
		
		List<Point> neighbors = quadtree.neighborsOf(point);
		
		assertEquals(1, neighbors.size());
		assertEquals(neighbor, neighbors.get(0));
	}
	
	@Test
	public void shouldNotBeNeighborsWhenNotInSameQuadtree() {
		Point southWestPoint = new Point(10, 10);
		Point southEastPoint = new Point(60, 10);
		Point northEastPoint = new Point(60, 80);
		Point northEastPoint2 = new Point(90, 60);
		Point northWestPoint = new Point(10, 90);
		
		quadtree.push(southWestPoint);
		quadtree.push(southEastPoint);
		quadtree.push(northEastPoint);
		quadtree.push(northEastPoint2);
		quadtree.push(northWestPoint);
		
		List<Point> northEastNeighbors = quadtree.neighborsOf(northEastPoint);

		assertEquals(1, northEastNeighbors.size());
	}
	
	@Test
	public void shouldBeNeighborsWhenInChildrenQuadtree() {
		quadtree.push(new Point(10, 10));
		quadtree.push(new Point(60, 10));
		quadtree.push(new Point(90, 60));
		quadtree.push(new Point(60, 80));
		
		Point center = new Point(50, 50);
		quadtree.push(center);
		
		List<Point> neighbors = quadtree.neighborsOf(center);
		
		assertEquals(4, neighbors.size());
	}
	
	@Test
	public void shouldFormatQuadtreeAsAStringWhenNoPoints() {
		String[] quadtreeLines = quadtree.toString().split("\n");
		
		assertEquals("Root (0) - []", quadtreeLines[0]);
	}
	
	@Test
	public void shouldFormatQuadtreeAsAStringWhen1Point() {
		quadtree.push(new Point(10, 15));
		
		String[] quadtreeLines = quadtree.toString().split("\n");
		
		assertEquals("Root (1) - [{10,15}]", quadtreeLines[0]);
	}
	
	@Test
	public void shouldFormatQuadtreeAsAStringWhenSplit() {
		quadtree.push(new Point(10, 10));
		quadtree.push(new Point(60, 10));
		quadtree.push(new Point(60, 80));
		quadtree.push(new Point(90, 60));
		quadtree.push(new Point(10, 90));
		
		String[] quadtreeLines = quadtree.toString().split("\n");
		
		assertEquals("Root (0) - []", quadtreeLines[0]);
		assertEquals("\tSW (1) - [{10,10}]", quadtreeLines[1]);
		assertEquals("\tSE (1) - [{60,10}]", quadtreeLines[2]);
		assertEquals("\tNE (2) - [{60,80}, {90,60}]", quadtreeLines[3]);
		assertEquals("\tNW (1) - [{10,90}]", quadtreeLines[4]);
	}
}
