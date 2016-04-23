require 'rspec'
require 'byebug'
require './lib/node.rb'

RSpec.describe Node do

  describe '#initialize' do

    context 'with valid boundaries' do
      it 'create node with specified boundaries' do
        node = Node.new(top_left: [0, 0], top_right: [0, 99],
                        bottom_right: [99, 99], bottom_left: [99, 0])

        expect(node.boundaries.top_left).to eq([0, 0])
        expect(node.boundaries.top_right).to eq([0, 99])
        expect(node.boundaries.bottom_right).to eq([99, 99])
        expect(node.boundaries.bottom_left).to eq([99, 0])
      end
    end

    context 'with invalid boundaries' do
      it 'raise error' do
        expect {
          Node.new(top_left: [0, 0], top_right: [0, 99])
        }.to raise_error(ArgumentError)
      end
    end
  end

  describe '#add_point (or <<)' do
    let(:node) { Node.new(top_left: [0, 0], top_right: [0, 99],
                         bottom_right: [99, 99], bottom_left: [99, 0]) }

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
      end
    end
  end

end
