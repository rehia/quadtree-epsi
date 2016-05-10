require 'rspec'
require 'byebug'
require './lib/tree.rb'

RSpec.describe QuadTree::Tree do

  describe '#initialize' do
    it 'create quadtree with default values' do
      tree = QuadTree::Tree.new

      expect(tree.root.width).to eq(100)
      expect(tree.root.height).to eq(100)
      expect(tree.root.x).to eq(0)
      expect(tree.root.y).to eq(0)
    end

    it 'create quadtree with specified values' do
      tree = QuadTree::Tree.new(width: 42, height: 42, x: 21, y: 21)

      expect(tree.root.width).to eq(42)
      expect(tree.root.height).to eq(42)
      expect(tree.root.x).to eq(21)
      expect(tree.root.y).to eq(21)
    end
  end

end
