'use strict'
var Coordinate = require('./coordinate');

function Node(centerCoordinates, halfDimension) {
  this.centerCoordinates = validatePointValue(centerCoordinates);
  this.halfDimension = validateHalfDimensionValue(this.centerCoordinates, halfDimension);
  this.points = [];
  this.childNodes = [];
};

Node.prototype.getCenterCoordinates = function() {
  return this.centerCoordinates;
};

Node.prototype.setCenterCoordinates = function(newCenterCoordinates) {
  this.centerCoordinates = validatePointValue(newCenterCoordinates);
};
Node.prototype.getCenterCoordinatesX = function() {
  return this.centerCoordinates.getX();
}
Node.prototype.getCenterCoordinatesY = function() {
  return this.centerCoordinates.getY();
};

Node.prototype.getHalfDimension = function() {
  return this.halfDimension;
};

Node.prototype.setHalfDimension = function(newHalfDimension) {
  this.halfDimension = validatePointValue(newHalfDimension);
};

Node.prototype.getHalfDimensionX = function() {
  return this.halfDimension.getX();
};
Node.prototype.getHalfDimensionY = function() {
  return this.halfDimension.getY();
};

Node.prototype.getWidth = function() {
  return this.getHalfDimension().getX() - this.getCenterCoordinates().getX();
};

Node.prototype.getHeight = function() {
  return this.getHalfDimension().getY() - this.getCenterCoordinates().getY();
};

Node.prototype.getPoints = function() {
  return this.points;
};

Node.prototype.setPoints = function(newPoints, index) {
  this.points = validatePointsTableValue(newPoints);
};

Node.prototype.emptyPoints = function() {
  this.points = [];
};

Node.prototype.getLengthOfPoints = function() {
  return this.getPoints().length;
};

Node.prototype.isFullOfPoints = function() {
  console.log(this.getLengthOfPoints() < 4);
  return !(this.getLengthOfPoints() < 4);
};

Node.prototype.isOutOfHalfDimension = function(newPoint) {
  return (newPoint.getX() < this.getCenterCoordinatesX() ||  newPoint.getY() < this.getCenterCoordinatesY() ||  newPoint.getX() > this.getHalfDimensionX() ||  newPoint.getY() > this.getHalfDimensionY());
};

Node.prototype.addPointToAllChildNodes = function(point) {
  this.getChildNodes().forEach(function(childNode, index) {
    childNode.addPoint(point);
  });
};

Node.prototype.addPoint = function(newPoint) {
  if (this.isOutOfHalfDimension(newPoint)) {
    return false;
  }
  if (this.isFullOfPoints()) {
    if (this.hasNoChild()) {
      this.splitInFourNodes();
    }
    this.addPointToAllChildNodes(newPoint);

  } else {
    this.points.push(validatePointValue(newPoint));
  }
};

Node.prototype.splitInFourNodes = function() {
  this.createFourNodes();
  var _this = this;
  this.getPoints().forEach(function(point, index) {
    _this.addPointToAllChildNodes(point);
  });
  this.emptyPoints();
};

Node.prototype.createFourNodes = function() {
  var halfDimensionX = this.getHalfDimensionX(),
    halfDimensionY = this.getHalfDimensionY(),
    centerCoordinatesX = this.getCenterCoordinatesX(),
    centerCoordinatesY = this.getCenterCoordinatesY(),
    newCenterCoordinatesX = halfDimensionX / 2,
    newCenterCoordinatesY = halfDimensionY / 2,
    newHalfDimensionX = (halfDimensionX - centerCoordinatesX) / 2,
    newHalfDimensionY = (halfDimensionY - centerCoordinatesY) / 2,
    newHalfDimension = new Coordinate(newHalfDimensionX, newHalfDimensionY);
  this.addChildNode(new Node(this.getCenterCoordinates(), newHalfDimension));
  this.addChildNode(new Node(new Coordinate(newCenterCoordinatesX, centerCoordinatesY), new Coordinate(halfDimensionX, newHalfDimensionY)));
  this.addChildNode(new Node(new Coordinate(centerCoordinatesX, newCenterCoordinatesY), new Coordinate(newHalfDimensionX, halfDimensionY)));
  this.addChildNode(new Node(new Coordinate(newCenterCoordinatesX, centerCoordinatesY), this.getHalfDimension()));
};
Node.prototype.hasNoChild = function() {
  return this.getChildNodes().length === 0;
};

Node.prototype.hasNotToManyChild = function () {
  return this.getChildNodes().length < 4;
}
Node.prototype.getChildNodes = function() {
  return this.childNodes;
};

Node.prototype.setChildNodes = function(newChildNodes) {
  this.childNodes = validateNodesTableValue(newChildNodes);
};

Node.prototype.addChildNode = function(newChildNode) {
  if (this.hasNotToManyChild()) {
    this.childNodes.push(validateNodeValue(newChildNode));
  } else {
    throw new Error("Node can't have more than 4 child");
  }
};

function validatePointValue(pointToValidate) {
  if (!(pointToValidate instanceof Coordinate)) {
    throw new Error("Must be a Coordinate");
  }
  return pointToValidate;
};

function validatePointsTableValue(pointsTableToValidate) {
  pointsTableToValidate.forEach(function(pointToValidate, index) {
    validatePointValue(pointToValidate);
  });
  return pointsTableToValidate;
};

function validateHalfDimensionValue(centerCoordinatesToCompareWith, halfDimensionToValidate) {
  halfDimensionToValidate = validatePointValue(halfDimensionToValidate);
  if (centerCoordinatesToCompareWith.x === halfDimensionToValidate.x || centerCoordinatesToCompareWith.y === halfDimensionToValidate.y) {
    throw new Error("centerCoordinates must be different from halfDimension");
  }
  return halfDimensionToValidate;
};

function validateNodesTableValue(nodesTableToValidate) {
  nodesTableToValidate.forEach(function(nodeToValidate, index) {
    validateNodeValue(nodeToValidate);
  });
  return nodesTableToValidate;
};

function validateNodeValue(nodeToValidate) {
  if (!(nodeToValidate instanceof Node)) {
    throw new Error("Must be a Node");
  }
  return nodeToValidate;
};
module.exports = Node;
