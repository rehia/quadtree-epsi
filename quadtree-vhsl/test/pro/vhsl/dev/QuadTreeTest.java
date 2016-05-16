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

}
