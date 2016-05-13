var Coordinate = require('./coordinate');
function Node (centerCoordinates, halfDimension) {
  this.centerCoordinates = validateNodeValue(centerCoordinates);
  this.halfDimension = validateNodeValue(halfDimension);
};
Node.prototype.getCenterCoordinates = function () {
  return this.centerCoordinates;
};
Node.prototype.setCenterCoordinates = function (newCenterCoordinates) {
  this.centerCoordinates = validateNodeValue(newCenterCoordinates);
};
Node.prototype.getHalfDimension = function () {
  return this.getHalfDimension;
};
Node.prototype.setHalfDimension = function (newHalfDimension) {
  this.halfDimension = validateNodeValue(newHalfDimension);
}
function validateNodeValue (valueToValidate){
  if (!(valueToValidate instanceof Coordinate)) {
    throw new Error("Node must have Coordinate as attribute value");
  }
  return valueToValidate;
};
module.exports = Node;
