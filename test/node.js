'use strict'
var Node = require('../prototypes/node'),
  Coordinate = require('../prototypes/coordinate'),
  expect = require('chai').expect;
var test = describe('node', function() {
  var testNode = new Node(new Coordinate(0, 0), new Coordinate(1, 1), 'NE');
  it('should throw error when centerCoordinates value is not construct with an instance of Coordinate', function() {
    expect(function() {
      new Node(0, new Coordinate(0, 0));
    }).to.throw("Must be a Coordinate");
  });
  it('its attribute points should be a empty table', function() {
    expect(testNode.getPoints()).to.be.an('array');
    expect(testNode.getPoints().length).to.equals(0);
  });
  it('should throw error when centerCoordinates value is not set with an instance of Coordinate', function() {
    expect(function() {
      testNode.setCenterCoordinates(0);
    }).to.throw("Must be a Coordinate");
  });
  it('should throw error when halfDimension value is not construct with an instance of Coordinate', function() {
    expect(function() {
      new Node(new Coordinate(0, 0), 0);
    }).to.throw("Must be a Coordinate");
  });
  it('should throw error when halfDimension value is not set with an instance of Coordinate', function() {
    expect(function() {
      testNode.setHalfDimension(0);
    }).to.throw("Must be a Coordinate");
  });
  it('should throw error because halfDimension muste be different from centerCoordinates', function () {
    expect(function () {
      new Node(new Coordinate(0, 0), new Coordinate(0, 0));
    }).to.throw("centerCoordinates must be different from halfDimension");
  });
  it('set points with a tab with a value which is not an insctance of Coordinate should throw an error', function() {
    expect(function() {
      testNode.setPoints([0, 1]);
    }).to.throw("Must be a Coordinate");
  });
  it('set points with a tab of Coordinate should not throw error', function() {
    expect(function() {
      testNode.setPoints([new Point(0, 0), new Point(1, 2)]);
    }).to.not.throw("Must be a Coordinate");
  });
  it('set zone with an other value than one of the enumerator Zone should throw error', function() {
    expect(function() {
      testNode.setZone('test');
    }).to.throw("Must be a value from the enumerator Zone");
  });
  it('set zone with an other value than one of the enumerator Zone should throw error', function() {
    expect(function() {
      testNode.setZone('NE');
    }).to.not.throw("Must be a value from the enumerator Zone");
  });
  it('set nodesChild with a tab with a value which is not an instance of Node should throw an error', function() {
    expect(function() {
      testNode.setChildNodes([0, 2]);
    }).to.throw('Must be a Node');
  });
  it('set nodesChild with a tab with a value which is an instance of Node should not throw an error', function() {
    expect(function() {
      testNode.setChildNodes([new Node(new Coordinate(0, 0), new Coordinate(0, 0))]);
    }).to.not.throw('Must be a Node');
  });
});
module.exports = test;
