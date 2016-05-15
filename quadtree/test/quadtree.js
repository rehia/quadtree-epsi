'use strict'
var Node = require('../prototypes/node'),
  Coordinate = require('../prototypes/coordinate'),
  Quadtree = require('../prototypes/quadtree'),
  expect = require('chai').expect;
var test = describe('quadtree', function() {
  var dumpQuadtree = new Quadtree(100, 100);
  it('A quadtree should have a originNode', function() {
    expect(dumpQuadtree.getOriginNode()).to.be.a('object');
  });
  it('its attribute points should be a empty table', function() {
    expect(dumpQuadtree.getPoints()).to.be.an('array');
    expect(dumpQuadtree.getPoints().length).to.equals(0);
  });
  it('should toString the quadtree', function () {
    var dumpRandom = function () {
      return Math.floor(Math.random()*(100-0+1)+0);
    };
    var index = 0;
    var execute = function () {
      console.log(index);
      if (index < 50) {
          dumpQuadtree.addPoint(new Coordinate(dumpRandom(), dumpRandom()));
          index++;
      } else {
        dumpQuadtree.toString();
        clearInterval(this);
      }
    };
    setInterval(execute, 100);
  });
});
module.exports = test;
