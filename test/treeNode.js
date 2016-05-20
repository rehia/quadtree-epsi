'use strict';

const expect = require('chai').expect;

const TreeNode = require('./../src/treeNode');
const ContainerNode = require('./../src/containerNode');
const LeafNode = require('./../src/leafNode');

describe('Test of TreeNode ', function(){
  let testElems = [
    new ContainerNode(0,14),
    new LeafNode(1,17)
  ];

  testElems.forEach(function(elem) {
    describe('Test of creation', function(){

      it('Should be an instance of TreeNode', function(done){
        expect(elem).to.be.an.instanceof(TreeNode);
        done();
      });

      it('Should have coord x and y', function(done){
        expect(elem).to.have.property('x');
        expect(elem).to.have.property('y');
        expect(elem.getX()).to.be.a('Number');
        expect(elem.getY()).to.be.a('Number');
        expect(elem.getX()).to.be.at.least(0);
        expect(elem.getY()).to.be.at.least(0);
        done();
      });

    });
  });
});
