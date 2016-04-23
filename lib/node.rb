require 'byebug'
require 'awesome_print'
require 'ostruct'
require 'forwardable'

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

    @childrens = Array.new(4) { nil }
    @points = Array.new
    @boundaries = OpenStruct.new(boundaries) # Syntaxic sugar, access hash like a object
  end

  def add_point(*point) # Splat operator ensure we always get an array
    point.flatten! # Ensure we have 1D array
    raise ArgumentError, "More than 2 coordinates received" if point.size > 2
    raise ArgumentError, "Point #{point} is outside boundaries" unless own_point(point)

    points << point

    if points.count > 4

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

  private # ====================================================================

  def subdivide
  end

  def check_boundaries_presence(boundaries)
    missings = BOUNDARIES - boundaries.keys # This is why I love ruby :-)

    if missings.any?
      raise ArgumentError, "You must provide 4 boundaries (#{missings} are missing)"
    end
  end

end
