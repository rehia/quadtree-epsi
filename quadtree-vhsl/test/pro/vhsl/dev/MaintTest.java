package pro.vhsl.dev;

import static org.junit.Assert.*;

import org.junit.Test;

public class MaintTest {

	@Test
	public void shouldCreateARandomPointWithinRange() {
		XY point = Main.createRandomPoint(100);
		
		assertNotNull(point);
	}

}
