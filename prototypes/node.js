var Coordinate = require('./coordinate');
function Node (centerCoordinates, halfDimension) {
  if (!(centerCoordinates instanceof Coordinate)) {
    throw new Error("Node must have Coordinate as attribute value");
  }
  if (!(halfDimension instanceof Coordinate)) {
    throw new Error("Node must have Coordinate as attribute value");
  }
  this.centerCoordinates = centerCoordinates;
  this.halfDimension = halfDimension;
};
Node.prototype.getCenterCoordinates = function () {
  return this.centerCoordinates;
};
Node.prototype.setCenterCoordinates = function (newCenterCoordinates) {
  if (!(newCenterCoordinates instanceof Coordinate)) {
    throw new Error("Node must have Coordinate as attribute value");
  }
  this.centerCoordinates = newCenterCoordinates;
};
Node.prototype.getHalfDimension = function () {
  return this.getHalfDimension;
};
Node.prototype.setHalfDimension = function (newHalfDimension) {
  if (!(newHalfDimension instanceof Coordinate)) {
    throw new Error("Node must have Coordinate as attribute value");
  }
  this.halfDimension = newHalfDimension;
}
module.exports = Node;
