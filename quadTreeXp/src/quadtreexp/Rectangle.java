/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        int resultat;
        if("X".equals(coordonnee)){
            resultat = origine.getX();
        } else {
            resultat = origine.getY();
        }
        return resultat;
    }    
    
    public boolean contient(Point point) {
        int px = point.getX();
        int py = point.getY();
        return px >= origine.getX() && py >= origine.getY() && px <= origine.getX() + largeur && py <= origine.getY() + hauteur;
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
