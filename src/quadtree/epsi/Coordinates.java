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

    public void setX(int X) {
        if(isValidCoordinate())
        this.X = X;
    }

    public void setY(int Y) {
        this.Y = Y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }
    
    public Coordinates(){
        
    }

    private boolean isValidCoordinate() {
       
    return true;
    }
}
