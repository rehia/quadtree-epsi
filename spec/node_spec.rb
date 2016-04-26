require 'rspec'
require 'byebug'
require './lib/node.rb'

RSpec.describe QuadTree::Node do

  describe '#initialize' do

    context 'with valid boundaries' do
      it 'create node with specified boundaries' do
        node = QuadTree::Node.new(top_left: [0, 0], top_right: [99, 0],
                                  bottom_right: [99, 99], bottom_left: [0, 99])

        expect(node.top_left).to eq([0, 0])
        expect(node.top_right).to eq([99, 0])
        expect(node.bottom_right).to eq([99, 99])
        expect(node.bottom_left).to eq([0, 99])
      end
    end

    context 'with invalid boundaries' do
      it 'raise error' do
        expect {
          QuadTree::Node.new(top_left: [0, 0], top_right: [99, 0])
        }.to raise_error(ArgumentError)
      end
    end
  end

  describe '#own_point' do
    # Lazy evaluated value, cached only in the same test
    let(:node) { QuadTree::Node.new(top_left: [0, 0], top_right: [99, 0],
                                    bottom_right: [99, 99], bottom_left: [0, 99]) }

    context 'with a point in node' do
      it 'return true' do
        expect(node.own_point([42, 42])).to eq(true)
        expect(node.own_point([0, 0])).to eq(true)
        expect(node.own_point([99, 99])).to eq(true)
      end
    end

    context 'with a point out of node' do
      it 'return false' do
        expect(node.own_point([-1, 42])).to eq(false)
        expect(node.own_point([42, 100])).to eq(false)
      end
    end
  end

  describe '#add_point (or <<)' do
    let(:node) { QuadTree::Node.new(top_left: [0, 0], top_right: [99, 0],
                                    bottom_right: [99, 99], bottom_left: [0, 99]) }

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
        expect(node.childrens.count).to eq(4)

        expect(node.childrens[0].points.count).to eq(4)
        expect(node.childrens[2].points.count).to eq(1)
      end
    end

    context 'with a node with more than 4 points' do
      it 'ventilate the point to childrens nodes' do
        node << [0, 0] << [10, 10] << [42, 42] << [21, 21] << [84, 84]

        node << [21, 84] << [84, 21]

        expect(node.childrens[3].count).to eq(1)
        expect(node.childrens[1].count).to eq(1)
      end
    end
  end

  describe '#subdivide' do
    let(:node) { QuadTree::Node.new(top_left: [0, 0], top_right: [99, 0],
                                    bottom_right: [99, 99], bottom_left: [0, 99]) }

    it 'create 4 equals child nodes' do
        top_left, top_right, bottom_right, bottom_left = node.subdivide

        expect(top_left.top_left).to eq([0, 0])
        expect(top_left.top_right).to eq([49, 0])
        expect(top_left.bottom_right).to eq([49, 49])
        expect(top_left.bottom_left).to eq([0, 49])

        expect(top_right.top_left).to eq([49, 0])
        expect(top_right.top_right).to eq([99, 0])
        expect(top_right.bottom_right).to eq([99, 49])
        expect(top_right.bottom_left).to eq([49, 49])

        expect(bottom_right.top_left).to eq([49, 49])
        expect(bottom_right.top_right).to eq([99, 49])
        expect(bottom_right.bottom_right).to eq([99, 99])
        expect(bottom_right.bottom_left).to eq([49, 99])

        expect(bottom_left.top_left).to eq([0, 49])
        expect(bottom_left.top_right).to eq([49, 49])
        expect(bottom_left.bottom_right).to eq([49, 99])
        expect(bottom_left.bottom_left).to eq([0, 99])
    end
  end

end
