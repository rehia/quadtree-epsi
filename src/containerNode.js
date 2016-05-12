'use strict';

const TreeNode = require('./treeNode');

class ContainerNode extends TreeNode {
  constructor() {
    super();
    this.children = [];
  }

  addNode(node) {
    node.setParent(this);
    this.children.push(node);
  }

  getChildren() {
    return this.children;
  }
}

module.exports = ContainerNode;
