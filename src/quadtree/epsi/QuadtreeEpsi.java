/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadtree.epsi;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author mathieu
 */
public class QuadtreeEpsi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Node node = new Node(new Coordinates(1, 1), 100, 100);
        node.addPoint(new Coordinates(10, 10));
        node.addPoint(new Coordinates(60, 20));
        node.addPoint(new Coordinates(80, 80));
        node.addPoint(new Coordinates(20, 40));
        node.addPoint(new Coordinates(45, 50));
        node.addPoint(new Coordinates(16, 17));
        node.addPoint(new Coordinates(90, 22));
        node.addPoint(new Coordinates(5, 8));
        node.addPoint(new Coordinates(27, 40));
        node.addPoint(new Coordinates(78, 50));
        node.toStringPoint();
        int choix = 3;
        Scanner scan = new Scanner(System.in);
        while (choix < 4 && choix > 0) {
            System.out.println("trouver la profondeur d'un point? (1) Ou ses voisins  ? (2) Entrer un nouveau point ? (3) Quitter ? (0)");
            choix = scan.nextInt();
            if (choix == 1) {
                System.out.println("Entrer le point à chercher :");
                System.out.println("X = ");
                int X = scan.nextInt();
                System.out.println("Y = ");
                int Y = scan.nextInt();
                int depth = node.getPointDepth(new Coordinates(X, Y));
                if (depth != -1) {
                    System.out.println("Profondeur : " + depth);
                } else {
                    System.out.println("Point absent de l'arbre");
                }

            } else if (choix == 2) {
                System.out.println("Entrer le point à chercher :");
                System.out.println("X = ");
                int X = scan.nextInt();
                System.out.println("Y = ");
                int Y = scan.nextInt();
                ArrayList<Coordinates> voisins = node.getNeighbors(new Coordinates(X, Y));
                System.out.println("Voisins du point :");
                if (voisins!=null) {
                    System.out.println(voisins.toString());
                } else {
                    System.out.println("Ce point n'est pas dans le quadtree");
                }
            }
            if (choix == 3) {
                System.out.println("Entrer le point à ajouter :");
                System.out.println("X = ");
                int X = scan.nextInt();
                System.out.println("Y = ");
                int Y = scan.nextInt();
                node.addPoint(new Coordinates(X, Y));
            }
            ArrayList<Coordinates> test = node.getNeighbors(new Coordinates(5, 8));

        }
    }
}
