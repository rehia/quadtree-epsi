'use strict'
var Node = require('./node'),
  Coordinate = require('./coordinate');

function Quadtree(halfDimensionX, halfDimensionY) {
  this.originNode = new Node(new Coordinate(0, 0), new Coordinate(halfDimensionX, halfDimensionY));
  this.points = [];
};

Quadtree.prototype.getPoints = function () {
  return this.points;
};
Quadtree.prototype.getOriginNode = function () {
  return this.originNode;
};

Quadtree.prototype.addPoint = function (newPoint) {
  if (this.originNode.addPoint(newPoint)){
    this.points.push(newPoint);
  }
};
module.exports = Quadtree;
