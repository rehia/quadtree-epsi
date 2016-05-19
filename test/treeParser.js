'use strict';

const expect = require('chai').expect;

const treeParser = require('./../src/treeParser');
const ContainerNode = require('./../src/containerNode');
const LeafNode = require('./../src/leafNode');

describe('Test of tree parser', function() {

  let root = new ContainerNode(0,0);
  root.setSideSize(11);
  root.addNode(new LeafNode(0,3));
  root.addNode(new LeafNode(1,1));
  root.addNode(new LeafNode(3,2));
  root.addNode(new LeafNode(2,7));
  root.addNode(new LeafNode(4,5));
  root.addNode(new LeafNode(8,4));

  describe('Test the deepness of a given point', function() {

    it('Should return 1 for the point (0,3)', function(done) {
      let rank = treeParser.getDeepNessOf({x:0, y:3}, root);
      expect(rank).to.be.a('Number');
      expect(rank).to.equal(1);
    });
  });

  describe('Test getting friend of a given point', function() {

  });

});
