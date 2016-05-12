var Point = require('../prototypes/point'),
  expect = require('chai').expect;
var test = describe('point', function() {
  var pointTest = new Point(2, 3);
  it('point construct with empty constructor should throw error', function() {
    expect(function() {
      new Point();
    }).to.throw("Point can't be construct with no values");
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
    }).to.throw("Point can't be construct with negative coordinate");
  });
  it('point construct with negative parameter y', function() {
    expect(function() {
      new Point(0, -1);
    }).to.throw("Point can't be construct with negative coordinate");
  });
});
module.exports = test;
