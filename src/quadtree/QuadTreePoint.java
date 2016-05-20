/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadtree;

/**
 *
 * @author vsouche
 */
public class QuadTreePoint {
    
    private int _x;
    private int _y;
    

    public QuadTreePoint() {
        
    }
    
    public QuadTreePoint(int x, int y) {
        this._x = x;
        this._y = y;
    }
    
    public QuadTreePoint generatePoint(int maxSize) {
        
        int lower = 0;
        int higher = maxSize;
        
        int randomX = (int)(Math.random() * (higher-lower)) + lower;
        int randomY = (int)(Math.random() * (higher-lower)) + lower;
        
        return new QuadTreePoint(randomX, randomY);
   }
    
    
    public int getX() {
        return _x;
    }

    public void setX(int x) {
        this._x = x;
    }

    public int getY() {
        return _y;
    }

    public void setY(int y) {
        this._y = y;
    }
   
  
    @Override
    public String toString() {
        return "(" + this._x + "," + this._y + ")";
    }
    
    
}
