'use strict';

const vorpal = require('vorpal')();

const GridFactory = require('./gridFactory');
const ContainerNode = require('./containerNode');
const LeafNode = require('./leafNode');
const treeParser = require('./treeParser');

let grid = GridFactory.fillGrid(GridFactory.createGrid());
let tree;

function printGrid() {
  let result;
  grid.forEach(function(line) {
    line.forEach(function(column) {
      result += column === null ? '-' : '+';
    });
    result += '\n';
  });
  console.log(result);
}

function printPoints() {
  let result;
  for(let x = 0; x < grid.length; x++) {
    for(let y = 0; y < grid[x].length; y++) {
      if(grid[x][y] !== null) {
        result += 'Point(' + x + ';' + y +  ')\n';
      }
    }
  }
  console.log(result);
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

function pointExist(x, y) {
  return grid[x][y] !== null;
}

function printFriendsList(point, friends){
  let result = 'Point(' + point.x + ';' + point.y  + ') has ' + friends.length + ' friends:\n';
  friends.forEach(function(friend) {
    result += '-> Point(' + friend.x + ';' +friend.y + ')\n';
  });
  console.log(result);
}

genTree();

//userChoice = prompt('Enter a number: \n 1) print grid \n 2) get deepness of a point \n 3) get friends of a point \n 0) to quit \n');
vorpal
  .command('print', 'Print the grid')
  .action(function(args, callback) {
    printGrid();
    callback();
  });

vorpal
  .command('coords', 'Print all coord in the scheme')
  .action(function(args, callback) {
    printPoints();
    callback();
  });

vorpal
  .command('deep <x> <y>', 'Get the deepness of a given point in the tree')
  .action(function (args, callback) {
    console.log(treeParser.getDeepnessOf({x: args.x, y: args.y}, tree));
    callback();
  });

vorpal
  .command('friends <x> <y>', 'Get friends of a given point')
  .action(function(args, callback) {
    let point = {x: args.x, y: args.y};
    let result = treeParser.getFriendOf(point, tree);
    printFriendsList(point, result);
    callback();
  });

vorpal
  .delimiter('Quadtree-cli$')
  .show();
