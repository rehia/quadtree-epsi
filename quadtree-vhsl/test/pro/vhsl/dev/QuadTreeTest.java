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
	
	@Test
	public void shouldPrintQuadTree() {
		QuadTree quadTree = new QuadTree(100, 100);

		quadTree.push(new XY(10, 10));
		quadTree.push(new XY(60, 20));
		quadTree.push(new XY(80, 80));
		quadTree.push(new XY(20, 40));
		quadTree.push(new XY(45, 50));
		String expected = "QuadTree containing 5 point(s) :" + "\r\n" + 
			"1-Root [0.00 - 0.00](1) (45.0, 50.0)" + "\r\n" + 
			"\t" + "2-NW [0.00 - 0.00](2) (10.0, 10.0), (20.0, 40.0)" + "\r\n" + 
			"\t" + "2-NE [50.00 - 0.00](1) (60.0, 20.0)" + "\r\n" + 
			"\t" + "2-SE [0.00 - 50.00](0) " + "\r\n" + 
			"\t" + "2-SW [50.00 - 50.00](1) (80.0, 80.0)]";
		String actual = quadTree.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnDepthOfNodeContainingRequestedPoint() {
		QuadTree quadTree = new QuadTree(100, 100);

		quadTree.push(new XY(10, 10));
		quadTree.push(new XY(60, 20));
		quadTree.push(new XY(80, 80));
		quadTree.push(new XY(20, 40));
		quadTree.push(new XY(45, 50));
		
		XY point = new XY(60, 20);
		
		int expected = 2;
		int actual = quadTree.getDepthOfPoint(point);
		
		assertEquals(expected, actual);
	}

}
