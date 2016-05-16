<?php

include_once "Quadtree/Quadtree.php";
include_once "Quadtree/Bounds.php";
include_once "Quadtree/Point.php";

$qtBounds = new Bounds(100, 100);
$qt = new Quadtree($qtBounds);

for ($i=0; $i<50; $i++) {
	do {
            $rand = rand(1, 99);
            $rand2 = rand(1, 99);
            $point = new Point($rand, $rand2);
        }
        while( $qt->insert($point) );
        $points[] = $point;
}
echo "Quadtree prof :".$qt->getDepth()."\n";
foreach ( $points as $point ) {
    echo "==================== POINT(".$point->getLeft().",".$point->getTop().") ====================";
    echo "Profondeur : ".$qt->getProfondeurPoint($point)." | \n";
    echo "Points les plus proches : \n";
    $nearpoints = $qt->getNearestPoints($point);
    foreach($nearpoints as $n) {
        echo "- Point(".$n->getLeft().",".$n->getTop().")\n";
    }
}

//$qt->dumpTree();

?>