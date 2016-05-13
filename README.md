# QuadTree

QuadTree implementation in ruby.

([Original README](README_ORIGINAL.md))

Arnaud Passelaigue
Adrien Maret

# Usage

## Docker

  - Pull ruby2.2.0 and build image : ```docker build -t aschen/quadtree .```  
  - Launch program : ```docker run -it --rm aschen/quadtree```
  - Run tests : ```docker run --rm aschen/quadtree rspec -f d -c spec/```

## Stand-alone

  - Install ruby2.2.0 : ```sudo apt-get install ruby2.2.0```
  - Bundle gems : ```gem install bundler && bundle install```
  - Launch program : ```irb -r ./main.rb```
  - Run tests : ```rspec -f d -c spec/```

## API

```ruby
require './lib/tree.rb'

# Create QuadTree
tree = QuadTree::Tree.new # => #<QuadTree::Tree: @x_max=100.0, @y_max=100.0, @x=0.0, @y=0.0>
tree = QuadTree::Tree.new(x_max: 42, y_max: 42) # => #<QuadTree::Tree: @x_max=42.0, @y_max=42.0, @x=0.0, @y=0.0>

# Print tree (node infos + points)
puts tree # => nil

# Print tree (only points)
puts tree.to_s(info: [:points])

# Print tree (only node infos)
puts puts tree.to_s(info: [:points])

# Add point to the tree
tree << [42, 42] << [21, 21] # => #<QuadTree::Tree: ...>
tree.add(42, 42) # => <QuadTree::Tree: ...>
tree.add_point(42, 42) # => <QuadTree::Tree: ...>

# Get random point
tree.random_point # => [78, 51]

# Get more random points
tree.random_point(42) # => [[21, 33], [56, 26] ... ]

# Add random points to the tree
tree.random_point(42) { |point| tree << point } # => [[21, 33], [56, 26] ... ]

# Count points in tree
tree.count_points # => 42

# Collect all points in tree
tree.collect_points # => [[21, 33], [56, 26] ... ]

# Find point depth
tree.point_depth [42, 42] # => 1

# Find point neighbors
tree.find_neighbors(42, 42) # => [[41, 40], [39, 42]]
```
