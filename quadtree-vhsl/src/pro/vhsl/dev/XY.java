package pro.vhsl.dev;

public class XY {

	private double x;
	private double y;

	public XY(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	public boolean isTheSame(XY p) {
		boolean result = false;
		if(this.x == p.getX() && this.y == p.getY())
			result = true;
		return result;
	}
	

}
