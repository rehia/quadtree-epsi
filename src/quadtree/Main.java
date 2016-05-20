package quadtree;
 
import quadtree.Quadtree;
import quadtree.Point;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

	private ArrayList<Point>ListeDePoint;

  	public static void main(String[] args){
  		Quadtree quadtree = new Quadtree();
  		
  		for (int i = 0; i< 50 ; i++)
  		{
  			Point Newpoint = new Point(100, 0, true); 
  			quadtree.insertUniquePoint(Newpoint);
  		}         
       //Methode Lister les diffenrents points générer et garder en memoir ses informations
  	   quadtree.afficheListPoint();
       System.out.println("Enter X coordinate:");
       Scanner X_coordinate = new Scanner(System.in);
       int X_coor = X_coordinate.nextInt();
      
       System.out.println("Enter Y coordinate:");
       Scanner Y_coordinate  = new Scanner(System.in);
       int Y_coor = Y_coordinate.nextInt();
      
       System.out.println("To know the depth of the point "+X_coor+","+Y_coor+" in the tree type YES:");
       Scanner depth_boolean = new Scanner(System.in);
       String depth_bool = depth_boolean.nextLine();
       System.out.println("Ceci est une var :"+depth_bool);
  
      
       if(depth_bool.contains("YES"))
       {
    	   System.out.println("Coucou TOi");
    	   quadtree.getProfondeurByPoint(X_coor, Y_coor);
       }
      
       System.out.println("To know the neighboring of "+X_coor+","+Y_coor+"points of X, Y type YES:");
       Scanner neighbors_boolean;
       neighbors_boolean = new Scanner( System.in );
       String n_bool = neighbors_boolean.toString();
      
       if (n_bool.contains("YES"))
       {
           //methode voisins de X_coordinate Y_coordinate
           System.out.println("neu");
       }
                  
       System.out.println("Thank you for using our program. GOODBYE :)");
    }
}
