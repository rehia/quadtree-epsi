package quadtree;
 
import quadtree.Quadtree;
import quadtree.Point;
import java.util.ArrayList;

public class Main {

	private ArrayList<Point>ListeDePoint;

  	public static void main(String[] args){
  	  String action ="";
  	  String X_point="";
  	  String Y_point="";
	   if(args.length >0)
	   {
		   if(args[0].length() != 0)
	       {
	    	    action = args[0];
	       }
	       if(args[1].length() != 0)
	       {
	    	   	X_point = args[1];
	       }
	       if(args[1].length() != 0)
	       {
	    	   	Y_point = args[2];
	       }
	       
	   }
	   else
	   {
		   action = "list";
	   }
	  		
       
       //String action = "list";
       
       final int nbPointMax = 4;
		
  		Quadtree quadtree = new Quadtree();
  		
  		for (int i = 0; i< 50 ; i++)
  		{
  			Point Newpoint = new Point(100, 0, true); 
  			quadtree.insertUniquePoint(Newpoint);
  		}  

  		
      
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
