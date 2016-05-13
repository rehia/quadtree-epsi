require 'rspec'
require 'byebug'
require './lib/node.rb'

RSpec.describe QuadTree::Node do

  describe '#initialize' do

    context 'with valid boundaries' do
      it 'create node with specified size and coordinates' do
        node = QuadTree::Node.new(width: 100, height: 100, x: 0, y: 0)

        expect(node.width).to eq(100)
        expect(node.height).to eq(100)
        expect(node.x).to eq(0)
        expect(node.y).to eq(0)
      end
    end

    context 'with invalid or incomplete size or coordinates' do
      it 'raise error' do
        expect {
          QuadTree::Node.new(width: 100, height: 100, x: 0)
        }.to raise_error(ArgumentError)

        expect {
          QuadTree::Node.new(width: -42, height: 100, x: 42, y: 42)
        }.to raise_error(ArgumentError)
      end
    end
  end

  describe '#own_point?' do
    # Lazy evaluated value, cached only in the same test
    let(:node) { QuadTree::Node.new(width: 100, height: 100, x: 0, y: 0) }

    context 'with a point in node' do
      it 'return true' do
        expect(node.own_point?([42, 42])).to eq(true)
        expect(node.own_point?([0, 0])).to eq(true)
        expect(node.own_point?([99, 99])).to eq(true)
      end
    end

    context 'with a point out of node' do
      it 'return false' do
        expect(node.own_point?([-1, 42])).to eq(false)
        expect(node.own_point?([42, 100])).to eq(false)
      end
    end
  end

  describe '#add_point (or <<)' do
    let(:node) { QuadTree::Node.new(width: 100, height: 100, x: 0, y: 0) }

    context 'with a node with less than 4 point' do
      context 'with more than 2 coordinates' do
        it 'raise error' do
          expect {
            node.add_point(42, 42, 42)
          }.to raise_error(ArgumentError)
        end
      end

      context 'with a point inside boundaries' do
        it 'add point to the node' do
          node << [42, 42] << [21, 42]
          node.add_point(21, 21)

          expect(node.points.count).to eq(3)
        end
      end

      context 'with a point outside boundaries' do
        it 'raise error' do
          expect {
            node << [100, 0]
          }.to raise_error(ArgumentError)

          expect {
            node << [-1, 0]
          }.to raise_error(ArgumentError)
        end
      end
    end

    context 'with a node with 4 point' do
      it 'subdivide the node and ventilate points to childrens nodes' do
        node << [0, 0] << [10, 10] << [42, 42] << [21, 21]

        node << [84, 84]

        expect(node.points.count).to eq(0)
        expect(node.childrens.to_h.count).to eq(4)

        expect(node.top_left.points.count).to eq(4)
        expect(node.bottom_right.points.count).to eq(1)
      end
    end

    context 'with a node with more than 4 points' do
      it 'ventilate the point to childrens nodes' do
        node << [0, 0] << [10, 10] << [42, 42] << [21, 21] << [84, 84]

        node << [21, 84] << [84, 21]

        expect(node.bottom_left.points.count).to eq(1)
        expect(node.top_right.points.count).to eq(1)
      end
    end

    context 'with a child node with more than 4 points' do
      it 'subdivide child node and ventilate the point to childrens nodes' do
        node << [0, 0] << [10, 10] << [42, 42] << [21, 21]

        node << [1, 1]

        expect(node.top_left.top_left.points.count).to eq(4)
        expect(node.top_left.bottom_right.points.count).to eq(1)
      end
    end

    context 'with a point between 2 nodes' do
      it 'never happend' do
      end
    end
  end

  describe '#subdivide' do
    let(:node) { QuadTree::Node.new(width: 100, height: 100, x: 0, y: 0) }

    it 'create 4 equals child nodes' do
        top_left, top_right, bottom_right, bottom_left = node.subdivide

        expect(top_left.width).to eq(50)
        expect(top_left.height).to eq(50)
        expect(top_left.x).to eq(0)
        expect(top_left.y).to eq(0)

        expect(top_right.width).to eq(50)
        expect(top_right.height).to eq(50)
        expect(top_right.x).to eq(50)
        expect(top_right.y).to eq(0)

        expect(bottom_right.width).to eq(50)
        expect(bottom_right.height).to eq(50)
        expect(bottom_right.x).to eq(50)
        expect(bottom_right.y).to eq(50)

        expect(bottom_left.width).to eq(50)
        expect(bottom_left.height).to eq(50)
        expect(bottom_left.x).to eq(0)
        expect(bottom_left.y).to eq(50)
    end
  end

  describe '#to_s' do
    let(:node) { QuadTree::Node.new(width: 100, height: 100, x: 0, y: 0) }

    it 'return printable string representation of tree' do
        node << [0, 0] << [1, 1] << [2, 2] << [3, 3]
        node << [42, 42] << [89, 65] << [65, 89] << [13, 70]

        str = node.to_s
        expect(str.count("\n")).to eq(9)
    end
  end


end
