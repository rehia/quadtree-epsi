'use strict'
var Coordinate = require('./coordinate');
function Node (centerCoordinates, halfDimension) {
  this.centerCoordinates = validatePointValue(centerCoordinates);
  this.halfDimension = validatePointValue(halfDimension);
  this.points = [];
  this.childNodes = [];
};
Node.prototype.getCenterCoordinates = function () {
  return this.centerCoordinates;
};
Node.prototype.setCenterCoordinates = function (newCenterCoordinates) {
  this.centerCoordinates = validatePointValue(newCenterCoordinates);
};
Node.prototype.getHalfDimension = function () {
  return this.getHalfDimension;
};
Node.prototype.setHalfDimension = function (newHalfDimension) {
  this.halfDimension = validatePointValue(newHalfDimension);
};
Node.prototype.getPoints = function () {
  return this.points;
};
Node.prototype.setPoints = function (newPoints) {
  this.points = validatePointsTableValue(newPoints);
};
function validatePointsTableValue(pointsTableToValidate) {
  pointsTableToValidate.forEach(function (pointToValidate, index) {
    validatePointValue(pointToValidate);
  });
  return pointsTableToValidate;
};
function validatePointValue (pointToValidate){
  if (!(pointToValidate instanceof Coordinate)) {
    throw new Error("Must be a Coordinate");
  }
  return pointToValidate;
};
module.exports = Node;
