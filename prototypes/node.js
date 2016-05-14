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

Node.prototype.setPoints = function(newPoints) {
  this.points = validatePointsTableValue(newPoints);
};

Node.prototype.addPoint = function(newPoint) {
  if (!(this.points.length < 4)) {
    if (this.hasNoChild()) {
      var centerX = this.halfDimension.x / 2;
      var centerY = this.halfDimension.y / 2;
      var halfDimensionX = (this.halfDimension.x - this.centerCoordinates.x) / 2;
      var halfDimensionY = (this.halfDimension.y - this.centerCoordinates.y) / 2;
      var newHalfDimension = new Coordinate(halfDimensionX, halfDimensionY);
      this.childNodes.push(new Node(new Coordinate(this.centerCoordinates.x, this.centerCoordinates.y), newHalfDimension));
      this.childNodes.push(new Node(new Coordinate(centerX, this.centerCoordinates.y), new Coordinate(this.halfDimension.x, newHalfDimension.y)));
      this.childNodes.push(new Node(new Coordinate(this.centerCoordinates.x, centerY), new Coordinate(newHalfDimension.x, this.halfDimension.y)));
      this.childNodes.push(new Node(new Coordinate(centerX, this.centerCoordinates.y), this.halfDimension));
      var _this = this;
      this.points.forEach(function(point, index) {
        _this.childNodes[0].addPoint(point);
        _this.childNodes[1].addPoint(point);
        _this.childNodes[2].addPoint(point);
        _this.childNodes[3].addPoint(point);
      });
      this.points = [];
    }
    this.childNodes[0].points.push(newPoint);
  } else {
    var out = (newPoint.x < this.centerCoordinates.getX() ||  newPoint.y < this.centerCoordinates.getY() ||  newPoint.getX() > this.halfDimension.getX() - this.centerCoordinates.getX() ||  newPoint.getX() > this.halfDimension.getX() - this.centerCoordinates.getY());
    if (out) {
      return false;
    } else {
      this.points.push(validatePointValue(newPoint));
    }
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
    //TODO
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
