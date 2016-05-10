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

end
end
