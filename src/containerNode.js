'use strict';

const TreeNode = require('./treeNode');

const _divideInFourContainer = Symbol('_divideInFourContainer');
const _calculateFutureCoordonate = Symbol('_calculateFutureCoordonate');
const _addTheFourContainerNode = Symbol('_addTheFourContainerNode');
const _createContainerNode = Symbol('_createContainerNode');
const _dispatchLeaf = Symbol('_dispatchLeaf');
const _dispatchLeafInSubContainer = Symbol('_dispatchLeafInSubContainer');
const _pushNode = Symbol('_pushNode');

class ContainerNode extends TreeNode {
  constructor(x,y) {
    super(x,y);
    this.children = [];
    this.divided = false;
    this.sideSize = 0;
  }

  addNode(node) {
    if(this.children.length === 4 && this.divided === false) {
      this[_divideInFourContainer]();
    }
    this[_dispatchLeaf](node);
  }

  getChildren() {
    return this.children;
  }

  getSideSize() {
    return this.sideSize;
  }

  setSideSize(sideSize) {
    this.sideSize = sideSize;
  }

  couldContainsLeaf(leaf) {
    let lX = leaf.getX();
    let lY = leaf.getY();
    let xOk = lX >= this.x && lX < this.x + this.sideSize;
    let yOk = lY >= this.y && lY < this.y + this.sideSize;
    return xOk && yOk;
  }

  [_divideInFourContainer](){
    this.divided = true;
    let leafs = this.children;
    this[_addTheFourContainerNode]();
    for(let i=0; i<leafs.length; i++) {
      this[_dispatchLeaf](leafs[i]);
    }
  }

  [_addTheFourContainerNode]() {
    this.children = [];
    let sideSize = Math.ceil(this.sideSize / 2);
    for(let x=0; x < 2; x++) {
      for(let y=0; y < 2; y++) {
        let futureX = this[_calculateFutureCoordonate](this.x, x===0 ? 1:sideSize);
        let futureY = this[_calculateFutureCoordonate](this.y, y===0 ? 1:sideSize);
        let container = this[_createContainerNode](futureX, futureY, sideSize);
        this[_pushNode](container);
      }
    }
  }

  [_createContainerNode](x, y, sideSize) {
    let container = new ContainerNode(x,y);
    container.setSideSize(sideSize);
    container.setParent(this);
    return container;
  }

  [_calculateFutureCoordonate](baseCoord, offset = 1) {
    return baseCoord + offset -1;
  }

  [_dispatchLeaf] (leaf) {
    if(this.divided === true) {
      this[_dispatchLeafInSubContainer](leaf);
    } else {
      this[_pushNode](leaf);
    }
  }

  [_dispatchLeafInSubContainer](leaf) {
    let containers = [];
    this.children.forEach(function(child) {
      if(child instanceof ContainerNode) {
        if(child.couldContainsLeaf(leaf)){
          containers.push(child);
        }
      }
    });
    if(containers.length === 1) {
      containers[0].addNode(leaf);
    } else {
      this[_pushNode](leaf);
    }
  }

  [_pushNode] (node) {
    node.setParent(this);
    this.children.push(node);
  }
}


module.exports = ContainerNode;
