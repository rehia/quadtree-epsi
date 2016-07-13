package quadtree;

import java.util.ArrayList;
import java.util.List;

public class Quadtree {

	private Bounds bounds;
	private List<Point> points;

	public Quadtree(Point origin, double height, double width) {
		this.bounds = new Bounds(origin, height, width);
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
		return this.bounds.isInOrOn(point);
	}

	public boolean hasPoint(Point point) {
		return this.points.contains(point);
	}

	public int depthOf(Point point) {
		return 1;
	}
}
