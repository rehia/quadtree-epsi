<?php

include_once "Bounds.php";
include_once "Insertable.php";

interface ICollisionDetector
{
    /**
     * Test if insertable item intersects with bounds
     * @param \Quadtree\Geometry\Bounds $bounds
     * @param \Quadtree\Insertable $item
     * @return boolean
     */
    function intersects(Bounds $bounds, Insertable $item);
    
    /**
     * Test if new item collides with collection of items
     * @param \Quadtree\Insertable[] $insertedItems
     * @param \Quadtree\Insertable $item
     * @return boolean
     */
    function collide(array $insertedItems, Insertable $item);
}
