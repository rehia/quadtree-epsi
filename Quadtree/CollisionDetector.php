<?php

include_once "ICollisionDetector.php";
include_once "Bounds.php";
include_once "Insertable.php";

class CollisionDetector implements ICollisionDetector
{
    /**
     * @param \Quadtree\Geometry\Bounds $bounds
     * @param \Quadtree\Insertable $item
     * @return boolean
     */
    public function intersects(Bounds $bounds, Insertable $item)
    {
        return $bounds->getBounds()->intersects($item->getBounds());
    }
    
    /**
     * @param \Quadtree\Insertable[] $insertedItems
     * @param \Quadtree\Insertable $item
     * @return boolean
     */
    public function collide(array $insertedItems, Insertable $item) {
        foreach ($insertedItems as $insertedItem) {
            /* @var $insertedItem Insertable */
            if ($insertedItem->getBounds()->intersects($item->getBounds())) {
                return TRUE;
            }
        }
        return FALSE;
    }
}
    