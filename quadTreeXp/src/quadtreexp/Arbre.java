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
        if (rectangle.contient(point)) {
            if (rectangle.neContientPasDeRectangles()) {
                rectangle.ajouter(point);          
                if (rectangle.nombreDePoints() > 4) {
                    int largeurEnfant = rectangle.getLargeur()/ 2;
                    int hauteurEnfant = rectangle.getHauteur()/ 2;                   
                    int origineX = rectangle.pointOrigine("X");
                    int origineY = rectangle.pointOrigine("Y");
                    int nouvelleProfondeur = rectangle.getProfondeur() + 1;
                                                         
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
                    
                    
                    rectanglesEnfants.stream().forEach((r) -> {
                        r.setProfondeur(nouvelleProfondeur);
                    });
                    
                    miseAJourProfondeur(nouvelleProfondeur);
                                    
                    
                    liaison(rectanglesEnfants, rectangle); 
                    
                    ventilation(rectangle);
                    
                }
            } else {
                int nbRectanglesDuPoint = 0;
                for (Rectangle rectangleEnfant : rectangle.getRectangles()) {
                    if (rectangleEnfant.contient(point)) {
                        nbRectanglesDuPoint++;
                    }
                }
                if (nbRectanglesDuPoint < 2) {
                    for (Rectangle rectangleEnfant : rectangle.getRectangles()) {
                        rectangleEnfant.ajouter(point);
                    }
                } else {
                    rectangle.ajouter(point);
                }
            }
        }
    }
    
    public void miseAJourProfondeur(int nouvelleProfondeur) {
        if (nouvelleProfondeur > profondeurMaximale) {
            profondeurMaximale = nouvelleProfondeur;
        }
    }
    
    public void liaison(ArrayList<Rectangle> rectanglesEnfants, Rectangle rectangle) {
        rectanglesEnfants.stream().forEach((r) -> {
            rectangle.ajouter(r);
        });
    }
    
    public void ventilation(Rectangle rectangle) {
        ArrayList<Point> pointsDuRectangle = new ArrayList<>();
        ArrayList<Point> pointsDuRectanglePere = rectangle.getPoints();
        pointsDuRectangle.addAll(pointsDuRectanglePere);
        pointsDuRectanglePere.clear();
        for (Point pointsEnfants : pointsDuRectangle) {
            ajouterPoint(rectangle, pointsEnfants);
        }
    }
    
    
    
    public void draw() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(tailleGrille * scaling, tailleGrille * scaling));

        JFrame frame = new JFrame("QuadTree");
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);
    }
    
    

    public Rectangle getRectangleDescendant(Rectangle rectangle, Point point) {
        Rectangle rectangleDescendant = null;
        if (rectangle.neContientPasDeRectangles() && rectangle.contient(point)) {
            rectangleDescendant = rectangle;
        } else {
            if (rectangle.contient(point)) {
                int nbRectanglesDuPoint = 0;
                for (Rectangle rectangleEnfant : rectangle.getRectangles()) {
                    if (rectangleEnfant.contient(point)) {
                        nbRectanglesDuPoint++;
                    }
                }
                if (nbRectanglesDuPoint >= 2) {
                    rectangleDescendant = rectangle;
                } else {
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
    
    public ArrayList<Point> trouverVoisin(int x, int y) {
        Rectangle rectangle = getRectangle(x, y);
        ArrayList<Point> listeDePoints = new ArrayList<>();
        Point point = new Point(x, y);
        if (rectangle.neContientPasDeRectangles()) {
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

    public void printConsole() {
        Arbre.this.printConsole(rectangleRacine);
    }

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

    public ArrayList<Point> creerPointsAleatoires(int i) {
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
    
    public int getProfondeurMaximale() {
        return profondeurMaximale;
    }

    public void setProfondeurMaximale(int profondeurMaximale) {
        Arbre.profondeurMaximale = profondeurMaximale;
    }
    
    public Rectangle getRectangle(int x, int y) {
        return getRectangle(new Point(x, y));
    }

    public Rectangle getRectangle(Point point) {
        return getRectangleDescendant(rectangleRacine, point);
    }
    
}
