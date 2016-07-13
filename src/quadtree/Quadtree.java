package quadtree;

import java.util.ArrayList;
import java.util.List;

public class Quadtree {

	private double height;
	private double width;
	private Point origin;
	private List<Point> points;

	public Quadtree(Point origin, double height, double width) {
		this.origin = origin;
		this.height = height;
		this.width = width;
		this.points = new ArrayList<Point>();
	}

	public Quadtree(double height, double width) {
		this(new Point(), height, width);
	}

	public void push(Point point) {
		if (isInBounds(point)) {
			this.points.add(point);
		}
	}

	private boolean isInBounds(Point point) {
		return point.getX() >= this.origin.getX() 
				&& point.getX() <= this.width
				&& point.getY() >= this.origin.getY()
				&& point.getY() <= this.height;
	}

	public boolean hasPoint(Point point) {
		return this.points.contains(point);
	}

	
}
