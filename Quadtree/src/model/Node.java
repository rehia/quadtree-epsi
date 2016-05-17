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
public class Node {
    
    double x, y, width, height;
    List<Point> listPoint;
    List<Node> nodeChildren;
    
    

    public Node(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.listPoint = new ArrayList<>();
        
    }

    void pushPoint(Point point1) {
        this.listPoint.add(point1);
    }

    int countPoint() {
        return this.listPoint.size();
    }
    
     public boolean isLeaf(){
        return this.nodeChildren.size() == 0;
    }
    
}
