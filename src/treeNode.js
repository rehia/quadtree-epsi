'use strict';

class TreeNode {
  constructor() {
    this.parent = null;
    this.x = 0;
    this.y = 0;
  }

  getParent () {
    return this.parent;
  }

  setParent (parent) {
    this.parent = parent;
  }

  isRoot() {
    return this.parent === null;
  }

  getX() {
    return this.x;
  }

  getY() {
    return this.y;
  }
}

module.exports = TreeNode;
