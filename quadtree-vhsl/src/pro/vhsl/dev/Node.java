package pro.vhsl.dev;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private double x;
	private double y;
	private double width;
	private double height;
	private List<XY> points;

	public Node(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.setPoints(new ArrayList<>());
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public List<XY> getPoints() {
		return points;
	}

	public void setPoints(List<XY> points) {
		this.points = points;
	}

	public void push(XY point) {
		if(!this.isNotInBound(point))
			this.getPoints().add(point);
	}

	private boolean isNotInBound(XY point) {
		return 	point.getX() < this.x || point.getX() >= this.x + this.width ||
				point.getY() < this.y || point.getY() >= this.y + this.height;
	}
	
}
