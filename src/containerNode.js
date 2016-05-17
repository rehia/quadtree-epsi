'use strict';

const TreeNode = require('./treeNode');

const _divideInFourContainer = Symbol('_divideInFourContainer');
const _calculateFutureCoordonate = Symbol('_calculateFutureCoordonate');
const _addTheFourContainerNode = Symbol('_addTheFourContainerNode');
const _createContainerNode = Symbol('_createContainerNode');
const _pushNode = Symbol('_pushNode');

class ContainerNode extends TreeNode {
  constructor(x,y) {
    super(x,y);
    this.children = [];
    this.sideSize = 0;
  }

  addNode(node) {
    node.setParent(this);
    if(this.children.length === 4) {
      this[_divideInFourContainer]();
    } else {
      this[_pushNode](node);
    }
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

  [_divideInFourContainer](){
    this[_addTheFourContainerNode]();
  }

  [_addTheFourContainerNode]() {
    let sideSize = this.sideSize / 2;
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

  [_pushNode] (node) {
    this.children.push(node);
  }
}


module.exports = ContainerNode;
