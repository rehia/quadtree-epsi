require 'byebug'
require 'awesome_print'
require 'ostruct'
require 'forwardable'

module QuadTree
class Node
  # Config =====================================================================

  extend Forwardable # Needed to use def_delegators

  X, Y = 0, 1 # Syntaxic sugar

  BOUNDARIES = %i(top_left top_right bottom_right bottom_left) # Array of accepted boundary

  attr_accessor :boundaries, :childrens, :points # Class attributes

  def_delegators :@boundaries, *BOUNDARIES # Syntaxic sugar to access boundary

  # Class declaration ==========================================================

  def initialize(boundaries = {})
    # Raise error if one boundary is missing
    check_boundaries_presence(boundaries)

    @childrens = Array.new
    @points = Array.new
    @boundaries = OpenStruct.new(boundaries) # Syntaxic sugar, access hash like an object
    @width = top_right[X] - top_left[X] + 1
    @height = bottom_left[Y] -  top_left[Y] + 1
  end

  def add_point(*point) # Splat operator ensure we always get an array
    point.flatten! # Ensure we have 1-D array
    raise ArgumentError, "More than 2 coordinates received" if point.size > 2
    raise ArgumentError, "Point #{point} is outside boundaries" unless own_point(point)

    if points.count == 4
      subdivide
      points.each { |point| childrens.each { |child| child << point if child.own_point(point) } }
      points.clear
    end

    if childrens.any?
      childrens.each { |child| child << point if child.own_point(point) }
    else
      points << point
    end

    self # Return object himself to chain function call
  end
  alias_method :<<, :add_point # Syntaxic sugar

  def own_point(point)
    if point[X] >= top_left[X] && point[X] <= bottom_right[X] &&
       point[Y] >= top_left[Y] && point[Y] <= bottom_right[Y]
      true
    else
      false
    end
  end

  def subdivide
    child_width = @width / 2.0 - 1
    child_height = @height / 2.0 - 1

    top_left_child = {
      top_left:       top_left,
      top_right:      [child_width, top_left[Y]],
      bottom_right:   [child_width, child_height],
      bottom_left:    [top_left[X], child_height]
    }
    top_right_child = {
      top_left:       top_left_child[:top_right],
      top_right:      top_right,
      bottom_right:   [top_right[X], top_right[Y] + child_height],
      bottom_left:    top_left_child[:bottom_right]
    }
    bottom_right_child = {
      top_left:       top_right_child[:bottom_left],
      top_right:      top_right_child[:bottom_right],
      bottom_right:   bottom_right,
      bottom_left:    [top_right_child[:bottom_left][X], bottom_right[Y]]
    }
    bottom_left_child = {
      top_left:       top_left_child[:bottom_left],
      top_right:      top_left_child[:bottom_right],
      bottom_right:   bottom_right_child[:bottom_left],
      bottom_left:    bottom_left
    }

    [ top_left_child,
      top_right_child,
      bottom_right_child,
      bottom_left_child
    ].each { |child| childrens << QuadTree::Node.new(child) }

    childrens
  end

  private # ====================================================================

  def check_boundaries_presence(boundaries)
    missings = BOUNDARIES - boundaries.keys # This is why I love ruby :-)

    if missings.any?
      raise ArgumentError, "You must provide 4 boundaries (#{missings} are missing)"
    end
  end

end
end
