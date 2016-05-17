/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadtreexp;

import java.util.ArrayList;
import java.util.Random;
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
    
}