package quadtree;
 
import quadtree.Quadtree;
import quadtree.Point;
import java.util.ArrayList;

public class Main {

	private ArrayList<Point>ListeDePoint;

  	public static void main(String[] args){
  	  
       //String action = args[0];
  		String action = "depth";
      // String X_point = args[1];
       //String Y_point = args[2];
       
       final int nbPointMax = 4;
		
  		Quadtree quadtree = new Quadtree();
  		Point point = new Point(100,0,true);
  		Point point1 = new Point(100,0,true);
  		Point point2 = new Point(100,0,true);
  		Point point3 = new Point(100,0,true);
  		Point point4 = new Point(100,0,true);
  		Point point5 = new Point(100,0,true);
  		Point point6 = new Point(100,0,true);
  		
  		quadtree.insertUniquePoint(point);
  		quadtree.insertUniquePoint(point1);
  		quadtree.insertUniquePoint(point2);
  		quadtree.insertUniquePoint(point3);
  		quadtree.insertUniquePoint(point4);

  		
      
       if(action == "list")
       {

        //   appel de la methode qui liste les points générés aléatoirement
    	   quadtree.afficheListEtProfondeur();

       }
       else if(action == "depth")
       {
        
        //   appel de la methode qui donne la profondeur du point de coordonée X_point,Y_point
    	   quadtree.getProfondeurByPoint(10, 2);
 
       }
       else if(action == "nearest")
       {

        //    appel de la methode qui donne les points les plus proches du point de coordonées X_point,Y_point
  
       }
    }
}
