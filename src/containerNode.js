'use strict';

const TreeNode = require('./treeNode');

const _addContainer = Symbol('_addContainer');
const _pushNode = Symbol('_pushNode');

class ContainerNode extends TreeNode {
  constructor(x,y) {
    super(x,y);
    this.children = [];
    this.size = 0;
  }

  addNode(node) {
    node.setParent(this);
    if(this.children.length === 4) {
      this[_addContainer]();
    } else {
      this[_pushNode](node);
    }
  }

  getChildren() {
    return this.children;
  }

  getSize() {
    return this.size;
  }

  setSize(size) {
    this.size = size;
  }

  [_addContainer] () {
    for(let i=0; i < 4; i++) {
      let container = new ContainerNode();
      container.setSize(this.size/4);
      container.setParent(this);
      this[_pushNode](container);
    }
  }

  [_pushNode] (node) {
    this.children.push(node);
  }

}


module.exports = ContainerNode;
