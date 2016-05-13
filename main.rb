require './lib/tree.rb'

puts "\nHello from docker :-)"

_tree_ = QuadTree::Tree.new

define_method(:tree) do
  _tree_
end

define_method(:add_point) do
  _tree_.random_point(1) do |point|
    puts tree
    puts "Point added : #{point}"
    _tree_ << point
    puts "Total points : #{_tree_.root.count_points}"
  end
end

tree.random_point(100) { |point| tree << point }

define_method(:help_me) do
  puts ""
  puts "Tree generated with 100 random points"
  puts ""
  puts "Usage : "
  puts "  Display this help:\t\t\t\thelp_me"
  puts "  Print graphic representation of the tree:\tputs tree"
  puts "  Add point to the tree:\t\t\ttree << [42, 42]"
  puts "  Return 42 random points inside tree boundary: tree.random_point(42)"
  puts "  Add 42 random points to the tree:\t\ttree.random_point(42) { |point| tree << point }"
  puts "  Return number of points in the tree: \t\ttree.count_points"
  puts "  Return all points contained in the tree: \ttree.collect_points"
  puts "  Return depth of the point (42, 42): \t\ttree.point_depth [42, 42]"
  puts "  Return neighbors of the point (42, 42): \ttree.find_neighbors [42, 42]"
  puts ""
end

help_me
