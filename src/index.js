'use strict';

const readline = require('readline');

const GridFactory = require('./gridFactory');
const ContainerNode = require('./containerNode');
const LeafNode = require('./leafNode');

let grid = GridFactory.fillGrid(GridFactory.createGrid());
let tree;

function printGrid() {
  grid.forEach(function(line) {
    line.forEach(function(column) {
      console.log(column === null ? '-' : '+');
    });
  });
}

function genTree() {
  tree = new ContainerNode(0, 0);
  tree.setSideSize(grid.length);
  for (let x = 0; x < grid.length; x++) {
    for (let y = 0; y < grid.length; y++) {
      if (grid[x][y] !== null) {
        tree.addNode(new LeafNode(x, y));
      }
    }
  }
}

function getDeepnessOfPoint(x, y, rank = 0, node) {
  node.getChildren().forEach(function(child) {
    if (child instanceof ContainerNode) {
      let result = getDeepnessOfPoint(x, y, rank + 1, child);
      if (result !== false) {
        return result;
      }
    } else {
      if (child.getX() === x && child.getY() === y) {
        return rank;
      }
    }
  });
}

function pointExist(x, y) {
  return grid[x][y] !== null;
}

genTree();

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});
