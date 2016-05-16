/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadtree.xp;

import java.io.Console;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Hippolyte
 */
public class Main 
{

    private static int squareSize = 100;
    private static int pointNumber = 0;
    
    public static void main(String[] args) throws IOException 
    {
        System.out.println("=== QuadTree Extreme Programming ===\n");
        System.out.println("-> Dareau, Riquelme, Lacroix\n");

        QuadTree tree = new QuadTree(squareSize, squareSize);
        
        for (int i = 0; i < 50; i++)
        {
            tree.Push(generatePoint());   
        }
        
        graphicalDisplay(tree);
        
         while (true)
        {
            System.out.println("\n\n=== Request QuadTree ===");
            System.out.println("Rechercher un node via les coordonnées d'un point : (=> x,y)");
            System.out.println("Ecrire 'q' ou 'exit' pour quitter.");
            Scanner in = new Scanner(System.in);

            System.out.print("=> ");
            String s = in.next();
            if (s.equals("q") || s.equals("exit"))
                break;
            String[] coordonnée = s.split(",");
            int x = Integer.parseInt(coordonnée[0]);
            int y = Integer.parseInt(coordonnée[1]);
            tree.displayNodeFromPoint(new Point("ToFind", x, y));
        }
        
    }
    
    private static void graphicalDisplay(QuadTree tree)
    {
        boolean printed = false;
                
        System.out.println("\n== QuadTree Graphical Displaying ==\n");
        
        System.out.print("Y/X");
        for (int yAbs = 0; yAbs < squareSize; yAbs++)
        {
            System.out.print(" ");
            if (yAbs < 10)
                System.out.print("0");
            System.out.print(yAbs + " ");
        }
        System.out.print("\n");
        for (int x = 0; x < squareSize; x++)
        {
            for (int y = 0; y < squareSize; y++)
            { 
                if (y == 0)
                {
                    if (x < 10)
                        System.out.print("0");
                    System.out.print(x + " ");
                }
                for (Map.Entry<String, Point> entry : tree.getPoints().entrySet())
                {
                    printed = false;
                    if (entry.getValue().getX() == y && entry.getValue().getY() == x)
                    {
                        if (Integer.parseInt(entry.getValue().getName()) < 10)
                            System.out.print("[0"+ entry.getValue().getName() + "]");
                        else
                            System.out.print("["+ entry.getValue().getName() + "]");
                        printed = true;
                        break;
                    }      
                }
                if (!printed)
                    System.out.print("[..]");
            }
            System.out.print("\n");
        }  
        System.out.println("\n");
        
    }
    
    private static Point generatePoint()
    {
        Random rand = new Random();
        int randomX = rand.nextInt(squareSize - 2 + 1) + 1;
        int randomY = rand.nextInt(squareSize - 2 + 1) + 1;
        
        return new Point("" + pointNumber++, randomX, randomY);
    }
}
