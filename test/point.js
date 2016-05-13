var Point = require('../prototypes/point'),
  expect = require('chai').expect;
var test = describe('point', function() {
  var pointTest = new Point(2, 3);
  it('point construct with empty constructor should throw error', function() {
    expect(function() {
      new Point();
    }).to.throw("Point coordinate must be a number");
  });
  it('point have x property', function() {
    expect(pointTest).to.have.property('x');
  });
  it('point have y property', function() {
    expect(pointTest).to.have.property('y');
  });
  it('point(2, 3) should have x equals 2', function() {
    expect(pointTest.getX()).to.equal(2);
  });
  it('point(2, 3) should have x not equals 3', function() {
    expect(pointTest.getX()).to.not.equal(3);
  });
  it('point(2, 3) should have y not equals 2', function() {
    expect(pointTest.getY()).to.not.equal(2);
  });
  it('point construct with negative parameter x should throw error', function() {
    expect(function() {
      new Point(-1, 0);
    }).to.throw("Point coordinate must be grater than zero");
  });
  it('point construct with negative parameter y should throw error', function() {
    expect(function() {
      new Point(0, -1);
    }).to.throw("Point coordinate must be grater than zero");
  });
  it('point updated with negative parameter y should throw error', function() {
    expect(function() {
      pointTest.setY(-1)
    }).to.throw("Point coordinate must be grater than zero");
  });
  it('point updated with negative parameter x should throw error', function() {
    expect(function() {
      pointTest.setX(-1)
    }).to.throw("Point coordinate must be grater than zero");
  });
  it('point updated with x undefined value should throw error', function() {
    expect(function() {
      pointTest.setX()
    }).to.throw("Point coordinate must be a number");
  });
  it('point updated with y undefined value should throw error', function() {
    expect(function() {
      pointTest.setY()
    }).to.throw("Point coordinate must be a number");
  });
});
module.exports = test;
