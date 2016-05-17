/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadtreexp;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Maxime
 */
public class Arbre extends JPanel {
    private final Rectangle rectangleRacine;
    private final int tailleGrille;
    private final int scaling = 5;
    private static int profondeurMaximale = 0;
    
    public Arbre(int taille, int nombreDePoints) {
        this.tailleGrille = taille;
        rectangleRacine = new Rectangle(new Point(0, 0), taille, taille);
        rectangleRacine.setProfondeur(0);
        Random nombreAleatoire = new Random();
        for (int i = 0; i < nombreDePoints; i++) {
            Arbre.this.ajouterPoint(new Point(nombreAleatoire.nextInt(taille), nombreAleatoire.nextInt(taille)));
        }
    }

    public void ajouterPoint(Point point) {
        ajouterPoint(rectangleRacine, point);
    }
    
    public void ajouterPoint(Rectangle rectangle, Point point) {
        //Vérifie l'appartenance (géométrique) du point au rectangle.
        //Si le point est dans le rectangle
        if (rectangle.contient(point)) {
            //On vérifie si le rectangle à des enfants
            if (rectangle.estVide()) {
                //Si il n'en a pas, on ajoute le point
                rectangle.ajouter(point);
                //ajouterPointAuRectangle(rectangle, point);
                //On vérifie que le nombre de points ne dépasse pas 4              
                if (rectangle.nombreDePoints() > 4) {
                    int largeurEnfant = rectangle.getLargeur()/ 2;
                    int hauteurEnfant = rectangle.getHauteur()/ 2;                   
                    int origineX = rectangle.pointOrigine("X");
                    int origineY = rectangle.pointOrigine("Y");
                    final int nouvelleProfondeur = rectangle.getProfondeur() + 1;
                                                         
                    //Création
                    Rectangle rectangleEnfant1 = new Rectangle(new Point(origineX, origineY), largeurEnfant, hauteurEnfant);
                    Rectangle rectangleEnfant2 = new Rectangle(new Point(origineX, origineY).translate(largeurEnfant, 0), largeurEnfant, hauteurEnfant);
                    Rectangle rectangleEnfant3 = new Rectangle(new Point(origineX, origineY).translate(0, hauteurEnfant), largeurEnfant, hauteurEnfant);
                    Rectangle rectangleEnfant4 = new Rectangle(new Point(origineX, origineY).translate(largeurEnfant, hauteurEnfant), largeurEnfant, hauteurEnfant);
                    
                    ArrayList<Rectangle> rectanglesEnfants = new ArrayList<>();

                    rectanglesEnfants.add(rectangleEnfant1);
                    rectanglesEnfants.add(rectangleEnfant2);
                    rectanglesEnfants.add(rectangleEnfant3);
                    rectanglesEnfants.add(rectangleEnfant4);
                    
                    //On donne aux enfants la profondeur de leur parent + 1
                    rectanglesEnfants.stream().forEach((r) -> {
                        r.setProfondeur(nouvelleProfondeur);
                    });
                    
                    miseAJourProfondeur(nouvelleProfondeur);
                    
                    liaison(rectanglesEnfants, rectangle); 
                    
                    ventilation(rectangle);
                }
            } else {
                //Si le rectangle a déjà des enfants on regarde dans combien se situe le point.
                int nbRectanglesDuPoint = 0;
                for (Rectangle rectangleEnfant : rectangle.getRectangles()) {
                    if (rectangleEnfant.contient(point)) {
                        nbRectanglesDuPoint++;
                    }
                }
                //Si le point n'est pas dans au moins 2 rectangles enfants
                if (nbRectanglesDuPoint < 2) {
                    //On ventile le point.
                    for (Rectangle rectangleEnfant : rectangle.getRectangles()) {
                        rectangleEnfant.ajouter(point);
                    }
                } else {
                    //Sinon, le point est à cheval sur plusieurs rectangles et il reste lié au rectangle parent.
                    rectangle.ajouter(point);
                }
            }
        }
    }
    
    public void miseAJourProfondeur(final int nouvelleProfondeur) {
        if (nouvelleProfondeur > profondeurMaximale) {
            profondeurMaximale = nouvelleProfondeur;
        }
    }
    
    public void liaison(ArrayList<Rectangle> rectanglesEnfants, Rectangle rectangle) {
        //Liaison
        rectanglesEnfants.stream().forEach((r) -> {
            rectangle.ajouter(r);
        });
    }
    
    public void ventilation(Rectangle rectangle) {
        //Ventilation
        ArrayList<Point> pointsDuRectangle = new ArrayList<>();
        ArrayList<Point> pointsDuRectanglePere = rectangle.getPoints();
        pointsDuRectangle.addAll(pointsDuRectanglePere);
        pointsDuRectanglePere.clear();
        for (Point pointsEnfants : pointsDuRectangle) {
            ajouterPoint(rectangle, pointsEnfants);
        }
    }
    
    public int getProfondeurMaximale() {
        return profondeurMaximale;
    }

    public void setProfondeurMaximale(int profondeurMaximale) {
        Arbre.profondeurMaximale = profondeurMaximale;
    }
    
