/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadtree;

import java.util.List;

/**
 *
 * @author vsouche
 */
public class Point {
    private int _x;
    private int _y;
    

    public Point(int x, int y) {
        this._x = x;
        this._y = y;
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
    
    
}
