
import quadtree.Point;
import quadtree.Quadtree;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * @author sebastien
 */
public class PointTest {
    
    @Test
	public void pointIsInTheQuadTree()
	{
		//Arrange
                Point monpoint;
		Quadtree quad = new Quadtree();
		
		//Act
		monpoint = new Point(20,40);
		
		//Assert
		//test si 20 est compris entre xmin et xmax, idem pour 40 compris entre ymin et ymax
		
	}
    
}
