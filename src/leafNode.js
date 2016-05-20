'use strict';

const TreeNode = require('./treeNode');

class LeafNode extends TreeNode {
  constructor(x,y) {
    super(x,y);
  }
}

module.exports = LeafNode;
