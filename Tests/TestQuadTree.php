<?php

include_once "../Quadtree/ICollisionDetector.php";
include_once "../Quadtree/Bounds.php";
include_once "../Quadtree/Point.php";
include_once "../Quadtree/Quadtree.php";
include_once "../Quadtree/Insertable.php";

class TestQuadTree extends PHPUnit_Framework_TestCase
{
    // ...
    // Test pour vérifier l'insertion des points
    public function testContainsPoint()
    {
        $qtBounds = new Bounds(100, 100);
        $qt = new Quadtree($qtBounds);
        $point1 = new Point(50, 50);
        $point2 = new Point(60, 60);
        $qt->insert($point1);

        // Assert
        $this->assertEquals($qt->contains($point1), true); // doit renvoyer vrai
        $this->assertEquals($qt->contains($point2), false); // doit renvoyer faux
    }
    
    // On vérifie que le QuadTree se divise bien lorsque l'on dépasse le nombre maximum de points
    public function testDepth()
    {
        $qtBounds = new Bounds(100, 100);
        $qt = new Quadtree($qtBounds);
        $qt->insert(new Point(50, 50));

        // Assert
        $this->assertEquals($qt->getDepth(), 0); // doit renvoyer vrai
        $qt->insert(new Point(50, 51));
        $qt->insert(new Point(50, 52));
        $qt->insert(new Point(51, 52));
        $qt->insert(new Point(51, 50));
        // verifier que la profondeur est bien supérieur à 0 car on a 5 éléments dans le QuadTree
        // ce qui fait que la grille doit se diviser au minimumn en 4
        $this->assertLessThan($qt->getDepth(), 0); // doit renvoyer vrai
    }
    
    /*
    // test nombre d'items du QuadTree
    public function testSize()
    {
        $qtBounds = new Bounds(100, 100);
        $qt = new Quadtree($qtBounds);
        $qt->insert(new Point(50, 50));
        $qt->insert(new Point(50, 51));
        $qt->insert(new Point(50, 52));
        $qt->insert(new Point(51, 52));
        $qt->insert(new Point(53, 50));
        $qt->insert(new Point(60, 50));
        $qt->insert(new Point(20, 80));
        $qt->insert(new Point(31, 22));
        
        $this->assertEquals($qt->getSize(), 8);
    }*/
    
    // tester que l'on ne peux pas insérer deux fois le même point
    public function testInsertionPoints() {
        $qtBounds = new Bounds(100, 100);
        $qt = new Quadtree($qtBounds);
        $qt->insert(new Point(50, 50));
        
        $this->assertEquals($qt->insert(new Point(50, 50)), false);
    }
    
    public function testGetDepthPoint()
    {
    	
    	$qtBounds = new Bounds(100, 100);
    	$qt = new Quadtree($qtBounds, 1);
    	$this->assertEquals(0, $qt->getDepth());
    
    	$qt->insert(new Point(10, 10));
    	$this->assertEquals(0, $qt->getDepth());
    	$qt->insert(new Point(60, 60));
    	$this->assertEquals(1, $qt->getDepth());
    
    	$qt->insert(new Point(30, 30));
    	$this->assertEquals(2, $qt->getDepth());
    
    	$qt->insert(new Point(15, 15));
    	$this->assertEquals(3, $qt->getDepth());
    }

    // ...
}
