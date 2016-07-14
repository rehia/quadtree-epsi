package quadtree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Quadtree {

	private final static int MAX_CAPACITY = 4;

	private Bounds bounds;
	private int depth;
	private List<Point> points;
	private Map<Cardinals, Quadtree> children;

	private Quadtree(double xOrigin, double yOrigin, double width, double height) {
		this(new Bounds(xOrigin, yOrigin, width, height), 1);
	}

	public Quadtree(double height, double width) {
		this(0, 0, width, height);
	}

	private Quadtree(Bounds bounds, int depth) {
		this.bounds = bounds;
		this.depth = depth;
		this.points = new ArrayList<Point>();
		this.children = new HashMap<Cardinals, Quadtree>();
	}

	public void push(Point point) {
		if (this.pointAlreadyPushed(point) || this.isOutOfBounds(point)) {
			return;
		}
		
		if (this.isLeaf() && this.hasReachedCapacity()) {
			this.splitIntoChildren();
			this.spreadPointsToChildren();
		}
		
		if (this.isLeaf()) {
			this.points.add(point);
		} else {
			this.spreadSinglePointToChildren(point);
		}
	}

	private boolean pointAlreadyPushed(Point point) {
		return this.hasPoint(point) || this.pointAlreadySpread(point);
	}

	private boolean pointAlreadySpread(Point point) {
		boolean alreadySpread = false;
		for (Quadtree childQuadtree : this.children.values()) {
			alreadySpread |= childQuadtree.pointAlreadyPushed(point);
		}
		return alreadySpread;
	}

	private void spreadPointsToChildren() {
		List<Point> spreadPoints = new ArrayList<Point>();
		for (Point point : this.points) {
			this.spreadSinglePointToChildren(point);
			if (this.pointAlreadySpread(point)) {
				spreadPoints.add(point);
			}
		}
		for (Point point : spreadPoints) {
			this.points.remove(point);
		}
	}

	private void spreadSinglePointToChildren(Point point) {
		for (Quadtree childQuadtree : this.children.values()) {
			childQuadtree.push(point);
		}
	}

	private void splitIntoChildren() {
		Map<Cardinals, Bounds> nestedBounds = this.bounds.split();
		for (Cardinals cardinal : nestedBounds.keySet()) {
			this.children.put(cardinal, new Quadtree(nestedBounds.get(cardinal), this.depth + 1));
		}
	}

	private boolean hasReachedCapacity() {
		return this.points.size() == MAX_CAPACITY;
	}

	private boolean isOutOfBounds(Point point) {
		return !this.bounds.isInOrOn(point);
	}

	public boolean hasPoint(Point point) {
		return this.points.contains(point);
	}

	public int depthOf(Point point) {
		int pointDepth = this.hasPoint(point) ? this.depth : 0;
		for (Quadtree childQuadtree : this.children.values()) {
			pointDepth += childQuadtree.depthOf(point);
		}
		return pointDepth;
	}

	public Quadtree child(Cardinals cardinal) {
		return this.children.get(cardinal);
	}

	public boolean hasChildren() {
		return !this.isLeaf();
	}

	private boolean isLeaf() {
		return this.children.isEmpty();
	}
}
