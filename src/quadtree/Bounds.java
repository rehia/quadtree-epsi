package quadtree;

import java.util.HashMap;
import java.util.Map;

public class Bounds {
	private double xOrigin;
	private double yOrigin;
	private double height;
	private double width;

	public Bounds(double xOrigin, double yOrigin, double width, double height) {
		this.xOrigin = xOrigin;
		this.yOrigin = yOrigin;
		this.height = height;
		this.width = width;
	}
	
	public boolean isStrictlyIn(Point point) {
		return point.getX() > this.xOrigin
				&& point.getX() < this.xOrigin + this.width
				&& point.getY() > this.yOrigin
				&& point.getY() < this.yOrigin + this.height;
	}

	public Map<Cardinals, Bounds> split() {
		double halfWidth = this.width / 2;
		double halfHeight = this.height / 2;
		double xCenter = this.xOrigin + halfWidth;
		double yCenter = this.yOrigin + halfHeight;
		
		Map<Cardinals, Bounds> nestedBounds = new HashMap<Cardinals, Bounds>();
		nestedBounds.put(Cardinals.SW, new Bounds(xOrigin, yOrigin, halfWidth, halfHeight));
		nestedBounds.put(Cardinals.SE, new Bounds(xCenter, yOrigin, halfWidth, halfHeight));
		nestedBounds.put(Cardinals.NE, new Bounds(xCenter, yCenter, halfWidth, halfHeight));
		nestedBounds.put(Cardinals.NW, new Bounds(xOrigin, yCenter, halfWidth, halfHeight));
		
		return nestedBounds;
	}

	public boolean isOn(Point point) {
		return point.getX() == this.xOrigin
				|| point.getX() == this.xOrigin + this.width
				|| point.getY() == this.yOrigin
				|| point.getY() == this.yOrigin + this.height;
	}
}
