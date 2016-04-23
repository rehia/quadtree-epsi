require 'byebug'
require 'awesome_print'

class Node

  attr_accessor :boundaries, :childrens, :points

  def initialize(boundaries = {})
    @childrens = Array.new(4) { nil }
    @points = Array.new
  end

  def <<(point)
    points << point

    if points > 4
    end
  end

  private # ====================================================================

  def subdivide
  end

end
