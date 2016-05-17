/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tahitibob2016
 */
public class Point {
    double x,y;
    
    public Point(){
    
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    public static Point newRandomPoint(double nbMax){
        
        double x = (double)(Math.random() * (nbMax + 1));
        double y = (double)(Math.random() * (nbMax + 1));
        Point newPoint = new Point(x,y);
        
        return newPoint;
    }
    
    public static List<Point> newRandomManyPoint(int maxSize, int nb) {
        
        boolean Liste;
        
        List<Point> points = new ArrayList();
        
        for(int i=0;i<nb;++i) {
            
            Liste = false;
            
            Point newPoint = newRandomPoint(maxSize);
            
            for(Point p : points) {
                
                if(newPoint.isEquals(p)) {
                    --i;
                    Liste = true;
                }
            }
            
            if(!Liste) {
                points.add(newPoint);
            }
        }
        
        return points;
    }

    public boolean isEquals(Point p) {
        
        if(this.x == p.x && this.y == p.y) {
            
            return true;
        } else {
            
            return false;
        }
    }
}
