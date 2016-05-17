<<<<<<< HEAD
=======
package quadtree;
>>>>>>> dev_dorian

import junit.framework.Assert;
import quadtree.Point;
import quadtree.Quadtree;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
            boolean isInTheQuadTree = false;
            Point monpoint;
<<<<<<< HEAD
            Quadtree quad = new Quadtree(0,0,100,100);
=======
      
>>>>>>> dev_dorian

            //Act
            monpoint = new Point(20,40);
            isInTheQuadTree = VerifyIsInTheQuadTree(monpoint, isInTheQuadTree);
            
            //Assert
            Assert.assertEquals(isInTheQuadTree, true);
	}
        
        public void randomPointIsInTheQuadTree(){
            
            //Arrange
            boolean isInTheQuadTree = false;
            Point monpoint;
<<<<<<< HEAD
            Quadtree quad = new Quadtree(0,0,100,100);
=======
>>>>>>> dev_dorian
            
            //Act
            monpoint = new Point(0,100,true);
            isInTheQuadTree = VerifyIsInTheQuadTree(monpoint, isInTheQuadTree);
            
            //Assert
            Assert.assertEquals(isInTheQuadTree, true);
            
            
        }
        
        private boolean VerifyIsInTheQuadTree(Point monpoint, boolean isInTheQuadTree) {
        if (monpoint.abcisseX > 0 && monpoint.abcisseX < 100){
            if(monpoint.abcisseY > 0 && monpoint.abcisseY < 100){
                isInTheQuadTree = true;
            }
        }
        return isInTheQuadTree;
    }
        
}

