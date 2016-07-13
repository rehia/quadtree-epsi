package quadtree;

public class Bounds {
	private Point origin;
	private double height;
	private double width;

	public Bounds(Point origin, double height, double width) {
		this.origin = origin;
		this.height = height;
		this.width = width;
	}
	
	public boolean isInOrOn(Point point) {
		return point.getX() >= this.origin.getX() 
				&& point.getX() <= this.width
				&& point.getY() >= this.origin.getY()
				&& point.getY() <= this.height;
	}
}
