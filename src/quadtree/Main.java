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
  	   quadtree.afficheListEtProfondeur();
       System.out.println("Enter X coordinate:");
       Scanner X_coordinate = new Scanner(System.in);
       int X_coor = X_coordinate.nextInt();
      
       System.out.println("Enter Y coordinate:");
       Scanner Y_coordinate  = new Scanner(System.in);
       int Y_coor = Y_coordinate.nextInt();
      
       System.out.println("To know the depth of the point "+X_coor+","+Y_coor+" in the tree type YES:");
       Scanner depth_boolean = new Scanner(System.in);
       String depth_bool = depth_boolean.nextLine();
  
      
       if (depth_bool == "YES")
       {
           //methode profondeur de X_coordinate Y_coordinate
    	   quadtree.getProfondeurByPoint(10, 2);
       }
      
       System.out.println("To know the neighboring of "+X_coor+","+Y_coor+"points of X, Y type YES:");
       Scanner neighbors_boolean;
       neighbors_boolean = new Scanner( System.in );
       String n_bool = neighbors_boolean.toString();
      
       if (n_bool == "YES")
       {
           //methode voisins de X_coordinate Y_coordinate
           System.out.println("neu");
       }
                  
       System.out.println("Thank you for using our program. GOODBYE :)");
    }
}
