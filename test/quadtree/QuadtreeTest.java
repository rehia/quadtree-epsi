package quadtree;

import static org.junit.Assert.*;

import org.junit.Test;

public class QuadtreeTest {



	@Test
	public void getNbPMax()
	{
		//Arrange
		int nbPointMax;
		Quadtree quad = new Quadtree();
		
		//Act
		nbPointMax = quad.getNbPointMax();
		
		//Assert
		assertEquals(new Long(4), new Long(nbPointMax));
		
	}

}
