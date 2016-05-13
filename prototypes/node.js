var Coordinate = require('./coordinate');
function Node (centerCoordinates, halfDimension) {
  if (!centerCoordinates instanceof Coordinate) {
    throw new Error("Must be a Coordinate");
  }
  this.centerCoordinates = centerCoordinates;
  this.halfDimension = halfDimension;
};
Node.prototype.getCenterCoordinates = function () {
  return this.centerCoordinates;
};
Node.prototype.setCenterCoordinates = function (newCenterCoordinates) {
  this.centerCoordinates = newCenterCoordinates;
};
Node.prototype.getHalfDimension = function () {
  return this.getHalfDimension;
};
Node.prototype.setHalfDimension = function (newHalfDimension) {
  this.halfDimension = newHalfDimension;
}
module.exports = Node;
