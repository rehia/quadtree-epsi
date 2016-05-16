<?php

include_once "../Quadtree/ICollisionDetector.php";
include_once "../Quadtree/Bounds.php";
include_once "../Quadtree/Point.php";
include_once "../Quadtree/Quadtree.php";
include_once "../Quadtree/Insertable.php";

class testBounds extends PHPUnit_Framework_TestCase
{
    // ...
    // Test pour vérifier l'insertion des points, contenu dans des rectangles, les intersections etc ..
    public function testContainsPoint()
    {
        $qtBounds = new Bounds(50, 50, 1, 1);
        $qtBounds2 = new Bounds(50, 50, 50, 0);
        $point1 = new Point(25, 25);
        $point2 = new Point(50, 25);

        // Assert
        $this->assertEquals($qtBounds->containsPoint($point1), true);
        $this->assertEquals($qtBounds2->containsPoint($point1), false);
        $this->assertEquals($qtBounds->containsPoint($point2), true);
        $this->assertEquals($qtBounds2->containsPoint($point2), true);
    }
    
    public function testInsert()
    {

        $qtBounds = new Bounds(50, 50);
        $qt = new Quadtree($qtBounds);
        $this->assertTrue($qt->insert(new Bounds(10, 10, 5, 5)));
        $this->assertFalse($qt->insert(new Bounds(10, 10, 5, 5)));
        
        $this->assertTrue($qt->insert(new Bounds(10, 10, 20, 20)));
        $this->assertFalse($qt->insert(new Bounds(5, 5, 25, 25)));
        
        $qt2 = new Quadtree($qtBounds);
        $this->assertTrue($qt2->insert(new Bounds(100, 100, -20, -20)));
        $this->assertFalse($qt->insert(new Bounds(10, 10, 5, 5)));
        
        $qt3 = new Quadtree($qtBounds);
        $this->assertTrue($qt3->insert(new Bounds(2, 2, 10, 40)));
        $this->assertTrue($qt3->insert(new Bounds(2, 2, 40, 40)));
        $this->assertTrue($qt3->insert(new Bounds(2, 2, 40, 10)));
        $this->assertTrue($qt3->insert(new Bounds(2, 2, 15, 15)));
        
        $this->assertFalse($qt3->insert(new Bounds(2, 2, 40, 10)));
        
        $this->assertEquals(2, $qt->getSize());
        $this->assertEquals(1, $qt2->getSize());
        $this->assertEquals(4, $qt3->getSize());
    }
    
    public function testIntersection()
    {
        $qtBounds = new Bounds(50, 50, 1, 1);
        $qtBounds2 = new Bounds(50, 50, 20, 30);  
        $qtBounds3 = new Bounds(50, 50, 150, 1);  
        
        $this->assertEquals($qtBounds->intersects($qtBounds2), true);
        $this->assertEquals($qtBounds->intersects($qtBounds3), false);
    }
    
    public function testCenter() {
        $qtBounds = new Bounds(50, 50, 0, 0);
        $center = $qtBounds->getCenter();
        $this->assertEquals($center->getLeft(), 25);
        $this->assertEquals($center->getTop(), 25);
    }
}
