<?php

include_once "QuadtreeAbstract.php";
include_once "CollisionDetector.php";
include_once "Bounds.php";

class Quadtree extends QuadtreeAbstract
{
    function __construct(Bounds $bounds, $leafCapacity = NULL)
    {
        $intersector = new CollisionDetector();
        parent::__construct($intersector, $bounds, $leafCapacity);
    }
}