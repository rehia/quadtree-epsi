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
public class QuadTree {
    
    private static final int MAX_POINT= 4;
    
    private QuadTree NW;
    private QuadTree NE;
    private QuadTree SW;
    private QuadTree SE;

    private int X0;
    private int X1;
    private int Y0;
    private int Y1;
    
    private List<Point> _points;        
    
    public QuadTree(int x0, int x1, int y0, int y1) {
        NW = null;
        NE = null;
        SW = null;
        SE = null;
        
        X0 = x0;
        X1 = x1;
        Y0 = y0;
        Y1 = y1;
        
        System.out.println("Nouveau QuadTree de taille : " + X0 +"-> "+ X1 + ":" + Y0 + "-> " + Y1);
    }
    
 
   
    
    /*
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    } 
}
