/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadtree;

import java.util.Scanner;

/**
 *
 * @author max
 */
public class QuadTree {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println("Quadtree - EPSI");
        System.out.println("I4-C1 : HERMER - PRIMAUX - REYROLLE\n");

        Scanner sc = new Scanner(System.in);

        Grid g = new Grid();

        g.populate(50);
        //Scanner pour le menu (on garde en mémoire le choix de l'utilisateur)
        String userInput = new String();
        //Scanner pour l'ajout du point si manquant. 
        int userChoice;
        while (!userInput.equals("q")) {
            System.out.println("\n /************** MENU **************/");
            System.out.println("Depth of a Point by its coordinates ? (d)");
            System.out.println("Find nearest Points of a Point by its coordinates ? (f)");
            System.out.println("Help ? (h)");
            System.out.println("Quit ? (q)");

            userInput = sc.nextLine();
            while (!userInput.equals("d") && !userInput.equals("f") && !userInput.equals("q") && !userInput.equals("h")) {
                System.out.println("Enter a valid option");
                userInput = sc.nextLine();
            }
            if (userInput.equals("q")) {
                break;
            }
            else if (userInput.equals("h")) {
                System.out.println("Texte pour aider.");
            }
            else {
                System.out.println("Enter the coordinates  ( int 0 to " + (g.getSize()) + ") :");
                System.out.println("x : ");

                int x = sc.nextInt();

                while (x < 0 && x > g.getSize() - 1) {
                    System.out.println("Enter a valid coordinate");
                    x = sc.nextInt();
                }

                System.out.println("y : ");
                int y = sc.nextInt();

                while (y < 0 && y > g.getSize() - 1) {
                    System.out.println("Enter a valid coordinate");
                    y = sc.nextInt();
                }
                //For testing
                g.addPoint(new Point(1, 1));

                Point p = new Point(x, y);

                if (!g.pointExist(p)) {
                    System.out.println("This point doesn't exist in the grid, add it? (1/0)");
                    userChoice = sc.nextInt();
                    if (userChoice == 1) {
                        g.addPoint(p);
                    }
                }

                if (userInput.equals("d")) {
                    System.out.println("Depth of the Point : " + p + " = " + g.getDepthOfPoint(p));
                }
                else {
                    System.out.println("Nearest Points of the Point : " + p);
                    System.out.println(g.getNearestPoints(p));
                }
            }
        }

    }

}
