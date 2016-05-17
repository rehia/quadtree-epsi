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
		if(QuadTreeUtils.listOfPointsContainsPoint(points, point))
			return;
		
		boolean isInQuadTree = this.root.push(point);
		
		if(isInQuadTree)
			this.points.add(point);
	}

	@Override
	public String toString() {
		return String.format("QuadTree containing %1$d point(s) :" + "\r\n" 
				+ "%3$d-Root %2$s", this.points.size(), this.root, this.root.getDepth());
	}

	public int getDepthOfPoint(XY point) {
		return this.root.getNodeContainingPoint(point).getDepth();
	}

	public String getListClosestPointsOfPoint(XY point) {
		Node nodeContainingPoint = this.root.getNodeContainingPoint(point);
		List<XY> closest = nodeContainingPoint.getPoints();
		
		if(nodeContainingPoint.pointIsOverlappingTwoNodes(point)) {
			for(Node child : nodeContainingPoint.getChildren()) {
				closest.addAll(child.getPoints());
			}
		}
		
		closest = QuadTreeUtils.removePointFromList(point, closest);
		
		return QuadTreeUtils.pointsListToString(closest);
	}
	

}
