package pro.vhsl.dev;

import static org.junit.Assert.*;

import org.junit.Test;

public class NodeTest {

	@Test
	public void shouldCreateANodeWithOriginPointAndSize() {
		double x = 0;
		double y = 0;
		double width = 100;
		double height = 100;
		
		Node node = new Node(x, y, width, height);
		
		double[] expecteds = { x, y, width, height };
		double[] actuals = { node.getX(), node.getY(), node.getWidth(), node.getHeight() };
		
		assertArrayEquals(expecteds, actuals, 0);
	}
	
	@Test
	public void shouldAddPointsIntoANodePointsList() {
		Node node = new Node(0, 0, 100, 100);
		
		node.push(new XY(10, 10));
		
		int expected = 1;
		int actual = node.getPoints().size();
		
		assertEquals(expected, actual);
	}

}
