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

  describe('Test the filling of a grid with random point', function () {

    it('Should add 50 points', function(done) {
      let grid = gridFactory.createGrid();
      grid = gridFactory.fillGrid(grid);
      let nbPoints = 0;
      grid.forEach(function(line) {
        line.forEach(function(column) {
          if(column === 'point') {
            nbPoints++;
          }
        });
      });
      expect(nbPoints).to.equal(50);
      done();
    });

    it('Should add ramdom point', function(done) {
      let grid = gridFactory.createGrid();
      grid = gridFactory.fillGrid(grid);
      let pointFound = false;
      let nullAfterPointFound = false;
      let pointAfterNullFound = false;
      let line = 0;
      while(!pointAfterNullFound && line < grid.length){
        let column = 0;
        while(!pointAfterNullFound && column < grid[line].length){
          let pointValue = grid[line][column];
          if(!pointFound && pointValue === 'point') {
            pointFound = true;
          } else if (!nullAfterPointFound && pointFound && pointValue === null) {
            nullAfterPointFound = true;
          } else if (nullAfterPointFound && pointValue === 'point') {
            pointAfterNullFound = true;
          }
          column++;
        }
        line++;
      }
      expect(pointFound).to.be.true;
      expect(nullAfterPointFound).true;
      expect(pointAfterNullFound).to.be.true;
      done();
    });
  });
});
