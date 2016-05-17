package quadtreexp;

import java.util.ArrayList;

/**
 *
 * @author Maxime
 */
public class Rectangle {
    private final Point origine;
    private final int largeur;
    private final int hauteur;

    private final ArrayList<Point> points;

    private final ArrayList<Rectangle> rectangles;

    private int profondeur;
    
    public Rectangle(Point origine, int largeur, int hauteur) {
        this.origine = origine;
        this.largeur = largeur;
        this.hauteur = hauteur;
        rectangles = new ArrayList<>();
        points = new ArrayList<>();
    }

    public int pointOrigine(String coordonnee){
        int result;
        if("X".equals(coordonnee)){
            result = origine.getX();
        } else {
            result = origine.getY();
        }
        return result;
    }    
    
    public boolean contient(Point point) {
        int px = point.getX();
        int py = point.getY();
        return px >= origine.getX() && py >= origine.getY() && px <= origine.getX() + largeur && py <= origine.getY() + hauteur;
    }
    
    public boolean neContientPasDeRectangles(){
        return rectangles.isEmpty();
    }
    
    public boolean ajouter(Point point){
        return points.add(point);
    }
    
    public int nombreDePoints(){
        return points.size();
    }
    
    public boolean ajouter(Rectangle rectangle){
        return rectangles.add(rectangle);
    }
    
    // Getters et Setters
    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }
    
    public int getProfondeur() {
        return profondeur;
    }

    public void setProfondeur(int profondeur) {
        this.profondeur = profondeur;
    }

    public ArrayList<Rectangle> getRectangles() {
        return rectangles;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }
 
    public Point getOrigine() {
        return origine;
    }
}
