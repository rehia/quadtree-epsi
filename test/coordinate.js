var Coordinate = require('../prototypes/coordinate'),
  expect = require('chai').expect;
var test = describe('coordinate', function() {
  var coordinateTest = new Coordinate(2, 3);
  it('point construct with empty constructor should throw error', function() {
    expect(function() {
      new Coordinate();
    }).to.throw("Coordinate coordinate must be a number");
  });
  it('point have x property', function() {
    expect(coordinateTest).to.have.property('x');
  });
  it('point have y property', function() {
    expect(coordinateTest).to.have.property('y');
  });
  it('point(2, 3) should have x equals 2', function() {
    expect(coordinateTest.getX()).to.equal(2);
  });
  it('point(2, 3) should have x not equals 3', function() {
    expect(coordinateTest.getX()).to.not.equal(3);
  });
  it('point(2, 3) should have y not equals 2', function() {
    expect(coordinateTest.getY()).to.not.equal(2);
  });
  it('point construct with negative parameter x should throw error', function() {
    expect(function() {
      new Coordinate(-1, 0);
    }).to.throw("Coordinate coordinate must be grater than zero");
  });
  it('point construct with negative parameter y should throw error', function() {
    expect(function() {
      new Coordinate(0, -1);
    }).to.throw("Coordinate coordinate must be grater than zero");
  });
  it('point updated with negative parameter y should throw error', function() {
    expect(function() {
      coordinateTest.setY(-1)
    }).to.throw("Coordinate coordinate must be grater than zero");
  });
  it('point updated with negative parameter x should throw error', function() {
    expect(function() {
      coordinateTest.setX(-1)
    }).to.throw("Coordinate coordinate must be grater than zero");
  });
  it('point updated with x undefined value should throw error', function() {
    expect(function() {
      coordinateTest.setX()
    }).to.throw("Coordinate coordinate must be a number");
  });
  it('point updated with y undefined value should throw error', function() {
    expect(function() {
      coordinateTest.setY()
    }).to.throw("Coordinate coordinate must be a number");
  });
});
module.exports = test;
