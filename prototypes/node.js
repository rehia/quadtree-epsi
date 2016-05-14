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

Node.prototype.getHalfDimension = function() {
  return this.getHalfDimension;
};

Node.prototype.setHalfDimension = function(newHalfDimension) {
  this.halfDimension = validatePointValue(newHalfDimension);
};

Node.prototype.getPoints = function() {
  return this.points;
};

Node.prototype.setPoints = function(newPoints, index) {
  this.points = validatePointsTableValue(newPoints);
};

Node.prototype.addPoint = function(newPoint, index) {
  var out = (newPoint.x < this.centerCoordinates.getX() ||  newPoint.y < this.centerCoordinates.getY() ||  newPoint.getX() > this.halfDimension.getX() - this.centerCoordinates.getX() ||  newPoint.getX() > this.halfDimension.getX() - this.centerCoordinates.getY());
  if (out) {
    console.log('return false');
    return false;
  }
  if (!(this.points.length < 4)) {
    if (this.hasNoChild()) {
      var centerX = this.halfDimension.x / 2;
      var centerY = this.halfDimension.y / 2;
      var halfDimensionX = (this.halfDimension.x - this.centerCoordinates.x) / 2;
      var halfDimensionY = (this.halfDimension.y - this.centerCoordinates.y) / 2;
      var newHalfDimension = new Coordinate(halfDimensionX, halfDimensionY);
      this.addChildNode(new Node(new Coordinate(this.centerCoordinates.x, this.centerCoordinates.y), newHalfDimension));
      this.addChildNode(new Node(new Coordinate(centerX, this.centerCoordinates.y), new Coordinate(this.halfDimension.x, newHalfDimension.y)));
      this.addChildNode(new Node(new Coordinate(this.centerCoordinates.x, centerY), new Coordinate(newHalfDimension.x, this.halfDimension.y)));
      this.addChildNode(new Node(new Coordinate(centerX, this.centerCoordinates.y), this.halfDimension));
      var _this = this;
      this.points.forEach(function(point, index) {
        _this.childNodes[0].addPoint(point, index);
        _this.childNodes[1].addPoint(point, index);
        _this.childNodes[2].addPoint(point, index);
        _this.childNodes[3].addPoint(point, index);
      });
      console.log(this.childNodes[0]);
      this.points = [];
    }
    console.log(newPoint);
    this.childNodes[0].addPoint(newPoint);
    this.childNodes[1].addPoint(newPoint);
    this.childNodes[2].addPoint(newPoint);
    this.childNodes[3].addPoint(newPoint);

  } else {
      this.points.push(validatePointValue(newPoint));
      console.log('add point ', index, this.points);
  }
};

Node.prototype.hasNoChild = function() {
  return this.childNodes.length === 0;
};
Node.prototype.getChildNodes = function() {
  return this.childNodes;
};

Node.prototype.setChildNodes = function(newChildNodes) {
  this.childNodes = validateNodesTableValue(newChildNodes);
};

Node.prototype.addChildNode = function(newChildNode) {
  if (this.childNodes.length < 4) {
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
