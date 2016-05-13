/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadtree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author vsouche & asoyer
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
    
    private List<Point> allPoints = new ArrayList<>();        
    
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
        
        if (allPoints.size() > MAX_POINT) {
            
            divideQuadtreeInFourSubquadtree();
            
           for (Point point: allPoints) {
               
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

    private void ventileAllSubQuadTree() {
        SW.ventile();
        SE.ventile();
        NW.ventile();
        NE.ventile();
    }
    
    
    public void addPoint(Point newPoint) {
        
        if (!allPoints.contains(newPoint)) {
            
            allPoints.add(newPoint); 
            
        }
    }
    
    public List<Point> getAllPoints() {
        return allPoints;
    }
    
    public void removeAllPoints() {
        allPoints.clear();
    }
 
    
    public String currentLocationPoint(Point point) {
        String pointLocation;
        
        if (point.getX() < (X0+X1)/2) {
            
            if (point.getY() < (Y0+Y1)/2) {
                
                pointLocation = "SW";
                
            } else {
                
                pointLocation = "NW";
            }
        } else if (point.getY() < (Y0+Y1)/2) {
            
            pointLocation = "SE";
            
        } else {
            
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
            
        } else {
            
            pointDepth = 0;
            
        }
        
        return pointDepth;
        
    }
    
    
    public List<Point> nearPoint(Point p) {
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
           
       } else {
           
            return allPoints;
            
        }         
        
    }
    
    
   public void putPointsInChildren(Point point) {
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
     
   
    
    /*
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int i = 0;
        String reponse;
        int numberOfGeneratedPoints;
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Entrez le nombre de point à générer : ");
        reponse = sc.nextLine();
        numberOfGeneratedPoints = Integer.parseInt(reponse);
        
        System.out.println("Creation du Quadtree de taille 100x100 contenant " + numberOfGeneratedPoints + "points");
        
        QuadTree qt = new QuadTree(0, 100, 0, 100);
        Point p = new Point(22,64);
       
        for (i=0; i<numberOfGeneratedPoints; i++) {
            Point randomPoint = new Point();
            qt.addPoint(randomPoint.generatePoint(100));
        }
        
        qt.ventile();
        
        System.out.println("Profondeur max : "+ qt.getPointDepth());
        System.out.println("Points dans le meme quadtree : " + qt.nearPoint(p));
        
    } 
}
