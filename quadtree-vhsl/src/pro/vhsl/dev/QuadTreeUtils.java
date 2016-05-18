package pro.vhsl.dev;

import java.util.List;

public class QuadTreeUtils {

	public static String pointsListToString(List<XY> points) {
		StringBuilder sb = new StringBuilder();
		
		boolean seperateWithComma = false;
		for(XY point : points) {
			if(seperateWithComma)
				sb.append(", ");
			sb.append(point.toString());
			seperateWithComma = true;
		}
		return sb.toString();
	}

	public static List<XY> removePointFromList(XY point, List<XY> closest) {
		for(XY p : closest) {
			if(point.isTheSame(p)) {
				closest.remove(p);
				break;
			}
		}
		return closest;
	}

	public static QuadTree generateSampleQuadTree() {
		QuadTree quadTree = new QuadTree(100, 100);

		quadTree.push(new XY(10, 10));
		quadTree.push(new XY(60, 20));
		quadTree.push(new XY(80, 80));
		quadTree.push(new XY(20, 40));
		quadTree.push(new XY(45, 50));
		
		return quadTree;
		
	}

	public static boolean listOfPointsContainsPoint(List<XY> points, XY point) {
		boolean result = false;
		
		for(XY p : points){
			if(p.getX() == point.getX() && p.getY() == point.getY()){
				result = true;
				break;
			}
		}
		
		return result;
	}
}
