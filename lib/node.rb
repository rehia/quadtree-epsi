require 'byebug'
require 'awesome_print'
require 'ostruct'

class Node

  X, Y = 0, 1
  BOUNDARIES = %i(top_left top_right bottom_right bottom_left)

  attr_accessor :boundaries, :childrens, :points

  def initialize(boundaries = {})
    check_boundaries_presence(boundaries)

    @childrens = Array.new(4) { nil }
    @points = Array.new
    @boundaries = OpenStruct.new(boundaries)

  end

  def <<(point)
    points << point

    if points > 4
    end
  end

  private # ====================================================================

  def subdivide
  end

  def check_boundaries_presence(boundaries)
    missings = BOUNDARIES - boundaries.keys

    if missings.any?
      raise ArgumentError, "You must provide 4 boundaries (#{missings} are missing)"
    end
  end

end
