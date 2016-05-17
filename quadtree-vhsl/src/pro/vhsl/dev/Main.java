package pro.vhsl.dev;

import java.util.Random;
import java.util.Scanner;

public class Main {
	static Scanner scanner;
	public static void main(String[] args) {
		
		System.out.println("Welcome QuadTree Simulator 2016!");
		
		showMenu();
		scanner = new Scanner(System.in);
		int choice = scanner.nextInt();
		
		switch (choice) {
		case 1:
			generateQuadTreeWithRandomPoints();
			break;

		default:
			generateQuadTreeWithRandomPoints();
			break;
		}
		
		scanner.close();
	}

	private static void showMenu() {
		System.out.println("What do you want to do ?");
		System.out.println("1- Generate a QuadTree with random points.");
		
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
}
