package pro.vhsl.dev;

import java.util.ArrayList;
import java.util.List;

public class QuadTree {

	private Node root;
	private List<XY> points;

	public QuadTree(double width, double height) {
		this.root = new Node(0, 0, width, height);
		this.points = new ArrayList<>();
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public List<XY> getPoints() {
		return points;
	}

	public void setPoints(List<XY> points) {
		this.points = points;
	}

	public void push(XY point) {
		if(this.containsPoint(point))
			return;
		
		boolean isInQuadTree = this.root.push(point);
		
		if(isInQuadTree)
			this.points.add(point);
	}

	private boolean containsPoint(XY point) {
		boolean result = false;
		
		for(XY p : this.points){
			if(p.getX() == point.getX() && p.getY() == point.getY()){
				result = true;
				break;
			}
		}
		
		return result;
	}

	@Override
	public String toString() {
		return String.format("QuadTree containing %1$d point(s) :" + "\r\n" 
				+ "%3$d-Root %2$s", this.points.size(), this.root, this.root.getDepth());
	}

	public int getDepthOfPoint(XY point) {
		return this.root.getNodeContainingPoint(point).getDepth();
	}
	

}
