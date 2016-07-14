package quadtree;

public class Bounds {
	private double xOrigin;
	private double yOrigin;
	private double height;
	private double width;

	public Bounds(double xOrigin, double yOrigin, double height, double width) {
		this.xOrigin = xOrigin;
		this.yOrigin = yOrigin;
		this.height = height;
		this.width = width;
	}
	
	public boolean isInOrOn(Point point) {
		return point.getX() >= this.xOrigin
				&& point.getX() <= this.width
				&& point.getY() >= this.yOrigin
				&& point.getY() <= this.height;
	}
}
