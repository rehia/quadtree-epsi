'use strict'
var Node = require('../prototypes/node'),
  Coordinate = require('../prototypes/coordinate'),
  expect = require('chai').expect;
var test = describe('node', function() {
  var testNode = new Node(new Coordinate(0, 0), new Coordinate(1, 1));
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
  it('should throw error because halfDimension muste be different from centerCoordinates', function() {
    expect(function() {
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
  it('Children length must not be grater than 4 to push a new point', function() {
    var dumpNode = new Node(new Coordinate(0, 0), new Coordinate(100, 100));

    for (var i = 0; i < 4; i++) {
      dumpNode.addChildNode(new Node(new Coordinate(i, i), new Coordinate(i + 1, i + 1)));
    }
    dumpNode.addChildNode(new Node(new Coordinate(i, i), new Coordinate(i + 1, i + 1)));
    expect(dumpNode.getChildNodes()).to.have.length.below(5);
  });
  it('Points length must not be grater than 4 to push a new point', function() {
    var dumpNode = new Node(new Coordinate(0, 0), new Coordinate(100, 100));
    for (var i = 0; i < 5; i++) {
      dumpNode.addPoint(new Coordinate(i, i));
    }
    expect(dumpNode.getPoints()).to.have.length.below(5);
  });
  it('If a Node has already 4 point the new Point should be added to Children', function () {
    var dumpNode = new Node(new Coordinate(0, 0), new Coordinate(100, 100));
    for (var i = 0; i < 4; i++) {
      dumpNode.addPoint(new Coordinate(i, i));
    }
    dumpNode.addPoint(new Coordinate(51, 51));
    var result;
    dumpNode.getChildNodes().forEach(function (child, index) {
      console.log(child)
      if (child.points.length > 0) {
        result = true;
      }
    });
    expect(result).to.be.true;
  })
  it('Adding a Coordinate to the Points list should throw an error if the Coordinate is out of the current node', function() {
    var dumpNode = new Node(new Coordinate(0, 0), new Coordinate(100, 100));
    expect(dumpNode.addPoint(new Coordinate(101, 101))).to.be.false;
  });
  it('Node should splite itself when add point and if it has 4 points', function () {
      var dumpNode = new Node(new Coordinate(0, 0), new Coordinate(100, 100));
      for (var i = 0; i < 4; i++) {
        dumpNode.addPoint(new Coordinate(i, i));
      }
      dumpNode.addPoint(new Coordinate(40, 39));
      expect(dumpNode.getPoints().length).to.equals(0);
  });
});
module.exports = test;
