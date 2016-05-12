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
  let line = 0;
  while(nbPoints < 50) {
    let column = 0;
    while(nbPoints < 50 && column < 100) {
      let rand = Math.floor((Math.random() * 500) + 1);
      if(nbPoints < 50 && resultGrid[line][column] === null && rand < 3) {
        resultGrid[line][column] = 'point';
        nbPoints++;
      }
      column++;
    }
    line++;
    if(line >= 100 && nbPoints < 50) {
      line = 0;
    }
  }
  return resultGrid;
};
