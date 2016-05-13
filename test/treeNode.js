'use strict';

const expect = require('chai').expect;

const TreeNode = require('./../src/treeNode');
const ContainerNode = require('./../src/containerNode');
const LeafNode = require('./../src/leafNode');

describe('Test of TreeNode ', function(){
  let testElems = [
    new ContainerNode(),
    new LeafNode()
  ]

  testElems.forEach(function(elem) {
    describe('Test of creation of a ' + elem, function(){

      it('Should be an instance of TreeNode', function(done){
        expect(elem).to.be.an.instanceof(TreeNode);
        done();
      });

    });
  });
});
