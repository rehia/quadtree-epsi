/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadtree.epsi;

/**
 *
 * @author mathieu
 */
public class Coordinates {

    private int X;
    private int Y;

    private boolean isCoordValid(int coordinate) {
        if (coordinate < 0 || coordinate >= 100) {
            return false;
        }
        return true;
    }



    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coordinates other = (Coordinates) obj;
        if (this.X != other.X) {
            return false;
        }
        if (this.Y != other.Y) {
            return false;
        }
        return true;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    //public Coordinates() { }

    public Coordinates(int X, int Y)  {
       
        try {
            this.setX(X);
        } catch (Exception ex) {
            System.out.println("Mauvaise coordonée X");
        }
        try {
            this.setY(Y);
        } catch (Exception ex) {
            System.out.println("Mauvaise coordonée Y");
        }
    }

    public void setX(int X) throws Exception {
        if (isCoordValid(X)) {
            this.X = X;
        } else {
            throw new Exception("Coordonée de X invalide");
        }
        
    }

    public void setY(int Y) throws Exception {

        if (isCoordValid(Y)) {
            this.Y = Y;
        } else {
            throw new Exception("Coordonée de Y invalide");
        }
    }
}
