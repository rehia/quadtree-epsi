package quadtree;

import quadtree.Quadtree;
import quadtree.Point;
import java.util.ArrayList;

public class Main {
	private ArrayList<Point>ListeDePoint;

	public static void main (String [] arg)
	{
		final int nbPointMax = 4;
		
		Quadtree quadtree = new Quadtree();
		Point point = new Point(100,0,true);
		quadtree.insertUniquePoint(point);
		
	}
	
}
