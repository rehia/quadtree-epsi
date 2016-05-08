/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author Gautier
 */
public class Repere {
    private float X;
    private float Y;
    
    public Repere(){
        
    }
    
    public String affiche(){
        return this.X +";"+ this.Y;
    }
    
    public Repere(float x, float y){
        this.X= x;
        this.Y = y;
    }

    /**
     * @return the X
     */
    public float getX() {
        return X;
    }

    /**
     * @param X the X to set
     */
    public void setX(float X) {
        this.X = X;
    }

    /**
     * @return the Y
     */
    public float getY() {
        return Y;
    }

    /**
     * @param Y the Y to set
     */
    public void setY(float Y) {
        this.Y = Y;
    }
    
    
}
