var Node = require('./prototypes/node'),
    Coordinate = require('./prototypes/coordinate'),
    Quadtree = require('./prototypes/quadtree');
var dumpQuadtree = new Quadtree(100, 100);
var dumpRandom = function () {
    return Math.floor(Math.random() * (100 - 0 + 1) + 0);
};
/* more than twelve throw  RangeError: Maximum call stack size exceeded
 should maybe go full asynchrone*/
for (var i = 0; i < 12; i++) {
    dumpQuadtree.addPoint(new Coordinate(dumpRandom(), dumpRandom()));
}
dumpQuadtree.toString();