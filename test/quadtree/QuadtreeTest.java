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
		
		assertEquals(new Long(quadParent.getQuadtreeTailleX()/4), new Long (quadchild.getQuadtreeTailleX()));
		assertEquals(new Long(quadParent.getQuadtreeTailleY()/4), new Long (quadchild.getQuadtreeTailleY()));
	}

}
