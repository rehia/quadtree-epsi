function Point(x, y) {
  this.x = validateCoordinateValue(x);
  this.y = validateCoordinateValue(y);
};
Point.prototype.getX = function() {
  return this.x;
};
Point.prototype.setX = function(newX) {
  this.x = validateCoordinateValue(newX);
};
Point.prototype.getY = function() {
  return this.y;
};
Point.prototype.setY = function(newY) {
  this.y = validateCoordinateValue(newY);
};
function validateCoordinateValue(valueToValidate) {
  if (typeof valueToValidate !== "number") {
    throw new Error("Point coordinate must be a number");
  }
  if (valueToValidate < 0) {
    throw new Error("Point coordinate must be grater than zero");
  }
  return valueToValidate;
};
module.exports = Point;
