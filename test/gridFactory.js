'use strict';

const expect = require('chai').expect;

const gridFactory = require('../src/gridFactory');

describe('Test of the grid factory', function () {
  describe('Test of the grid generation', function() {

    it('Should generate a grid of 100x100', function(done){
      let grid = gridFactory.createGrid();

      expect(grid).to.be.a('Array');
      expect(grid).to.have.lengthOf(100);
      grid.forEach(function(line) {
        expect(line).to.be.a('Array');
        expect(line).to.have.lengthOf(100);
      });
      done();
    });

  });
});
