/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quatreebis;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tahitibob2016
 */
public class QuatreeBis {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    private final static int NB_POINT_MAX = 4;

    private QuadtreeBis _nw;
    private QuadtreeBis _ne;
    private QuadtreeBis _se;
    private QuadtreeBis _sw;
    
    /*  x0              x1
      y0 ---------------->
        |
        |
        |
        |
        |
        |
      y1\/
    */ 
    private int _x0;
    private int _x1;
    private int _y0;
    private int _y1;
    
    private List<Point> _points;

    public QuatreeBis(int x0, int x1, int y0, int y1) {
         
        _nw = null;
        _ne = null;
        _se = null;
        _sw = null;
        
        _x0 = x0;
        _x1 = x1;
        _y0 = y0;
        _y1 = y1;
        
        _points = new ArrayList();
    }
    
    public void addPoints(List<Point> newPoints) {
        _points.addAll(newPoints);
    }
    
    public void addPoint(Point newPoint) {
        _points.add(newPoint);
    }
    
     public void removePoints() {
        _points.clear();
    }  
     
    public List<Point> getPoints() {
        return _points;
    }
    
    public void ventilation() {
        
        if(_points.size() > NB_POINT_MAX) {
        
            _nw = new QuatreeBis(_x0,(_x0+_x1)/2,_y0,(_y0+_y1)/2);
            _ne = new QuatreeBis((_x0+_x1)/2,_x1,_y0,(_y0+_y1)/2);
            _se = new QuatreeBis((_x0+_x1)/2,_x1,(_y0+_y1)/2,_y1);
            _sw = new QuatreeBis(_x0,(_x0+_x1)/2,(_y0+_y1)/2,_y1);

            for(Point p : _points) {
                moveInQuadTreeChild(p);            
            }
            
            this.removePoints();
            
            _nw.ventilation();
            _ne.ventilation();
            _se.ventilation();
            _sw.ventilation();
            
        }
    }
    
    public int getLocation(Point p) {
        
        //0->NW | 1->NE | 10->SW | 11->SE
        int codeLocalisation = 0;
        
        if(p.getX() > (_x0+_x1)/2) {
            codeLocalisation += 1;
        }
        if(p.getY() > (_y0+_y1)/2) {
            codeLocalisation += 10;
        }
        
        return codeLocalisation;
    }
    
    public void moveInQuadTreeChild(Point p) {
        
        switch( getLocation(p) ) {
            case 0:
                _nw.addPoint(p);
                break;
            case 1:
                _ne.addPoint(p);
                break;
            case 10:
                _sw.addPoint(p);
                break;
            case 11:
                _se.addPoint(p);
                break;
            default:
                break;
        }
    }
    
    public int getDepthQuadtree() {
        
        if(_ne != null && _nw != null && _se != null && _sw != null) {
            
            int depth = _ne.getDepthQuadtree();
            depth = Math.max(depth,_nw.getDepthQuadtree());
            depth = Math.max(depth,_se.getDepthQuadtree());
            depth = Math.max(depth,_sw.getDepthQuadtree());
            
            return depth + 1;
            
        } else {
            
            return 1;
        }
    }
    
    public int getDepthOfPoint(Point pointWanted) {
        
        if(_ne != null && _nw != null && _se != null && _sw != null) {
            
            int depth = -1;
            
            switch( getLocation(pointWanted) ) {
                case 0:
                    depth = _nw.getDepthOfPoint(pointWanted);
                    break;
                case 1:
                    depth = _ne.getDepthOfPoint(pointWanted);
                    break;
                case 10:
                    depth = _sw.getDepthOfPoint(pointWanted);
                    break;
                case 11:
                    depth = _se.getDepthOfPoint(pointWanted);
                    break;
                default:
                    break;
            }
            
            return depth + 1;
            
        } else {
            
            return 1;
        }
    }
    
    public List<Point> getNeighbours(Point pointWanted) {
        
        if(_ne != null && _nw != null && _se != null && _sw != null) {

            
            switch( getLocation(pointWanted) ) {
                case 0:
                    return _nw.getNeighbours(pointWanted);
                case 1:
                    return _ne.getNeighbours(pointWanted);
                case 10:
                    return _sw.getNeighbours(pointWanted);
                case 11:
                    return _se.getNeighbours(pointWanted);
                default:
                    return null;
            }

        } else {
            
            return _points;
        }
    }
    
    public int getSize() {
        return _x1;
    }

    private static class QuadtreeBis {

        public QuadtreeBis() {
        }
    }

    
}
