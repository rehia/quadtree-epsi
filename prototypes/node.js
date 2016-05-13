'use strict'
var Coordinate = require('./coordinate'),
  zoneEnum = require('../enumerators/zone');

function Node(centerCoordinates, halfDimension, zone) {
  this.centerCoordinates = validatePointValue(centerCoordinates);
  this.halfDimension = validateHalfDimensionValue(this.centerCoordinates, halfDimension);
  this.zone = zone;
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

Node.prototype.getZone = function() {
  return this.zone;
};

Node.prototype.setZone = function(newZone) {
  this.zone = validateZoneValue(newZone);
};

Node.prototype.getPoints = function() {
  return this.points;
};

Node.prototype.setPoints = function(newPoints) {
  this.points = validatePointsTableValue(newPoints);
};

Node.prototype.addPoint = function(newPoint) {
  console.log(this.points, this.points.length);
  if (!(this.points.length < 4)) {
    this.childNodes[0].points.push(newPoint);

    //this.childNodes[0].addPoint(validatePointValue(newPoint));
  } else {
    console.log(newPoint.x < this.centerCoordinates.getX());
    var out = (newPoint.x < this.centerCoordinates.getX() || newPoint.y < this.centerCoordinates.getY() || newPoint.getX() > this.halfDimension.getX() - this.centerCoordinates.getX() || newPoint.getX() > this.halfDimension.getX() - this.centerCoordinates.getY());
    if (out) {
      return false;
    } else {
      this.points.push(validatePointValue(newPoint));
    }
  }
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
function validateZoneValue(zoneToValidate) {
  if (typeof zoneToValidate !== "string") {
    throw new Error("Must be a string");
  }
  if (!zoneEnum[zoneToValidate]) {
    throw new Error("Must be a value from the enumerator Zone");
  }
  return zoneToValidate;
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
