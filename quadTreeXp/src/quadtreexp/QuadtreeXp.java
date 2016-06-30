package quadtreexp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Maxime
 */
public class QuadtreeXp {

    private static Arbre t = null;
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static ArrayList<Point> candidats;
    private static boolean finished;

    public static void main(String[] args) throws IOException {
        String s;
        if (args.length == 2 && args[1].equals("t")) {
            genererArbre(100, 50);
        }
        finished = false;
        do {
            System.out.print("Tapez une commande (h ou help pour l'aide) > ");
            s = br.readLine();
            traiterCommande(s);
        } while (!finished);
        System.exit(0);
    }

    private static void traiterCommande(String s) throws IOException {
        switch (s) {
            case "help":
            case "h":
                System.out.println(help);
                break;
            case "q":
            case "quit":
                System.out.println("@+");
                finished = true;
                break;
            case "afficher":
            case "a":
                if (t == null) {
                    System.out.println("Veuillez commencer par générer un arbre à l'aide de la commande t.");
                } else {
                    t.printConsole();
                }
                break;
            case "dd":
                if (t == null) {
                    System.out.println("Veuillez commencer par générer un arbre à l'aide de la commande t.");
                } else {
                    t.draw();
                }
                break;
            case "tree":
            case "t":
                int size = 100;
                int nb = 50;
                genererArbre(size, nb);
                break;
            case "profondeur":
            case "pr":
                if (t == null) {
                    System.out.println("Veuillez commencer par générer un arbre à l'aide de la commande t.");
                } else {
                    int x = getIntFromUser("Donnez l'abscisse du point");
                    int y = getIntFromUser("Donnez l'ordonnée du point");
                    printProfondeurDuPoint(x, y);
                }
                break;
            case "points":
            case "p":
                if (t == null) {
                    System.out.println("Veuillez commencer par générer un arbre à l'aide de la commande t.");
                } else {
                    printCandidats();
                }
                break;
            case "voisins":
            case "v":
                if (t == null) {
                    System.out.println("Veuillez commencer par générer un arbre à l'aide de la commande t.");
                } else {
                    int x = getIntFromUser("Donnez l'abscisse du point");
                    int y = getIntFromUser("Donnez l'ordonnée du point");
                    printVoisins(x, y);
                }
                break;
            default:
                System.out.println(help);
        }
    }

    private static final String help = "Manuel d'utilisation : \n"
            + "h ou help : Afficher ce message d'aide\n"
            + "t ou tree : Générer un nouveau Quadtree\n"
            + "a ou afficher : Afficher le Quadtree\n"
            + "p ou points : Afficher des points du Quadtree\n"
            + "pr ou profondeur : Touver la profondeur d'un point\n"
            + "v ou voisins : trouver les voisins proches d'un point\n"
            + "q ou quit : Quitter\n";

    private static int getIntFromUser(String ask) throws IOException {
        System.out.print(ask + " >");
        String s = br.readLine();
        int size = -1;
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(s);
        if (m.matches()) {
            size = Integer.parseInt(s);
        } else {
            System.out.println("Erreur de format, entrez une valeur entière.");
        }
        return size;
    }

    private static void printCandidats() {
        System.out.println("Voici les coordonnées de 10 points de la grille.");
        for (Point point : candidats) {
            System.out.println("\t(" + point.getX() + "," + point.getY() + ")");
        }
    }

    public static void genererArbre(int taille, int nombre) {
        if (taille != -1 && nombre != -1) {
            t = new Arbre(taille, nombre);
            System.out.println("Nouveau Quadtree généré, profondeur maximale : " + t.getProfondeurMaximale());
            candidats = t.creerPointsAleatoires(10);
        }
    }

    private static void printProfondeurDuPoint(int x, int y) {
        int p = t.getProfondeur(x, y);
        if (p < 0 && p > -9999) {
            System.out.println("Attention le point (" + x + "," + y + ") ne fait pas partie du QuadTree.");
            p = -p;
        }
        if (p == -9999) {
            System.out.println("Attention le point (" + x + "," + y + ") ne fait pas partie de la grille.");
        } else {
            System.out.println("Le point (" + x + "," + y + ") à une profondeur de " + p);
        }
    }

    private static void printVoisins(int x, int y) {
        ArrayList<Point> voisins = t.trouverVoisin(x, y);
        System.out.println("Le point (" + x + "," + y + ") possède " + voisins.size() + " voisins proches :");
        for (Point p : voisins) {
            System.out.println("\t(" + p.getX() + "," + p.getY() + ")");
        }
    }
}
