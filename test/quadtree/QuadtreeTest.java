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
	
	@Test
	public void createQuadtreeWithGoodSizeForXAnDY()
	{
		Quadtree quadParent = new Quadtree();
		Quadtree quadchild = new Quadtree();
		
		quadParent.subdividion();
		quadchild = quadParent.getNordeEst();
		
		assertEquals(new Long((long) (quadParent.getQuadtreeTailleX()/4)), new Long ((long) quadchild.getQuadtreeTailleX()));
		assertEquals(new Long((long) (quadParent.getQuadtreeTailleY()/4)), new Long ((long) quadchild.getQuadtreeTailleY()));
	}
	
	@Test
	public void checkIfthePointisOnTheLine()
	{
		boolean isOnLine = false;
		Quadtree quad = new Quadtree();
		Point p = new Point(100, 50);
		
		isOnLine = quad.estSurLaLigne(p);
		
		assertTrue(isOnLine);
		
	}

}
