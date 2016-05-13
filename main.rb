require './lib/tree.rb'

puts "Hello from docker :-)"

_tree_ = QuadTree::Tree.new

define_method(:tree) do
  _tree_
end

define_method(:add_point) do
  _tree_.random_points(1) { |point| puts "#{point}";  _tree_ << point ; puts _tree_.root.count_points }
end

#_points_ = tree.random_points(100) { |point| tree << point }

define_method(:points) do
  _points_
end

#puts tree
