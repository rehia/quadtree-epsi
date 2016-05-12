'use strict';

const TreeNode = require('./treeNode');

const _addContainer = Symbol('_addContainer');
const _pushNode = Symbol('_pushNode');

class ContainerNode extends TreeNode {
  constructor() {
    super();
    this.children = [];
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

  [_addContainer]() {
    let container = new ContainerNode();
    container.setParent(this);
    this[_pushNode](container);
  }

  [_pushNode] (node) {
    this.children.push(node);
  }
}


module.exports = ContainerNode;
