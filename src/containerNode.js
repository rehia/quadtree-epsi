'use strict';

const TreeNode = require('./treeNode');

class ContainerNode extends TreeNode {
  constructor() {
    super();
    this.children = [];
  }

  addNode(node) {
    this.children.push(node);
  }

  getChildren() {
    return this.children;
  }
}

module.exports = ContainerNode;
