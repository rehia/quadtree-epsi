/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadtree.xp;

/**
 *
 * @author Hippolyte
 */
public class Point 
{
    private String name;
    private int x;
    private int y;


    public Point(String name, int x, int y)
    {
        this.name = name;
        this.x = x;
        this.y = y;
    }
    
    public String getPoint()
    {
        return "Point" + name + "[" + x + "," + y + "]";
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
}
