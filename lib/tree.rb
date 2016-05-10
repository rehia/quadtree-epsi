require 'byebug'
require 'awesome_print'
require './lib/node.rb'
require 'forwardable'

module QuadTree
class Tree
  # Config =====================================================================

  extend Forwardable

  DEFAULT_NODE = { width: 100, height: 100, x: 0, y: 0 }

  attr_reader :root

  def_delegators :@root, :<<, :add_point

  # Class declaration ==========================================================

  def initialize(node_attributes = {})
    node_attributes = DEFAULT_NODE.merge node_attributes

    @root = QuadTree::Node.new node_attributes
  end

  def random_point(count = 1)
    points = get_random_points count

    points.each { |point| yield self, point } if block_given?

    points
  end

  def point_depth(*point)
    point.flatten! # Ensure we have 1-D array
    raise ArgumentError, "More than 2 coordinates received" if point.size > 2

    # Some code ...
  end

  def find_neighbors(*point)
    point.flatten! # Ensure we have 1-D array
    raise ArgumentError, "More than 2 coordinates received" if point.size > 2

    # Some code ...
  end

  private

    def get_random_points(count)
      x_range = (@root.x)..(@root.width)-1
      y_range = (@root.y)..(@root.height)-1
      points = []

      count.times do
        break if points.count == @root.width * @root.height

        point = []
        begin
          point = [Random.rand(x_range), Random.rand(y_range)]
        end while points.include? point

        points << point
      end

      points
    end

end
end
