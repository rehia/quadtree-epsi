<?php

include_once "ICollisionDetector.php";
include_once "Bounds.php";
include_once "Insertable.php";

abstract class QuadtreeAbstract
{
    const CAPACITY = 4;

    /** @var ICollisionDetector */
    private $detector;

    /** @var Geometry\Boundss */
    private $bounds;

    /** @var int */
    private $capacity;

    /** @var Insertable[] */
    private $items = array();

    /** @var Quadtree */
    private $nw;

    /** @var Quadtree */
    private $ne;

    /** @var Quadtree */
    private $sw;

    /** @var Quadtree */
    private $se;
    
    /** @var boolean */
    private $overlappingInserted = FALSE;

    /**
     * @param \Quadtree\ICollisionDetector $detector
     * @param \Quadtree\Geometry\Bounds $bounds
     * @param int $leafCapacity
     */
    function __construct(ICollisionDetector $detector, Bounds $bounds, $leafCapacity = NULL)
    {
        $this->detector = $detector;
        $this->bounds = $bounds;

        $capacity = (int)$leafCapacity;
        if ($capacity <= 0) {
            $capacity = static::CAPACITY;
        }
        $this->capacity = $capacity;
    }

    /**
     * @param \Quadtree\Insertable $item
     * @return boolean
     */
    public function insert(Insertable $item)
    {
        if (!$this->detector->intersects($this->bounds, $item)) {
            return FALSE;
        }
        if ($this->collideWithItems($item)) {
            return FALSE;
        }
        
        return $this->insertItem($item);
    }

    /**
     * @param \Quadtree\Insertable $item
     * @return boolean
     */
    public function collideWithItems(Insertable $item)
    {
        if (!$this->detector->intersects($this->bounds, $item)) {
            return false;
        }
        if ($this->nw === NULL) {
            return $this->detector->collide($this->items, $item);
        } else {
            return $this->nw->collideWithItems($item)
                    || $this->ne->collideWithItems($item)
                    || $this->sw->collideWithItems($item)
                    || $this->se->collideWithItems($item);
        }
    }
    
    public function collideWithBounds(Insertable $item)
    {
        
    }
    
    /**
     * @param \Quadtree\Insertable $item
     * @return boolean
     */
    public function getNearestPoints(Point $point)
    {
        $items2 = array();
        if ($this->nw === NULL) {
            foreach ( $this->items as $item ) {
                if ($point == $item) {
                    $items2 = $this->items;
                }
            }
        }
         else {
            $res = $this->nw->getNearestPoints($point);
            $res = array_merge($res, $this->ne->getNearestPoints($point));
            $res = array_merge($res, $this->sw->getNearestPoints($point));
            $res = array_merge($res, $this->se->getNearestPoints($point));
            if ( $res )
                $items2 = $res;
        }
        foreach ( $items2 as $key => $item ) {
            //echo $item->getLeft()." | ".$point->getLeft()."\n";
            if ($item->getLeft() == $point->getLeft() && $item->getTop() == $point->getTop()) {
                //echo "Test";
                unset($items2[$key]);
            }
        }
        return $items2;
    }

    /**
     * @param \Quadtree\Insertable $item
     * @return boolean
     */
    private function insertItem(Insertable $item)
    {
        if ($this->overlappingInserted || $item->getBounds()->contains($this->bounds)) {
            $this->items[] = $item;
            $this->overlappingInserted = true;
            return TRUE;
        }

        if ($this->nw === NULL && count($this->items) < $this->capacity) {
            $this->items[] = $item;
            return TRUE;
        } else {
            if (count($this->items) >= $this->capacity) {
                $this->subdivide();
            }
            return $this->insertItemIntoSubtrees($item);
        }
    }
    
    public function getProfondeurPoint(Point $pt) {
        if ( $this->bounds->containsPoint($pt) ) {
            if ($this->nw === NULL) {
                return $this->getDepth();
            } else {
                $prof = $this->nw->getProfondeurPoint($pt);
                if ( !$prof ) {
                   $prof = $this->ne->getProfondeurPoint($pt); 
                   if ( !$prof ) {
                       $prof = $this->sw->getProfondeurPoint($pt);
                       if ( !$prof ) {
                           $prof = $this->se->getProfondeurPoint($pt);
                       }
                   }
                }
                return 1+$prof;
            }
        } else {
            return false;
        }
    }
    
    public function contains($pt) {
        if ($this->nw === NULL) {
            foreach ( $this->items as $item ) {
                if ($pt == $item) {
                    return true;
                }
            }
            return false;
        }
         else {
            foreach ( $this->items as $item ) {
                return ($this->nw->contains($pt) || $this->ne->contains($pt)
                        ||$this->sw->contains($pt) || $this->se->contains($pt));
            }
        }
    }

    /**
     * @param \Quadtree\Insertable $item
     * @return boolean
     */
    private function insertItemIntoSubtrees(Insertable $item)
    {
        $nwIn = $this->nw->insert($item);
        $neIn = $this->ne->insert($item);
        $swIn = $this->sw->insert($item);
        $seIn = $this->se->insert($item);
        return $nwIn || $neIn || $swIn || $seIn;
    }

    /**
     * Number of levels in the tree
     * @return int
     */
    public function getDepth()
    {
        if ($this->nw === NULL) {
            return 0;
        } else {
            $max = max($this->nw->getDepth(), $this->ne->getDepth(), $this->sw->getDepth(), $this->se->getDepth());
            return 1 + $max;
        }
    }

    /**
     * Number of items in the tree
     * @return int
     */
    public function getSize()
    {
        if ($this->nw === NULL) {
            return count($this->items);
        } else {
            return $this->nw->getSize() + $this->ne->getSize() + $this->sw->getSize() + $this->se->getSize();
        }
    }

    private function subdivide()
    {
        list($this->nw, $this->ne, $this->sw, $this->se) = $this->getDividedBounds();
        foreach ($this->items as $item) {
            $this->nw->insert($item);
            $this->ne->insert($item);
            $this->sw->insert($item);
            $this->se->insert($item);
        }
        $this->items = array();
    }

    /**
     * @return QuadtreeAbstract[]
     */
    private function getDividedBounds()
    {
        $c = $this->bounds->getCenter();
        $width = $this->bounds->getWidth() / 2;
        $height = $this->bounds->getHeight() / 2;

        $nw = new static(new Bounds($width, $height, $c->getLeft() - $width, $c->getTop() - $height), $this->capacity);
        $ne = new static(new Bounds($width, $height, $c->getLeft(), $c->getTop() - $height), $this->capacity);
        $sw = new static(new Bounds($width, $height, $c->getLeft() - $width, $c->getTop()), $this->capacity);
        $se = new static(new Bounds($width, $height, $c->getLeft(), $c->getTop()), $this->capacity);

        return array($nw, $ne, $sw, $se);
    }
    
    function dumpTree( $depth = 1, $indentation = "")
    {
        if ($this->nw === NULL) {
            echo $indentation."Node\n";
        } else {
            $this->nw->dumpTree($depth+1, $indentation."    ");
            $this->ne->dumpTree($depth+1, $indentation."    ");
            echo $indentation."Node".$depth."\n";
            $this->sw->dumpTree($depth+1, $indentation."    ");
            $this->se->dumpTree($depth+1, $indentation."    ");
        }
    }

}