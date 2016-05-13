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

  CTOR_ATTRIBUTES = %i(width height x y)

  attr_accessor *CTOR_ATTRIBUTES, :childrens, :points # Class attributes

  CHILDRENS_LOCATIONS = %i(top_left top_right bottom_right bottom_left)

  Childrens = Struct.new *CHILDRENS_LOCATIONS

  def_delegators :@childrens, *CHILDRENS_LOCATIONS # Syntaxic sugar to access childrens

  # Class declaration ==========================================================

  def initialize(attributes = {})
    # Raise error if one attribute is missing or invalid
    check_attributes(attributes)

    @width = attributes[:width].to_f
    @height = attributes[:height].to_f
    @x = attributes[:x].to_f
    @y = attributes[:y].to_f

    @childrens = Childrens.new
    @points = Array.new
  end

  def to_s(options = {})
    options = { info: [:points, :coord], offset: 0 }.merge options

    node_header = "#{" " * options[:offset]}##{options[:offset] / DUMP_OFFSET + 1}"

    msg = [if options[:info].include? :coord
      CTOR_ATTRIBUTES.map { |attr| "#{attr}:#{self.send attr}" }.join(" ")
    end,
    if options[:info].include? :points
      " " + @points.map { |e| e.join(",") }.join(" ")
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
      ventilate_to_childrens points
      points.clear
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
    point[X] >= @x && point[X] < @x + @width &&
    point[Y] >= @y && point[Y] < @y + @height
  end

  def subdivide
    x_mid = (@x + @width) / 2.0
    y_mid = (@y + @height) / 2.0
    child_width = @width / 2.0
    child_height = @height / 2.0

    top_left = {
      x: @x,
      y: @y
    }
    top_right = {
      x: @x + child_width,
      y: @y
    }
    bottom_right = {
      x: @x + child_width,
      y: @y + child_height
    }
    bottom_left = {
      x: @x,
      y: @y + child_height
    }

    [top_left, top_right, bottom_right, bottom_left].each do |child|
      child.merge! width: child_width, height: child_height
    end

    @childrens.top_left = Node.new top_left
    @childrens.top_right = Node.new top_right
    @childrens.bottom_right = Node.new bottom_right
    @childrens.bottom_left = Node.new bottom_left

    @childrens.values # Return nodes
  end

  def is_leaf?
    @childrens.values.compact.empty?
  end

  def is_node?
    ! is_leaf?
  end

  private # ====================================================================

  def between_childrens?(point)
    @childrens.values.compact.count { |child| child.own_point? point } > 1
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

    if attributes[:height] <= 0 || attributes[:width] <= 0
      raise ArgumentError, "Width and height must be greater than 0"
    end
  end

end
end
