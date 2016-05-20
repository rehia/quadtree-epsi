'use strict';

const ContainerNode = require('./containerNode');

module.exports.getDeepnessOf = getDeepnessOf;
module.exports.getFriendOf = getFriendOf;

function getDeepnessOf(point, node, rank = 0) {
  let result = -1;
  let it = 0;
  let children = node.getChildren();

  while(result === -1 && it < children.length) {
    let subNode = children[it];
    if(subNode instanceof ContainerNode){
      result = getDeepnessOf(point, subNode, rank+1);
    } else {
      if (subNode.getX() === point.x && subNode.getY() === point.y) {
        result = rank;
      }
    }
    it++;
  }
  return result;
}

function getFriendOf(point, node) {
  let result = [];
  let it = 0;
  let children = node.getChildren();

  while(result.length === 0 && it < children.length) {
    let subNode = children[it];
    if(subNode instanceof ContainerNode){
      result = getFriendOf(point, subNode);
    } else {
      if (subNode.getX() === point.x && subNode.getY() === point.y) {
        result = addFriends(point, node);
      }
    }
    it++;
  }
  return result;
}

function addFriends(point, node) {
  let result = [];
  node.getChildren().forEach(function(child) {
    if(child instanceof ContainerNode) {
      result = result.concat(addFriends(point, child));
    } else {
      if(child.getX() !== point.x || child.getY() !== point.y) {
        result.push(child);
      }
    }
  });
  return result;
}
