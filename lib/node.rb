require 'byebug'
require 'awesome_print'
require 'ostruct'
require 'forwardable'

module QuadTree
class Node
  # Config =====================================================================

  extend Forwardable

  X, Y = 0, 1 # Syntaxic sugar

  DUMP_OFFSET = 42

  CTOR_ATTRIBUTES = %i(x y x_max y_max)

  attr_accessor *CTOR_ATTRIBUTES, :childrens, :points # Class attributes

  CHILDRENS_LOCATIONS = %i(top_left top_right bottom_right bottom_left)

  Childrens = Struct.new *CHILDRENS_LOCATIONS

  def_delegators :@childrens, *CHILDRENS_LOCATIONS # Syntaxic sugar to access childrens

  # Class declaration ==========================================================

  def initialize(attributes = {})
    # Raise error if one attribute is missing or invalid
    check_attributes(attributes)

    @x = attributes[:x].to_f
    @y = attributes[:y].to_f
    @x_max = attributes[:x_max].to_f
    @y_max = attributes[:y_max].to_f

    @childrens = Childrens.new
    @points = Array.new
  end

  def to_s(options = {})
    options = { info: [:points, :node], offset: 0 }.merge options

    node_header = "#{" " * options[:offset]}##{options[:offset] / DUMP_OFFSET + 1}"

    msg = [if options[:info].include? :node
      CTOR_ATTRIBUTES.map { |attr| "#{attr}:#{self.send attr}" }.join(" ")
    end,
    if options[:info].include? :points
      @points.map { |e| e.join(",") }.join(" ")
    end].compact.join(' | ')

    node_string = ""
    if is_leaf?
      node_string << "#{node_header} #{msg}\n"
    else
      options[:offset] += DUMP_OFFSET
      [
        top_left.to_s(options),
        top_right.to_s(options),
        "#{node_header} #{msg}\n",
        bottom_right.to_s(options),
        bottom_left.to_s(options)
      ].each { |s| node_string << s }
    end

    node_string
  end

  def count_points
    total = 0
    unless is_leaf?
      @childrens.values.compact.each do |child|
        total += child.count_points
      end
    end
    total + points.count
  end

  def collect_points(node = self)
    return node.points if node.is_leaf?

    node.points + node.childrens.values.inject([]) do |points, child|
      points += collect_points(child)
    end
  end

  def add_point(*point) # Splat operator ensure we always get an array
    point.flatten! # Ensure we have 1-D array
    raise ArgumentError, "More than 2 coordinates received" if point.size > 2
    raise ArgumentError, "Point #{point} is outside node" unless own_point?(point)

    if is_leaf? && points.count == 4
      subdivide
      points_to_ventilate = @points.select { |p| ! between_childrens?(p) }
      ventilate_to_childrens points_to_ventilate
      @points = @points - points_to_ventilate
    end

    if is_node? && ! between_childrens?(point)
      ventilate_to_childrens point
    else
      points << point unless points.include? point
    end

    self # Return object himself to chain function call
  end
  alias_method :<<, :add_point # Syntaxic sugar
  alias_method :add, :add_point

  def own_point?(point)
    point[X] >= @x && point[X] <= @x_max &&
    point[Y] >= @y && point[Y] <= @y_max
  end

  def is_leaf?
    @childrens.values.compact.empty?
  end

  def is_node?
    ! is_leaf?
  end

  private # ====================================================================

  def subdivide
    child_x_max = (@x_max - @x) / 2.0
    child_y_max = (@y_max - @y) / 2.0

    top_left = {
      x: @x,
      y: @y
    }
    top_right = {
      x: @x + child_x_max,
      y: @y
    }
    bottom_right = {
      x: @x + child_x_max,
      y: @y + child_y_max
    }
    bottom_left = {
      x: @x,
      y: @y + child_y_max
    }

    [top_left, top_right, bottom_right, bottom_left].each do |child|
      child.merge! x_max: child[:x] + child_x_max, y_max: child[:y] + child_y_max
    end

    @childrens.top_left = Node.new top_left
    @childrens.top_right = Node.new top_right
    @childrens.bottom_right = Node.new bottom_right
    @childrens.bottom_left = Node.new bottom_left

    @childrens.values # Return nodes
  end

  def between_childrens?(point)
    max_bounds = @childrens.map { |c| [c.x_max, c.y_max] }.flatten.uniq
    max_bounds.include?(point[X]) || max_bounds.include?(point[Y])
  end

  def ventilate_to_childrens(*points)
    # Get 1-D array and then put point coordinates by pair again
    points = points.flatten.each_slice(2).map { |point| point }
    points.each do |point|
      @childrens.values.compact.each { |child| child << point if child.own_point? point }
    end
  end

  def check_attributes(attributes)
    missings = CTOR_ATTRIBUTES - attributes.keys # This is why I love ruby :-)

    if missings.any?
      raise ArgumentError, "You must provide 4 attributes (#{missings.join(', ')} are missing)"
    end

    if attributes[:x_max] < attributes[:x] || attributes[:y_max] < attributes[:y]
      raise ArgumentError, "x_max and y_max must be respectively greater than x and y"
    end
  end

end
end
