/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/


import java.util.Scanner;
import quadtree.QuadTree;
import quadtree.QuadTreePoint;
import utils.Constants;

/**
 *
 * @author sacha
 */
public class Main {
    
    private static int point_x;
    private static int point_y;
    private static int points_generated;
    
    
    public static void main(String[] args) {
        
        readUserEntry(args);
        
        System.out.println("Création du Quadtree de taille 100 x 100...\n");
        
        System.out.println("-> Nombre de points : " + points_generated);
        System.out.println("-> Point x : " + point_x);
        System.out.println("-> Point y : " + point_y);
        
        QuadTree qt = new QuadTree(0, 100, 0, 100);
        QuadTreePoint p = new QuadTreePoint(point_x, point_y);
        
        qt.generateBasedQuadTree(points_generated);
        
        //
        
        displayResult(qt, p);
        
        System.out.println("\n\n[ Made with <3 in Montpellier ]");
    }
    
    public static void displayResult(QuadTree qt, QuadTreePoint p) {
        
        System.out.println("\nProfondeur max : " + qt.getPointDepth());
        System.out.println("Points dans le même QuadTree : " + (! qt.nearPoint(p).isEmpty() ? qt.nearPoint(p) : "0"));
    }
    
    
    public static void readUserEntry(String[] args) {
        
        // command line mode
        
        if (args.length >= 5) {
            
            if (args[0].equals(Constants.ARGS_POINTS_NUMBER)) {
                points_generated = Integer.parseInt(args[1]);
            }
            if (args[2].equals(Constants.ARGS_POINT_X)) {
                point_x = Integer.parseInt(args[3]);
            }
            if (args[4].equals(Constants.ARGS_POINT_Y)) {
                point_y = Integer.parseInt(args[5]);
            }
        }
        
        // gui mode
        
        else {            
            
            Scanner responses = new Scanner(System.in);
            
            System.out.print("Entrez le nombre de points à générer : ");
            points_generated =  Integer.parseInt( responses.nextLine() );
            
            System.out.print("Point x : ");
            point_x =  Integer.parseInt( responses.nextLine() );
            
            System.out.print("Point y : ");
            point_y =  Integer.parseInt( responses.nextLine() );
        }
        
    }
    
    
}
