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

end
