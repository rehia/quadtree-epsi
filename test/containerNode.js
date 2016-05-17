'use strict';

const expect = require('chai').expect;

const ContainerNode = require('./../src/containerNode');
const LeafNode = require('./../src/leafNode');

describe('Test of treeNode', function() {
  describe('Test adding point without creating conatiner', function() {

    let rootNode;

    beforeEach(function() {
      rootNode = new ContainerNode();
    });

    it('Should add a point single point to a node', function(done){
      rootNode.addNode(new LeafNode(1,2));
      rootNode.getChildren().forEach(function(child) {
        expect(child).to.be.an.instanceof(LeafNode);
      });
      expect(rootNode.getChildren()).to.have.lengthOf(1);
      done();
    });

    it('Should be the parent of a the added point', function(done){
      rootNode.addNode(new LeafNode(1,2));
      let leaf = rootNode.getChildren()[0];
      expect(leaf.getParent()).to.equal(rootNode);
      done();
    });

  });

  describe('Test of container creation', function() {

    let rootNode = new ContainerNode(0,0);
    rootNode.setSideSize(10);
    for(let i = 0; i<5; i++) {
      rootNode.addNode(new LeafNode(i, i+3));
    }

    it('Should create 4 containers', function(done){
      let counter = 0;
      rootNode.getChildren().forEach(function(child) {
        if(child instanceof ContainerNode) {
          counter++;
        }
      });
      expect(counter).to.equal(4);
      done();
    });

    rootNode.getChildren().forEach(function(child) {
      it('Should have side half the sideSideSize of it\'s parent one', function (done){
        if(child instanceof ContainerNode) {
          expect(child.getSideSize()).to.be.a('Number');
          expect(child.getSideSize()).to.not.equal(0);
          expect(child.getSideSize()).to.equal(rootNode.getSideSize()/2);
        }
        done();
      });

      it('Sub container should have rootNode\'s X coordinate or X + rootNode\'s sideSideSize divided by 2', function(done) {
        if(child instanceof ContainerNode) {
          expect(child.getX())
            .to.be.oneOf([rootNode.getX(), rootNode.getX() + Math.floor(rootNode.getSideSize()/2) - 1]);
        }
        done();
      });

      it('Sub container should have rootNode\'s Y coordinate or Y + rootNode\'s sideSideSize divided by 2', function(done) {
        if(child instanceof ContainerNode) {
          expect(child.getY())
            .to.be.oneOf([rootNode.getY(), rootNode.getY() + Math.floor(rootNode.getSideSize()/2) -1]);
        }
        done();
      });

    });
  });
});
