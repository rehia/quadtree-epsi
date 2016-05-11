'use strict';

module.exports.createGrid = function() {
  let grid = [];
  for(let line = 0; line < 100; line++) {
    grid[line] = [];
    for(let column = 0; column < 100; column++) {
      grid[line][column] = null;
    }
  }
  return grid;
};

module.exports.fillGrid = function (grid) {
  let nbPoints = 0;
  let resultGrid = grid;
  resultGrid.forEach(function (line){
    line.forEach(function(column) {
      if(nbPoints < 50 && Math.floor((Math.random() * 10) + 1) < 7) {
        column = 'point';
      }
    });
  });
  return resultGrid;
};
