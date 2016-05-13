var Node = require('../prototypes/node'),
  Coordinate = require('../prototypes/coordinate'),
  expect = require('chai').expect;
var test = describe('node', function() {
  var testNode = new Node(new Coordinate(0,0), new Coordinate(0, 0));
  it('should be passing', function() {
    expect(true).to.be.true;
  });
});
module.exports = test;
