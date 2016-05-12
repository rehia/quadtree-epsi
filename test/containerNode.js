'use strict';

const expect = require('chai').expect;

const ContainerNode = require('./../src/containerNode');
const LeafNode = require('./../src/leafNode');

describe('Test of treeNode', function() {
  describe('Test of add point', function() {

    let rootNode;

    beforeEach(function() {
      rootNode = new ContainerNode();
      rootNode.addNode(new LeafNode(22,33));
      rootNode.addNode(new LeafNode(33,22));
    });

    it('Should add a point single point', function(done){
      rootNode.addNode(new LeafNode(1,2));
      rootNode.getChildren().forEach(function(child) {
        expect(child).to.be.an.instanceof(LeafNode);
      });
      expect(rootNode.getChildren()).to.have.lengthOf(3);
      done();
    });

  });
});
