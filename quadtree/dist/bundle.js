(function e(t,n,r){function s(o,u){if(!n[o]){if(!t[o]){var a=typeof require=="function"&&require;if(!u&&a)return a(o,!0);if(i)return i(o,!0);var f=new Error("Cannot find module '"+o+"'");throw f.code="MODULE_NOT_FOUND",f}var l=n[o]={exports:{}};t[o][0].call(l.exports,function(e){var n=t[o][1][e];return s(n?n:e)},l,l.exports,e,t,n,r)}return n[o].exports}var i=typeof require=="function"&&require;for(var o=0;o<r.length;o++)s(r[o]);return s})({1:[function(require,module,exports){
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

},{}],2:[function(require,module,exports){
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
  return true;
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

},{"./coordinate":1}],3:[function(require,module,exports){
'use strict'
var Node = require('./node'),
  Coordinate = require('./coordinate');

function Quadtree(halfDimensionX, halfDimensionY) {
  this.originNode = new Node(new Coordinate(0, 0), new Coordinate(halfDimensionX, halfDimensionY));
  this.points = [];
};

Quadtree.prototype.getPoints = function () {
  return this.points;
};
Quadtree.prototype.getOriginNode = function () {
  return this.originNode;
};

Quadtree.prototype.addPoint = function (newPoint) {
  if (this.originNode.addPoint(newPoint)){
    this.points.push(newPoint);
  }
};
module.exports = Quadtree;

},{"./coordinate":1,"./node":2}]},{},[3])
//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi4uLy4uLy4uLy4uLy4uLy4uLy4uLy4uLy4uL3Vzci9saWIvbm9kZV9tb2R1bGVzL3dhdGNoaWZ5L25vZGVfbW9kdWxlcy9icm93c2VyaWZ5L25vZGVfbW9kdWxlcy9icm93c2VyLXBhY2svX3ByZWx1ZGUuanMiLCJjb29yZGluYXRlLmpzIiwibm9kZS5qcyIsInF1YWR0cmVlLmpzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0FDQUE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7O0FDM0JBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOztBQ2pMQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBIiwiZmlsZSI6ImdlbmVyYXRlZC5qcyIsInNvdXJjZVJvb3QiOiIiLCJzb3VyY2VzQ29udGVudCI6WyIoZnVuY3Rpb24gZSh0LG4scil7ZnVuY3Rpb24gcyhvLHUpe2lmKCFuW29dKXtpZighdFtvXSl7dmFyIGE9dHlwZW9mIHJlcXVpcmU9PVwiZnVuY3Rpb25cIiYmcmVxdWlyZTtpZighdSYmYSlyZXR1cm4gYShvLCEwKTtpZihpKXJldHVybiBpKG8sITApO3ZhciBmPW5ldyBFcnJvcihcIkNhbm5vdCBmaW5kIG1vZHVsZSAnXCIrbytcIidcIik7dGhyb3cgZi5jb2RlPVwiTU9EVUxFX05PVF9GT1VORFwiLGZ9dmFyIGw9bltvXT17ZXhwb3J0czp7fX07dFtvXVswXS5jYWxsKGwuZXhwb3J0cyxmdW5jdGlvbihlKXt2YXIgbj10W29dWzFdW2VdO3JldHVybiBzKG4/bjplKX0sbCxsLmV4cG9ydHMsZSx0LG4scil9cmV0dXJuIG5bb10uZXhwb3J0c312YXIgaT10eXBlb2YgcmVxdWlyZT09XCJmdW5jdGlvblwiJiZyZXF1aXJlO2Zvcih2YXIgbz0wO288ci5sZW5ndGg7bysrKXMocltvXSk7cmV0dXJuIHN9KSIsIid1c2Ugc3RyaWN0J1xuZnVuY3Rpb24gQ29vcmRpbmF0ZSh4LCB5KSB7XG4gIHRoaXMueCA9IHZhbGlkYXRlQ29vcmRpbmF0ZVZhbHVlKHgpO1xuICB0aGlzLnkgPSB2YWxpZGF0ZUNvb3JkaW5hdGVWYWx1ZSh5KTtcbn07XG5Db29yZGluYXRlLnByb3RvdHlwZS5nZXRYID0gZnVuY3Rpb24oKSB7XG4gIHJldHVybiB0aGlzLng7XG59O1xuQ29vcmRpbmF0ZS5wcm90b3R5cGUuc2V0WCA9IGZ1bmN0aW9uKG5ld1gpIHtcbiAgdGhpcy54ID0gdmFsaWRhdGVDb29yZGluYXRlVmFsdWUobmV3WCk7XG59O1xuQ29vcmRpbmF0ZS5wcm90b3R5cGUuZ2V0WSA9IGZ1bmN0aW9uKCkge1xuICByZXR1cm4gdGhpcy55O1xufTtcbkNvb3JkaW5hdGUucHJvdG90eXBlLnNldFkgPSBmdW5jdGlvbihuZXdZKSB7XG4gIHRoaXMueSA9IHZhbGlkYXRlQ29vcmRpbmF0ZVZhbHVlKG5ld1kpO1xufTtcbmZ1bmN0aW9uIHZhbGlkYXRlQ29vcmRpbmF0ZVZhbHVlKHZhbHVlVG9WYWxpZGF0ZSkge1xuICBpZiAodHlwZW9mIHZhbHVlVG9WYWxpZGF0ZSAhPT0gXCJudW1iZXJcIikge1xuICAgIHRocm93IG5ldyBFcnJvcihcIkNvb3JkaW5hdGUgY29vcmRpbmF0ZSBtdXN0IGJlIGEgbnVtYmVyXCIpO1xuICB9XG4gIGlmICh2YWx1ZVRvVmFsaWRhdGUgPCAwKSB7XG4gICAgdGhyb3cgbmV3IEVycm9yKFwiQ29vcmRpbmF0ZSBjb29yZGluYXRlIG11c3QgYmUgZ3JhdGVyIHRoYW4gemVyb1wiKTtcbiAgfVxuICByZXR1cm4gdmFsdWVUb1ZhbGlkYXRlO1xufTtcbm1vZHVsZS5leHBvcnRzID0gQ29vcmRpbmF0ZTtcbiIsIid1c2Ugc3RyaWN0J1xudmFyIENvb3JkaW5hdGUgPSByZXF1aXJlKCcuL2Nvb3JkaW5hdGUnKTtcblxuZnVuY3Rpb24gTm9kZShjZW50ZXJDb29yZGluYXRlcywgaGFsZkRpbWVuc2lvbikge1xuICB0aGlzLmNlbnRlckNvb3JkaW5hdGVzID0gdmFsaWRhdGVQb2ludFZhbHVlKGNlbnRlckNvb3JkaW5hdGVzKTtcbiAgdGhpcy5oYWxmRGltZW5zaW9uID0gdmFsaWRhdGVIYWxmRGltZW5zaW9uVmFsdWUodGhpcy5jZW50ZXJDb29yZGluYXRlcywgaGFsZkRpbWVuc2lvbik7XG4gIHRoaXMucG9pbnRzID0gW107XG4gIHRoaXMuY2hpbGROb2RlcyA9IFtdO1xufTtcblxuTm9kZS5wcm90b3R5cGUuZ2V0Q2VudGVyQ29vcmRpbmF0ZXMgPSBmdW5jdGlvbigpIHtcbiAgcmV0dXJuIHRoaXMuY2VudGVyQ29vcmRpbmF0ZXM7XG59O1xuXG5Ob2RlLnByb3RvdHlwZS5zZXRDZW50ZXJDb29yZGluYXRlcyA9IGZ1bmN0aW9uKG5ld0NlbnRlckNvb3JkaW5hdGVzKSB7XG4gIHRoaXMuY2VudGVyQ29vcmRpbmF0ZXMgPSB2YWxpZGF0ZVBvaW50VmFsdWUobmV3Q2VudGVyQ29vcmRpbmF0ZXMpO1xufTtcbk5vZGUucHJvdG90eXBlLmdldENlbnRlckNvb3JkaW5hdGVzWCA9IGZ1bmN0aW9uKCkge1xuICByZXR1cm4gdGhpcy5jZW50ZXJDb29yZGluYXRlcy5nZXRYKCk7XG59XG5Ob2RlLnByb3RvdHlwZS5nZXRDZW50ZXJDb29yZGluYXRlc1kgPSBmdW5jdGlvbigpIHtcbiAgcmV0dXJuIHRoaXMuY2VudGVyQ29vcmRpbmF0ZXMuZ2V0WSgpO1xufTtcblxuTm9kZS5wcm90b3R5cGUuZ2V0SGFsZkRpbWVuc2lvbiA9IGZ1bmN0aW9uKCkge1xuICByZXR1cm4gdGhpcy5oYWxmRGltZW5zaW9uO1xufTtcblxuTm9kZS5wcm90b3R5cGUuc2V0SGFsZkRpbWVuc2lvbiA9IGZ1bmN0aW9uKG5ld0hhbGZEaW1lbnNpb24pIHtcbiAgdGhpcy5oYWxmRGltZW5zaW9uID0gdmFsaWRhdGVQb2ludFZhbHVlKG5ld0hhbGZEaW1lbnNpb24pO1xufTtcblxuTm9kZS5wcm90b3R5cGUuZ2V0SGFsZkRpbWVuc2lvblggPSBmdW5jdGlvbigpIHtcbiAgcmV0dXJuIHRoaXMuaGFsZkRpbWVuc2lvbi5nZXRYKCk7XG59O1xuTm9kZS5wcm90b3R5cGUuZ2V0SGFsZkRpbWVuc2lvblkgPSBmdW5jdGlvbigpIHtcbiAgcmV0dXJuIHRoaXMuaGFsZkRpbWVuc2lvbi5nZXRZKCk7XG59O1xuXG5Ob2RlLnByb3RvdHlwZS5nZXRXaWR0aCA9IGZ1bmN0aW9uKCkge1xuICByZXR1cm4gdGhpcy5nZXRIYWxmRGltZW5zaW9uKCkuZ2V0WCgpIC0gdGhpcy5nZXRDZW50ZXJDb29yZGluYXRlcygpLmdldFgoKTtcbn07XG5cbk5vZGUucHJvdG90eXBlLmdldEhlaWdodCA9IGZ1bmN0aW9uKCkge1xuICByZXR1cm4gdGhpcy5nZXRIYWxmRGltZW5zaW9uKCkuZ2V0WSgpIC0gdGhpcy5nZXRDZW50ZXJDb29yZGluYXRlcygpLmdldFkoKTtcbn07XG5cbk5vZGUucHJvdG90eXBlLmdldFBvaW50cyA9IGZ1bmN0aW9uKCkge1xuICByZXR1cm4gdGhpcy5wb2ludHM7XG59O1xuXG5Ob2RlLnByb3RvdHlwZS5zZXRQb2ludHMgPSBmdW5jdGlvbihuZXdQb2ludHMsIGluZGV4KSB7XG4gIHRoaXMucG9pbnRzID0gdmFsaWRhdGVQb2ludHNUYWJsZVZhbHVlKG5ld1BvaW50cyk7XG59O1xuXG5Ob2RlLnByb3RvdHlwZS5lbXB0eVBvaW50cyA9IGZ1bmN0aW9uKCkge1xuICB0aGlzLnBvaW50cyA9IFtdO1xufTtcblxuTm9kZS5wcm90b3R5cGUuZ2V0TGVuZ3RoT2ZQb2ludHMgPSBmdW5jdGlvbigpIHtcbiAgcmV0dXJuIHRoaXMuZ2V0UG9pbnRzKCkubGVuZ3RoO1xufTtcblxuTm9kZS5wcm90b3R5cGUuaXNGdWxsT2ZQb2ludHMgPSBmdW5jdGlvbigpIHtcbiAgY29uc29sZS5sb2codGhpcy5nZXRMZW5ndGhPZlBvaW50cygpIDwgNCk7XG4gIHJldHVybiAhKHRoaXMuZ2V0TGVuZ3RoT2ZQb2ludHMoKSA8IDQpO1xufTtcblxuTm9kZS5wcm90b3R5cGUuaXNPdXRPZkhhbGZEaW1lbnNpb24gPSBmdW5jdGlvbihuZXdQb2ludCkge1xuICByZXR1cm4gKG5ld1BvaW50LmdldFgoKSA8IHRoaXMuZ2V0Q2VudGVyQ29vcmRpbmF0ZXNYKCkgfHwgwqBuZXdQb2ludC5nZXRZKCkgPCB0aGlzLmdldENlbnRlckNvb3JkaW5hdGVzWSgpIHx8IMKgbmV3UG9pbnQuZ2V0WCgpID4gdGhpcy5nZXRIYWxmRGltZW5zaW9uWCgpIHx8IMKgbmV3UG9pbnQuZ2V0WSgpID4gdGhpcy5nZXRIYWxmRGltZW5zaW9uWSgpKTtcbn07XG5cbk5vZGUucHJvdG90eXBlLmFkZFBvaW50VG9BbGxDaGlsZE5vZGVzID0gZnVuY3Rpb24ocG9pbnQpIHtcbiAgdGhpcy5nZXRDaGlsZE5vZGVzKCkuZm9yRWFjaChmdW5jdGlvbihjaGlsZE5vZGUsIGluZGV4KSB7XG4gICAgY2hpbGROb2RlLmFkZFBvaW50KHBvaW50KTtcbiAgfSk7XG59O1xuXG5Ob2RlLnByb3RvdHlwZS5hZGRQb2ludCA9IGZ1bmN0aW9uKG5ld1BvaW50KSB7XG4gIGlmICh0aGlzLmlzT3V0T2ZIYWxmRGltZW5zaW9uKG5ld1BvaW50KSkge1xuICAgIHJldHVybiBmYWxzZTtcbiAgfVxuICBpZiAodGhpcy5pc0Z1bGxPZlBvaW50cygpKSB7XG4gICAgaWYgKHRoaXMuaGFzTm9DaGlsZCgpKSB7XG4gICAgICB0aGlzLnNwbGl0SW5Gb3VyTm9kZXMoKTtcbiAgICB9XG4gICAgdGhpcy5hZGRQb2ludFRvQWxsQ2hpbGROb2RlcyhuZXdQb2ludCk7XG5cbiAgfSBlbHNlIHtcbiAgICB0aGlzLnBvaW50cy5wdXNoKHZhbGlkYXRlUG9pbnRWYWx1ZShuZXdQb2ludCkpO1xuICB9XG4gIHJldHVybiB0cnVlO1xufTtcblxuTm9kZS5wcm90b3R5cGUuc3BsaXRJbkZvdXJOb2RlcyA9IGZ1bmN0aW9uKCkge1xuICB0aGlzLmNyZWF0ZUZvdXJOb2RlcygpO1xuICB2YXIgX3RoaXMgPSB0aGlzO1xuICB0aGlzLmdldFBvaW50cygpLmZvckVhY2goZnVuY3Rpb24ocG9pbnQsIGluZGV4KSB7XG4gICAgX3RoaXMuYWRkUG9pbnRUb0FsbENoaWxkTm9kZXMocG9pbnQpO1xuICB9KTtcbiAgdGhpcy5lbXB0eVBvaW50cygpO1xufTtcblxuTm9kZS5wcm90b3R5cGUuY3JlYXRlRm91ck5vZGVzID0gZnVuY3Rpb24oKSB7XG4gIHZhciBoYWxmRGltZW5zaW9uWCA9IHRoaXMuZ2V0SGFsZkRpbWVuc2lvblgoKSxcbiAgICBoYWxmRGltZW5zaW9uWSA9IHRoaXMuZ2V0SGFsZkRpbWVuc2lvblkoKSxcbiAgICBjZW50ZXJDb29yZGluYXRlc1ggPSB0aGlzLmdldENlbnRlckNvb3JkaW5hdGVzWCgpLFxuICAgIGNlbnRlckNvb3JkaW5hdGVzWSA9IHRoaXMuZ2V0Q2VudGVyQ29vcmRpbmF0ZXNZKCksXG4gICAgbmV3Q2VudGVyQ29vcmRpbmF0ZXNYID0gaGFsZkRpbWVuc2lvblggLyAyLFxuICAgIG5ld0NlbnRlckNvb3JkaW5hdGVzWSA9IGhhbGZEaW1lbnNpb25ZIC8gMixcbiAgICBuZXdIYWxmRGltZW5zaW9uWCA9IChoYWxmRGltZW5zaW9uWCAtIGNlbnRlckNvb3JkaW5hdGVzWCkgLyAyLFxuICAgIG5ld0hhbGZEaW1lbnNpb25ZID0gKGhhbGZEaW1lbnNpb25ZIC0gY2VudGVyQ29vcmRpbmF0ZXNZKSAvIDIsXG4gICAgbmV3SGFsZkRpbWVuc2lvbiA9IG5ldyBDb29yZGluYXRlKG5ld0hhbGZEaW1lbnNpb25YLCBuZXdIYWxmRGltZW5zaW9uWSk7XG4gIHRoaXMuYWRkQ2hpbGROb2RlKG5ldyBOb2RlKHRoaXMuZ2V0Q2VudGVyQ29vcmRpbmF0ZXMoKSwgbmV3SGFsZkRpbWVuc2lvbikpO1xuICB0aGlzLmFkZENoaWxkTm9kZShuZXcgTm9kZShuZXcgQ29vcmRpbmF0ZShuZXdDZW50ZXJDb29yZGluYXRlc1gsIGNlbnRlckNvb3JkaW5hdGVzWSksIG5ldyBDb29yZGluYXRlKGhhbGZEaW1lbnNpb25YLCBuZXdIYWxmRGltZW5zaW9uWSkpKTtcbiAgdGhpcy5hZGRDaGlsZE5vZGUobmV3IE5vZGUobmV3IENvb3JkaW5hdGUoY2VudGVyQ29vcmRpbmF0ZXNYLCBuZXdDZW50ZXJDb29yZGluYXRlc1kpLCBuZXcgQ29vcmRpbmF0ZShuZXdIYWxmRGltZW5zaW9uWCwgaGFsZkRpbWVuc2lvblkpKSk7XG4gIHRoaXMuYWRkQ2hpbGROb2RlKG5ldyBOb2RlKG5ldyBDb29yZGluYXRlKG5ld0NlbnRlckNvb3JkaW5hdGVzWCwgY2VudGVyQ29vcmRpbmF0ZXNZKSwgdGhpcy5nZXRIYWxmRGltZW5zaW9uKCkpKTtcbn07XG5Ob2RlLnByb3RvdHlwZS5oYXNOb0NoaWxkID0gZnVuY3Rpb24oKSB7XG4gIHJldHVybiB0aGlzLmdldENoaWxkTm9kZXMoKS5sZW5ndGggPT09IDA7XG59O1xuXG5Ob2RlLnByb3RvdHlwZS5oYXNOb3RUb01hbnlDaGlsZCA9IGZ1bmN0aW9uICgpIHtcbiAgcmV0dXJuIHRoaXMuZ2V0Q2hpbGROb2RlcygpLmxlbmd0aCA8IDQ7XG59XG5Ob2RlLnByb3RvdHlwZS5nZXRDaGlsZE5vZGVzID0gZnVuY3Rpb24oKSB7XG4gIHJldHVybiB0aGlzLmNoaWxkTm9kZXM7XG59O1xuXG5Ob2RlLnByb3RvdHlwZS5zZXRDaGlsZE5vZGVzID0gZnVuY3Rpb24obmV3Q2hpbGROb2Rlcykge1xuICB0aGlzLmNoaWxkTm9kZXMgPSB2YWxpZGF0ZU5vZGVzVGFibGVWYWx1ZShuZXdDaGlsZE5vZGVzKTtcbn07XG5cbk5vZGUucHJvdG90eXBlLmFkZENoaWxkTm9kZSA9IGZ1bmN0aW9uKG5ld0NoaWxkTm9kZSkge1xuICBpZiAodGhpcy5oYXNOb3RUb01hbnlDaGlsZCgpKSB7XG4gICAgdGhpcy5jaGlsZE5vZGVzLnB1c2godmFsaWRhdGVOb2RlVmFsdWUobmV3Q2hpbGROb2RlKSk7XG4gIH0gZWxzZSB7XG4gICAgdGhyb3cgbmV3IEVycm9yKFwiTm9kZSBjYW4ndCBoYXZlIG1vcmUgdGhhbiA0IGNoaWxkXCIpO1xuICB9XG59O1xuXG5mdW5jdGlvbiB2YWxpZGF0ZVBvaW50VmFsdWUocG9pbnRUb1ZhbGlkYXRlKSB7XG4gIGlmICghKHBvaW50VG9WYWxpZGF0ZSBpbnN0YW5jZW9mIENvb3JkaW5hdGUpKSB7XG4gICAgdGhyb3cgbmV3IEVycm9yKFwiTXVzdCBiZSBhIENvb3JkaW5hdGVcIik7XG4gIH1cbiAgcmV0dXJuIHBvaW50VG9WYWxpZGF0ZTtcbn07XG5cbmZ1bmN0aW9uIHZhbGlkYXRlUG9pbnRzVGFibGVWYWx1ZShwb2ludHNUYWJsZVRvVmFsaWRhdGUpIHtcbiAgcG9pbnRzVGFibGVUb1ZhbGlkYXRlLmZvckVhY2goZnVuY3Rpb24ocG9pbnRUb1ZhbGlkYXRlLCBpbmRleCkge1xuICAgIHZhbGlkYXRlUG9pbnRWYWx1ZShwb2ludFRvVmFsaWRhdGUpO1xuICB9KTtcbiAgcmV0dXJuIHBvaW50c1RhYmxlVG9WYWxpZGF0ZTtcbn07XG5cbmZ1bmN0aW9uIHZhbGlkYXRlSGFsZkRpbWVuc2lvblZhbHVlKGNlbnRlckNvb3JkaW5hdGVzVG9Db21wYXJlV2l0aCwgaGFsZkRpbWVuc2lvblRvVmFsaWRhdGUpIHtcbiAgaGFsZkRpbWVuc2lvblRvVmFsaWRhdGUgPSB2YWxpZGF0ZVBvaW50VmFsdWUoaGFsZkRpbWVuc2lvblRvVmFsaWRhdGUpO1xuICBpZiAoY2VudGVyQ29vcmRpbmF0ZXNUb0NvbXBhcmVXaXRoLnggPT09IGhhbGZEaW1lbnNpb25Ub1ZhbGlkYXRlLnggfHwgY2VudGVyQ29vcmRpbmF0ZXNUb0NvbXBhcmVXaXRoLnkgPT09IGhhbGZEaW1lbnNpb25Ub1ZhbGlkYXRlLnkpIHtcbiAgICB0aHJvdyBuZXcgRXJyb3IoXCJjZW50ZXJDb29yZGluYXRlcyBtdXN0IGJlIGRpZmZlcmVudCBmcm9tIGhhbGZEaW1lbnNpb25cIik7XG4gIH1cbiAgcmV0dXJuIGhhbGZEaW1lbnNpb25Ub1ZhbGlkYXRlO1xufTtcblxuZnVuY3Rpb24gdmFsaWRhdGVOb2Rlc1RhYmxlVmFsdWUobm9kZXNUYWJsZVRvVmFsaWRhdGUpIHtcbiAgbm9kZXNUYWJsZVRvVmFsaWRhdGUuZm9yRWFjaChmdW5jdGlvbihub2RlVG9WYWxpZGF0ZSwgaW5kZXgpIHtcbiAgICB2YWxpZGF0ZU5vZGVWYWx1ZShub2RlVG9WYWxpZGF0ZSk7XG4gIH0pO1xuICByZXR1cm4gbm9kZXNUYWJsZVRvVmFsaWRhdGU7XG59O1xuXG5mdW5jdGlvbiB2YWxpZGF0ZU5vZGVWYWx1ZShub2RlVG9WYWxpZGF0ZSkge1xuICBpZiAoIShub2RlVG9WYWxpZGF0ZSBpbnN0YW5jZW9mIE5vZGUpKSB7XG4gICAgdGhyb3cgbmV3IEVycm9yKFwiTXVzdCBiZSBhIE5vZGVcIik7XG4gIH1cbiAgcmV0dXJuIG5vZGVUb1ZhbGlkYXRlO1xufTtcbm1vZHVsZS5leHBvcnRzID0gTm9kZTtcbiIsIid1c2Ugc3RyaWN0J1xudmFyIE5vZGUgPSByZXF1aXJlKCcuL25vZGUnKSxcbiAgQ29vcmRpbmF0ZSA9IHJlcXVpcmUoJy4vY29vcmRpbmF0ZScpO1xuXG5mdW5jdGlvbiBRdWFkdHJlZShoYWxmRGltZW5zaW9uWCwgaGFsZkRpbWVuc2lvblkpIHtcbiAgdGhpcy5vcmlnaW5Ob2RlID0gbmV3IE5vZGUobmV3IENvb3JkaW5hdGUoMCwgMCksIG5ldyBDb29yZGluYXRlKGhhbGZEaW1lbnNpb25YLCBoYWxmRGltZW5zaW9uWSkpO1xuICB0aGlzLnBvaW50cyA9IFtdO1xufTtcblxuUXVhZHRyZWUucHJvdG90eXBlLmdldFBvaW50cyA9IGZ1bmN0aW9uICgpIHtcbiAgcmV0dXJuIHRoaXMucG9pbnRzO1xufTtcblF1YWR0cmVlLnByb3RvdHlwZS5nZXRPcmlnaW5Ob2RlID0gZnVuY3Rpb24gKCkge1xuICByZXR1cm4gdGhpcy5vcmlnaW5Ob2RlO1xufTtcblxuUXVhZHRyZWUucHJvdG90eXBlLmFkZFBvaW50ID0gZnVuY3Rpb24gKG5ld1BvaW50KSB7XG4gIGlmICh0aGlzLm9yaWdpbk5vZGUuYWRkUG9pbnQobmV3UG9pbnQpKXtcbiAgICB0aGlzLnBvaW50cy5wdXNoKG5ld1BvaW50KTtcbiAgfVxufTtcbm1vZHVsZS5leHBvcnRzID0gUXVhZHRyZWU7XG4iXX0=
