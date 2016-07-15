package quadtree;

public class Point {

	private int x;
	private int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point() {
		this(0, 0);
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Point)) {
			return false;
		}
		Point other = (Point) obj;
		return this.x == other.x && this.y == other.y;
	}

	public static Point random(int maxX, int maxY) {
		return new Point(
				(int) (Math.random() * maxX), 
				(int) (Math.random() * maxY));
	}
}
