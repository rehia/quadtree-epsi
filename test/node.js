var Node = require('../prototypes/node'),
  Coordinate = require('../prototypes/coordinate'),
  expect = require('chai').expect;
var test = describe('node', function() {
  var testNode = new Node(new Coordinate(0,0), new Coordinate(0, 0));
  it('should throw error when centerCoordinates value is not construct with an instance of Coordinate', function() {
    expect(function() {
      new Node(0, new Coordinate(0, 0));
    }).to.throw("Node must have Coordinate as attribute value");
  });
  it('should throw error when centerCoordinates value is not set with an instance of Coordinate', function () {
    expect(function () {
      testNode.setCenterCoordinates(0);
    }).to.throw("Node must have Coordinate as attribute value");
  });
  it('should throw error when halfDimension value is not construct with an instance of Coordinate', function() {
    expect(function() {
      new Node(new Coordinate(0, 0), 0);
    }).to.throw("Node must have Coordinate as attribute value");
  });
  it('should throw error when halfDimension value is not set with an instance of Coordinate', function () {
    expect(function () {
      testNode.setHalfDimension(0);
    }).to.throw("Node must have Coordinate as attribute value");
  });
});
module.exports = test;
