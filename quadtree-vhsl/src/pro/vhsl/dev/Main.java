package pro.vhsl.dev;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
	static Scanner scanner;
	public static void main(String[] args) {
		
		System.out.println("Welcome QuadTree Simulator 2016!");
		int choice = 0;
		scanner = new Scanner(System.in);
		do {
			showMenu();
			choice = scanner.nextInt();
			switch (choice) {
			case 0:
				break;
			case 1:
				generateQuadTreeWithRandomPoints();
				break;
			case 2:
				querySampleQuadTreeToGetDepthOfRequestedPoint();
				break;
			case 3:
				querySampleQuadTreeToGetListOfClosestPointsToRequestedPoint();
				break;
	
			default:
				generateQuadTreeWithRandomPoints();
				break;
			}
		}while(choice > 0);
		
		scanner.close();
	}

	private static void showMenu() {
		System.out.println("\r\n");
		System.out.println("------------------------------");
		System.out.println("What do you want to do ?");
		System.out.println("1- Generate a QuadTree with random points.");
		System.out.println("2- Query sample QuadTree to get the depth of a point.");
		System.out.println("3- Query sample QuadTree to get the list of closests points to a point.");
		System.out.println("0- Exit.");
		
	}

	static Random random = new Random(10);
	public static XY createRandomPoint(int max) {
		return new XY(random.nextInt(max) -1, random.nextInt(max) -1);
	}

	static void generateQuadTreeWithRandomPoints() {
		System.out.println("Enter the QuadTree width: (default: 100)");
		int width = scanner.nextInt();
		if(width <= 0) width = 100;
		
		System.out.println("Enter the QuadTree height: (default: 100)");
		int height = scanner.nextInt();
		if(height <= 0) height = 100;
		
		System.out.println("Number of points to push into QuadTree: (default: 20)");
		int numberOfPoints = scanner.nextInt();
		if(numberOfPoints <= 0) numberOfPoints = 20;
		
		QuadTree quadtree = new QuadTree(width, height);
		try {
			System.out.println("List of points :");
			for(int i = 1; i <= numberOfPoints; ++i) {
				XY point = createRandomPoint(100);
				System.out.println(i + " - " + point);
				quadtree.push(point);
			}
		} catch (StackOverflowError e) {
			System.out.println("Stackoverflow error");
			System.out.println(e.getStackTrace());
		}

		System.out.println("QuadTree representation :");
		System.out.println(quadtree);
	}

	private static void querySampleQuadTreeToGetDepthOfRequestedPoint() {
		querySampleQuadTree("ToGetDepthOfRequestedPoint");
	}

	private static void querySampleQuadTreeToGetListOfClosestPointsToRequestedPoint() {
		querySampleQuadTree("ToGetListOfClosestPointsToRequestedPoint");
	}
	
	private static void querySampleQuadTree(String query) {
		QuadTree quadTree = QuadTreeUtils.generateSampleQuadTree();
		List<XY> points = quadTree.getPoints();
		System.out.println("List of points :");
		int i = 1;
		for(XY point : points) {
			System.out.println(i + " - " + point);
			++i;
		}
		
		System.out.println("Enter point index :");
		int pointId = scanner.nextInt();
		if(pointId > points.size())
			pointId = points.size();
		
		--pointId;

		System.out.println("Requested point : " + points.get(pointId));
		if(query.equals("ToGetDepthOfRequestedPoint"))
			System.out.println("Depth : " + quadTree.getDepthOfPoint(quadTree.getPoints().get(pointId)));
		else if(query.equals("ToGetListOfClosestPointsToRequestedPoint"))
			System.out.println("List of closest points : " + quadTree.getListClosestPointsOfPoint(points.get(pointId)));
	}
}
