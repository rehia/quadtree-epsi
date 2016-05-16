'use strict';

class TreeNode {
  constructor(x,y) {
    this.parent = null;
    this.x = x;
    this.y = y;
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
