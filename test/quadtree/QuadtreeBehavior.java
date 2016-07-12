package quadtree;

import static org.junit.Assert.*;

import org.junit.Test;

public class QuadtreeBehavior {

	@Test
	public void shouldPushPointToQuadtreeWhenInBounds() {
		Quadtree quadtree = new Quadtree(100, 100);
		Point point = new Point(10, 20);
		
		quadtree.push(point);
		
		assertTrue(quadtree.hasPoint(point));
	}

}
