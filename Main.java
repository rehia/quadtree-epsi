package quadtree;

import quadtree.Quadtree;
import quadtree.Point;
import java.util.ArrayList;

public class Main {

	private ArrayList<Point>ListeDePoint;

  	public static void main(String[] args){
  	  
       String action = args[0];
       String X_point = args[1];
       String Y_point = args[2];
       
       final int nbPointMax = 4;
		
  		Quadtree quadtree = new Quadtree();
  		Point point = new Point(100,0,true);
  		quadtree.insertUniquePoint(point);
      
       if(action == list)
       {

        //   appel de la methode qui liste les points générés aléatoirement
    	   quadtree.afficheListEtProfondeur();

       }
       else if(action == depth)
       {
        
        //   appel de la methode qui donne la profondeur du point de coordonée X_point,Y_point
 
       }
       else if(action == nearest)
       {

        //    appel de la methode qui donne les points les plus proches du point de coordonées X_point,Y_point
  
       }
    }
}