    public void draw() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(tailleGrille * scaling, tailleGrille * scaling));

        JFrame frame = new JFrame("QuadTree");
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * Renvoie le rectangle dans lequel se trouve le point de coordonnées (x,y).
     * @param x
     * @param y
     * @return 
     */
    public Rectangle getRectangle(int x, int y) {
        return getRectangle(new Point(x, y));
    }

    /**
     * Renvoie le rectangle dans lequel se trouve le point p.
     * @param p
     * @return 
     */
    public Rectangle getRectangle(Point p) {
        return getRectangleDescendant(rectangleRacine, p);
    }

    /**
     * Méthode récursive qui renvoie le rectangle descendant de r dans lequel se trouve le point p.
     * @param rectangle
     * @param point
     * @return 
     */
    public Rectangle getRectangleDescendant(Rectangle rectangle, Point point) {
        Rectangle rectangleDescendant = null;
        //Si le rectangle n'as pas d'enfants et qu'il contient le point p, c'est celui qu'on cherche.
        if (rectangle.estVide() && rectangle.contient(point)) {
            rectangleDescendant = rectangle;
        } else {
            if (rectangle.contient(point)) {
                //On vérifie si le point est à cheval sur plusieurs rectangles.
                int nbRectanglesDuPoint = 0;
                for (Rectangle rectangleEnfant : rectangle.getRectangles()) {
                    if (rectangleEnfant.contient(point)) {
                        nbRectanglesDuPoint++;
                    }
                }
                //Si oui on renvoie le rectangle parent.
                if (nbRectanglesDuPoint >= 2) {
                    rectangleDescendant = rectangle;
                } else {
                    //Sinon on cherche le rectangle parmis les enfants
                    for (Rectangle rectangleEnfant : rectangle.getRectangles()) {
                        Rectangle rectangleTrouve = getRectangleDescendant(rectangleEnfant, point);
                        if (rectangleTrouve != null) {
                            rectangleDescendant = rectangleTrouve;
                            break;
                        }
                    }
                }
            }
        }
        return rectangleDescendant;
    }
    
    /**
     * Renvoie la liste des points proches du point de coordonnées (x,y).
     * Les points proches sont ceux qui appartiennent au même rectangle, ou bien aux descendants lorsque le point est à cheval sur plusieurs rectangles.
     * @param x
     * @param y
     * @return 
     */
    public ArrayList<Point> trouverVoisin(int x, int y) {
        Rectangle rectangle = getRectangle(x, y);
        ArrayList<Point> listeDePoints = new ArrayList<>();
        Point point = new Point(x, y);
        if (rectangle.estVide()) {
            for (Point pointEnfant : rectangle.getPoints()) {
                if (pointEnfant.getX() != point.getX() && pointEnfant.getY() != point.getY()) {
                    listeDePoints.add(pointEnfant);
                }
            }
        } else {
            listeDePoints = getDescendantPoints(rectangle, point);
        }
        return listeDePoints;
    }

    /**
     * Renvoie les points qui se trouvent dans les rectangles descendants du rectangle r.
     * @param rectangle
     * @param point
     * @return 
     */
    public ArrayList<Point> getDescendantPoints(Rectangle rectangle, Point point) {
        ArrayList<Point> points = new ArrayList<>();
        if (point == null) {
            points.addAll(rectangle.getPoints());
        } else {
            for (Point pointEnfant : rectangle.getPoints()) {
                if (pointEnfant.getX() != point.getX() && pointEnfant.getY() != point.getY()) {
                    points.add(pointEnfant);
                }
            }
        }
        for (Rectangle rectangleEnfant : rectangle.getRectangles()) {
            points.addAll(getDescendantPoints(rectangleEnfant, point));
        }
        return points;
    }

    /**
     * Renvoie la profondeur du point (x,y).
     * @param x
     * @param y
     * @return 
     */
    public int getProfondeur(int x, int y) {
        Rectangle rectangle = getRectangle(x, y);
        int profondeur = -9999;
        if (rectangle != null) {
            if (pointDansRectangle(new Point(x, y), rectangle)) {
                profondeur = rectangle.getProfondeur();
            } else {
                profondeur = -rectangle.getProfondeur();
            }
        }
        return profondeur;
    }

    /**
     * Vérifie si le point appartient au rectangle.
     * Ici on vérifie si il existe un point dans ce rectangle qui à les même coordonnées que le point en paramètre.
     * @param point
     * @param rectangle
     * @return 
     */
    public boolean pointDansRectangle(Point point, Rectangle rectangle) {
        boolean resultat = false;
        for (Point pointEnfant : rectangle.getPoints()) {
            if (point.getX() == pointEnfant.getX() && point.getY() == pointEnfant.getY()) {
                resultat = true;
                break;
            }
        }
        return resultat;
    }
    /**
     * Affiche l'arbre dans la console de manière récursive.
     */
    public void printConsole() {
        Arbre.this.printConsole(rectangleRacine);
    }
    /**
     * Affiche un rectangle dans la rectangle.
     * @param r 
     */
    private void printConsole(Rectangle rectangle) {
        String retour = "";
        for (int i = 0; i < rectangle.getProfondeur() - 1; i++) {
            retour += "\t";
        }
        System.out.println(retour + "Noeud");
        retour += "\t";
        for (Point point : rectangle.getPoints()) {
            System.out.println(retour + point.getX() + "," + point.getY());
        }
        for (Rectangle rectangleEnfant : rectangle.getRectangles()) {
            Arbre.this.printConsole(rectangleEnfant);
        }
    }
    /**
     * Renvoie une liste de i points de l'arbre choisis au hasard.
     * @param i
     * @return 
     */
    ArrayList<Point> creerPointsAleatoires(int i) {
        ArrayList<Point> listeDePoints = new ArrayList<>();
        ArrayList<Point> listeDePointsDescendants = getDescendantPoints(rectangleRacine, null);
        for (int j = 0; j < i; j++) {
            ajouterUnPointAleatoire(listeDePoints, listeDePointsDescendants);
        }
        return listeDePoints;
    }

    public void ajouterUnPointAleatoire(ArrayList<Point> resultat, ArrayList<Point> tousLesPoints) {
        resultat.add(tousLesPoints.get(new Random().nextInt(tousLesPoints.size())));
    }
    
}