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
      expect(tree.root.points.count).to eq(0)
    end

    it 'create quadtree with specified values' do
      tree = QuadTree::Tree.new(width: 42, height: 42, x: 21, y: 21)

      expect(tree.root.width).to eq(42)
      expect(tree.root.height).to eq(42)
      expect(tree.root.x).to eq(21)
      expect(tree.root.y).to eq(21)
      expect(tree.root.points.count).to eq(0)
    end
  end


  describe '#random_point' do
    let (:tree) { QuadTree::Tree.new }

    it 'return distinct random points' do
      points = tree.random_point(42)

      expect(points.count).to eq(42)
    end

    it 'return point between the node boundaries' do
      tree = QuadTree::Tree.new(width: 10, height: 10)
      points = tree.random_point(200)

      expect(points.count).to eq(100)
    end

    context 'with block given' do
      it 'yield the random point' do
        tree.random_point(1) { |point| tree << point }
        tree.random_point(3) { |point| tree << point }

        expect(tree.root.points.count).to eq(4)
      end
    end

  end
end
