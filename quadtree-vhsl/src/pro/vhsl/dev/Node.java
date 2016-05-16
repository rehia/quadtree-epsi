package pro.vhsl.dev;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private static final int MAXIMUM_POINT_CAPACITY = 4;
	private double x;
	private double y;
	private double width;
	private double height;
	private List<XY> points;
	private List<Node> children;
	private Node northWest;
	private Node northEast;
	private Node southWest;
	private Node southEast;

	public Node(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.points = new ArrayList<>();
		this.children = new ArrayList<>();
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

	public List<Node> getChildren() {
		return this.children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}

	public boolean push(XY point) {
		boolean success = false;
		if(this.isNotInBound(point))
			return success;
		
		if(this.points.size() < MAXIMUM_POINT_CAPACITY){
			this.points.add(point);
			success = true;
		} else {
			this.split();
		}
		
		return success;
	}

	private void split() {
		double middleX = (this.x + this.width) / 2;
		double middleY = (this.y + this.height) / 2;
		double halfWidth = this.width / 2;
		double halfHeight = this.height / 2;
		
		this.northWest = new Node(this.x, this.y, halfWidth, halfHeight);
		this.northEast = new Node(middleX, this.y, halfWidth, halfHeight);
		this.southWest = new Node(this.x, middleY, halfWidth, halfHeight);
		this.southEast = new Node(middleX, middleY, halfWidth, halfHeight);
		
		updateChildrenList();
	}

	private void updateChildrenList() {
		this.children.add(this.northWest);
		this.children.add(this.northEast);
		this.children.add(this.southWest);
		this.children.add(this.southEast);
		
	}

	private boolean isNotInBound(XY point) {
		return 	point.getX() < this.x || point.getX() >= this.x + this.width ||
				point.getY() < this.y || point.getY() >= this.y + this.height;
	}
	
	
	
}
