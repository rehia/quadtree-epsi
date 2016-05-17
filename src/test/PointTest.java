package test;

import static org.junit.Assert.*;

import org.junit.Test;

import quad.Point;

public class PointTest {

	@Test
	public void initialisePointTest() {
		Point point = new Point(5,10);
		
		assertEquals(5, point.getAbscisse());
	}
	
	@Test
	public void testIfPointIsInThisBloc(){
		Point point = new Point(5, 10);
		
		boolean result = point.isInThisBloc(0,100,0,100);
		
		assertEquals(true, result);
	}

}
