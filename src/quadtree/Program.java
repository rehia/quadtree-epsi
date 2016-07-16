package quadtree;

import java.util.List;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		System.out.println("**** Starting Quadtree ****");

		Quadtree quadtree = generateQuadtree();

		userInteraction(quadtree);
	}

	private static Quadtree generateQuadtree() {
		Quadtree quadtree = new Quadtree(100, 100);

		for (int i = 0; i < 50; i++) {
			quadtree.push(Point.random(100, 100));
		}
		return quadtree;
	}

	private static void userInteraction(Quadtree quadtree) {
		Scanner in = new Scanner(System.in);

		while (true) {
			System.out.println();
			System.out.println("Make a choice between :");
			System.out.println("	p - print the quadtree");
			System.out.println("	d - get the depth and neighbors of a point");
			System.out.println("	q - quit");
			String choice = in.next();
			System.out.println();

			if (choice.equals("q")) {
				System.out.println("OK ! Bye !");
				break;
			} else if (choice.equals("p")) {
				System.out.println("Here's the quadtree :");
				System.out.println(quadtree.toString());
			} else if (choice.equals("d")) {
				depthAndNeighbors(in, quadtree);
			} else {
				System.out.println("Sorry, I didn't understand your choice...");
			}
		}
	}

	private static void depthAndNeighbors(Scanner in, Quadtree quadtree) {
		System.out.println("Which point do you want ? (it can be an existing one or not; if not it will be injected)");
		System.out.print("X coordinate ? ");
		int x = in.nextInt();
		System.out.print("Y coordinate ? ");
		int y = in.nextInt();

		Point point = new Point(x, y);
		quadtree.push(point);

		List<Point> neighbors = quadtree.neighborsOf(point);
		System.out.print("OK... Point depth is " + quadtree.depthOf(point) + " and it has the " + neighbors.size()
				+ " following neighbors :\n" + neighbors);
	}

}
