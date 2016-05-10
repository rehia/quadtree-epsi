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

  describe '#meta_delegate_to_root' do
    let (:tree) { QuadTree::Tree.new }

    it 'delegate <<, add_point, add to root and return tree itself' do
      # Create a double to receive methods delegation
      root = double("root")
      # Set private variable to our double
      tree.instance_variable_set(:@root, root)

      # Allow the double to receive :add_point method
      allow(root).to receive(:add_point)
      same_tree1 = tree.add_point [42, 42]
      expect(same_tree1).to eq(tree)

      allow(root).to receive(:add)
      same_tree2 = tree.add 21, 42
      expect(same_tree2).to eq(tree)

      allow(root).to receive(:<<)
      same_tree3 = tree << [42, 21]
      expect(same_tree3).to eq(tree)
    end

    it 'delegate to_s to root' do
        root = double("root")
        tree.instance_variable_set(:@root, root)

        allow(root).to receive(:to_s)
        tree.to_s
    end
  end

  describe '#point_depth' do
    let (:tree) do
      QuadTree::Tree.new << [0, 21] << [21, 0] << [0, 1] << [40, 40] << [84, 84]
    end

    context 'with point in a leaf' do
      it 'return the point depth' do
        expect(tree.point_depth(40, 40)).to eq(2)
        expect(tree.point_depth([0, 21])).to eq(2)
      end
    end

    context 'with point in a node' do
      it 'return the point depth' do
        tree << [49, 49]

        expect(tree.point_depth(49, 49)).to eq(1)
      end
    end

    context 'with point not in tree' do
      it 'return 0' do
        expect(tree.point_depth(84, 42)).to eq(0)
      end
    end
  end

  describe '#find_neighbors' do
    let (:match_group_1) { [[0, 21], [21, 0], [0, 1], [5, 5], [12, 12], [40, 40]] }
    let (:match_group_2) { [[21, 84], [34, 67], [42, 84]] }
    let (:tree) do
      tree = QuadTree::Tree.new
      match_group_1.each { |point| tree << point }
      match_group_2.each { |point| tree << point }
      tree
    end

    context 'with neighbors' do

      context 'with point in a node' do
        it 'return points in neighborhood' do
          tree << [84, 84] << [12, 12] << [24, 24]

          expect(tree.find_neighbors(24, 24)).to eq(match_group_1)
        end
      end

      context 'with point in a leaf' do
        it 'return points in neighborhood' do
          tree << [42, 72] << [84, 84] << [49, 49]

          expect(tree.find_neighbors(42, 72)).to eq(match_group_2)
        end
      end
    end

    context 'without neightbors' do
      it 'return an empty array' do
        tree << [84, 84]

        expect(tree.find_neighbors(84, 84)).to eq([])
      end
    end
  end

end
