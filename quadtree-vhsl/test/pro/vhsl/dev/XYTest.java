package pro.vhsl.dev;

import static org.junit.Assert.*;

import org.junit.Test;

public class XYTest {

	@Test
	public void shouldCreateAPointWithXYCoordinates() {
		double x = 0;
		double y = 0;
		XY point = new XY(x, y);
		
		double[] expecteds = { x, y };
		double[] actuals = { point.getX(), point.getY() };
		
		assertArrayEquals(expecteds, actuals, 0);
	}

}
