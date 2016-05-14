'use strict'
function Coordinate(x, y) {
  this.x = validateCoordinateValue(x);
  this.y = validateCoordinateValue(y);
};
Coordinate.prototype.getX = function() {
  return this.x;
};
Coordinate.prototype.setX = function(newX) {
  this.x = validateCoordinateValue(newX);
};
Coordinate.prototype.getY = function() {
  return this.y;
};
Coordinate.prototype.setY = function(newY) {
  this.y = validateCoordinateValue(newY);
};
function validateCoordinateValue(valueToValidate) {
  if (typeof valueToValidate !== "number") {
    throw new Error("Coordinate coordinate must be a number");
  }
  if (valueToValidate < 0) {
    throw new Error("Coordinate coordinate must be grater than zero");
  }
  return valueToValidate;
};
module.exports = Coordinate;
