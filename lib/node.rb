require 'byebug'
require 'awesome_print'
require 'ostruct'
require 'forwardable'

module QuadTree
class Node
  # Config =====================================================================

  extend Forwardable

  X, Y = 0, 1 # Syntaxic sugar

  CTOR_ATTRIBUTES = %i(width height x y)

  attr_accessor *CTOR_ATTRIBUTES, :childrens, :points # Class attributes

  CHILDRENS_LOCATIONS = %i(top_left top_right bottom_right bottom_left)

  def_delegators :@childrens, *CHILDRENS_LOCATIONS # Syntaxic sugar to access childrens

  # Class declaration ==========================================================

  def initialize(attributes = {})
    # Raise error if one attribute is missing or invalid
    check_attributes(attributes)

    @width = attributes[:width]
    @height = attributes[:height]
    @x = attributes[:x]
    @y = attributes[:y]

    @childrens = OpenStruct.new
    @points = Array.new
    @is_leaf = true
  end

  def add_point(*point) # Splat operator ensure we always get an array
    point.flatten! # Ensure we have 1-D array
    raise ArgumentError, "More than 2 coordinates received" if point.size > 2
    raise ArgumentError, "Point #{point} is outside node" unless own_point(point)

    if @is_leaf && points.count == 4
      subdivide
      ventilate_to_childrens points
      points.clear
    end

    if childrens.to_h.any? && ! is_between_childrens(point)
      ventilate_to_childrens point
    else
      points << point
    end

    self # Return object himself to chain function call
  end
  alias_method :<<, :add_point # Syntaxic sugar

  def own_point(point)
    point[X] >= @x && point[X] < @x + @width &&
    point[Y] >= @y && point[Y] < @y + @height
  end

  def subdivide
    @is_leaf = false

    child_width = @width / 2.0
    child_height = @height / 2.0

    top_left = {
      x: @x,
      y: @y
    }
    top_right = {
      x: @x + child_width - 1,
      y: @y
    }
    bottom_right = {
      x: @x + child_width - 1,
      y: @y + child_height - 1
    }
    bottom_left = {
      x: @x,
      y: @y + child_height -1
    }

    [top_left, top_right, bottom_right, bottom_left].each do |child|
      child.merge! width: child_width, height: child_height
    end

    childrens.top_left = Node.new top_left
    childrens.top_right = Node.new top_right
    childrens.bottom_right = Node.new bottom_right
    childrens.bottom_left = Node.new bottom_left

    childrens.to_h.values # Return nodes without location
  end

  private # ====================================================================

  def is_between_childrens(point)
    childrens.to_h.values.count { |child| child.own_point point } > 1
  end

  def ventilate_to_childrens(*points)
    # Get 1-D array and then put point coordinates by pair again
    points = points.flatten.each_slice(2).map { |point| point }
    points.each do |point|
      childrens.to_h.values.each { |child| child << point if child.own_point point }
    end
  end

  def check_attributes(attributes)
    missings = CTOR_ATTRIBUTES - attributes.keys # This is why I love ruby :-)

    if missings.any?
      raise ArgumentError, "You must provide 4 attributes (#{missings} are missing)"
    end

    if attributes[:height] <= 0 || attributes[:width] <= 0
      raise ArgumentError, "Width and height must be greater than 0"
    end
  end

end
end
