package quadtree;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class PointBehavior {

	@Test
	public void shouldGenerateAPointWithRandomCoordinatesWithinBounds() {
		Point point = Point.random(100, 100);
		
		assertThat(point.getX(), is(lessThanOrEqualTo(100)));
		assertThat(point.getY(), is(lessThanOrEqualTo(100)));
	}

	@Test
	public void shouldFormatPointAsAStringWithCoordinates() {
		Point point = new Point(10, 15);
		
		assertEquals("{10,15}", point.toString());
	}
}
