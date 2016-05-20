/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package quadtree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utils.Constants;

/**
 *
 * @author vsouche & asoyer
 */
public class QuadTree {
    
    private QuadTree NW;
    private QuadTree NE;
    private QuadTree SW;
    private QuadTree SE;
    
    private int X0;
    private int X1;
    private int Y0;
    private int Y1;
    
    private List<QuadTreePoint> allPoints = new ArrayList<>();
    
    public QuadTree(int x0, int x1, int y0, int y1) {
        NW = null;
        NE = null;
        SW = null;
        SE = null;
        
        X0 = x0;
        X1 = x1;
        Y0 = y0;
        Y1 = y1;
    }
    
    public void ventile() {
        
        if (allPoints.size() > Constants.MAX_POINT) {
            
            divideQuadtreeInFourSubquadtree();
            
            for (QuadTreePoint point: allPoints) {
                putPointsInChildren(point);
            }
            
            removeAllPoints();
            
            ventileAllSubQuadTree();
        }
    }
    
    private void divideQuadtreeInFourSubquadtree() {
        newSWQuadtree();
        newSEQuadtree();
        newNWQuadtree();
        newNEQuadtree();
    }
    
    private void newNEQuadtree() {
        NE = new QuadTree(((X0+X1)/2), X1, ((Y0+Y1)/2), Y1);
    }
    
    private void newNWQuadtree() {
        NW = new QuadTree(X0, ((X0+X1)/2), ((Y0+Y1)/2), Y1);
    }
    
    private void newSEQuadtree() {
        SE = new QuadTree(((X0+X1)/2), X1, Y0, ((Y0+Y1)/2));
    }
    
    private void newSWQuadtree() {
        SW = new QuadTree(X0, ((X0+X1)/2), Y0, ((Y0+Y1)/2));
    }
    
    public void ventileAllSubQuadTree() {
        SW.ventile();
        SE.ventile();
        NW.ventile();
        NE.ventile();
    }
    
    
    public void addPoint(QuadTreePoint newPoint) {
        
        if (!allPoints.contains(newPoint)) {
            allPoints.add(newPoint);
        }
    }
    
    public List<QuadTreePoint> getAllPoints() {
        return allPoints;
    }
    
    public void removeAllPoints() {
        allPoints.clear();
    }
    
    
    public String currentLocationPoint(QuadTreePoint point) {
        
        String pointLocation;
        
        if (point.getX() < (X0 + X1) / 2) {
            
            if (point.getY() < (Y0 + Y1) / 2) {
                pointLocation = "SW";
            }
            else {
                pointLocation = "NW";
            }
        }
        else if (point.getY() < (Y0 + Y1) / 2) {
            pointLocation = "SE";
        }
        else {
            pointLocation = "NE";
        }
        
        return pointLocation;
    }
    
    
    public int getPointDepth() {
        
        int pointDepth;
        
        if (NW != null) {
            pointDepth= NW.getPointDepth();
            
            pointDepth = Math.max(pointDepth, NE.getPointDepth());
            pointDepth = Math.max(pointDepth, SW.getPointDepth());
            pointDepth = Math.max(pointDepth, SE.getPointDepth());
            
            pointDepth++;
        }
        else {
            pointDepth = 0;
        }
        
        return pointDepth;
    }
    
    
    public List<QuadTreePoint> nearPoint(QuadTreePoint p) {
        
        String currentPoint = currentLocationPoint(p);
        
        if (NW != null && NE != null && SW != null && SE != null) {
            
            switch (currentPoint) {
                case "NW":
                    return NW.nearPoint(p);
                case "NE":
                    return NE.nearPoint(p);
                case "SW":
                    return SW.nearPoint(p);
                case "SE":
                    return SE.nearPoint(p);
                default:
                    return null;
            }
        }
        else {
            return allPoints;
        }
        
    }
    
    
    public void putPointsInChildren(QuadTreePoint point) {
        
        switch (currentLocationPoint(point)) {
            case "NW":
                NW.addPoint(point);
                break;
            case "NE":
                NE.addPoint(point);
                break;
            case "SW":
                SW.addPoint(point);
                break;
            case "SE":
                SE.addPoint(point);
                break;
            default:
                break;
        }
    }


    
    public void generateBasedQuadTree(int numberOfGeneratedPoints) {
        
        for (int i = 0; i < numberOfGeneratedPoints; i++) {
            
            QuadTreePoint randomPoint = new QuadTreePoint();
            
            this.addPoint(randomPoint.generatePoint(100));
        }
        
        this.ventile();
    }    
    
}
