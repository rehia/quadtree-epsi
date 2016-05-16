package pro.vhsl.dev;

import static org.junit.Assert.*;

import org.junit.Test;

public class QuadTreeTest {

	@Test
	public void shouldCreateAQuadTreeWithARootNode() {
		double width = 100;
		double height = 100;
		QuadTree quadTree = new QuadTree(width, height);
		Node root = quadTree.getRoot();
		
		double[] expecteds = { 0, 0, width, height };
		double[] actuals = { root.getX(), root.getY(), root.getWidth(), root.getHeight() };
		
		assertArrayEquals(expecteds, actuals, 0);
	}
	
	@Test
	public void shouldAddAPointIntoQuadTree() {
		QuadTree quadTree = new QuadTree(100, 100);

		quadTree.push(new XY(20, 20));
		quadTree.push(new XY(200, 200));
		
		int expected = 1;
		int actual = quadTree.getPoints().size();
		
		assertEquals(expected, actual);
	}

}
