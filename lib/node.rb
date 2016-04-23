require 'byebug'
require 'awesome_print'
require 'ostruct'
require 'forwardable'

class Node
  # Config =====================================================================

  extend Forwardable

  X, Y = 0, 1

  BOUNDARIES = %i(top_left top_right bottom_right bottom_left)

  attr_accessor :boundaries, :childrens, :points

  def_delegators :@boundaries, *BOUNDARIES

  # Class declaration ==========================================================

  def initialize(boundaries = {})
    check_boundaries_presence(boundaries)

    @childrens = Array.new(4) { nil }
    @points = Array.new
    @boundaries = OpenStruct.new(boundaries)

  end

  def add_point(*point)
    point.flatten!
    raise ArgumentError, "More than 2 coordinates received" if point.size > 2
    raise ArgumentError, "Point #{point} is outside boundaries" unless belongs_to_boundaries(point)

    points << point

    if points.count > 4
    end

    self
  end
  alias_method :<<, :add_point

  private # ====================================================================

  def subdivide
  end

  def check_boundaries_presence(boundaries)
    missings = BOUNDARIES - boundaries.keys

    if missings.any?
      raise ArgumentError, "You must provide 4 boundaries (#{missings} are missing)"
    end
  end

  def belongs_to_boundaries(point)
    if point[X] > top_left[X] && point[X] < bottom_right[X] \
      && point[Y] > top_left[Y] && point[Y] < bottom_right[Y]
      true
    else
      false
    end
  end

end
