
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import quad.Node;
import quad.Point;

public class Main {

	public static void main(String[] args) {
		Node nodeParent = new Node(0, 100, 0, 100, 1);
		
		Random rand = new Random();
		List<Point> points = new ArrayList<Point>();
		for(int index=0; index < 50; index++){
			int abscisse = 1 + rand.nextInt(100 * 1);
			int ordonne = 1 + rand.nextInt(100 * 1);
			Point point = new Point(abscisse, ordonne);
			nodeParent.addPoint(point);
			points.add(point);	
		}
		
		for(int index=0; index < 10; index++){
			int nextPoint = 1+ rand.nextInt(50 * 1);
			checkProfondeur(nodeParent, points.get(nextPoint));
			checkBrothers(nodeParent, points.get(nextPoint));
		}
		
		System.out.println("\n");
		
		System.out.println(nodeParent.toString());

	}

	private static void checkProfondeur(Node nodeParent, Point point) {
		Node nodeResult = nodeParent.findPoint(point);
		System.out.println(point.toString()+" "+nodeResult.getProfondeur());
	}
	
	private static void checkBrothers(Node nodeParent, Point point){
		Node nodeResult = nodeParent.findPoint(point);
		System.out.println(nodeResult);
	}

}
