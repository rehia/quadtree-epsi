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
    
    int x, y, width, height;
    List<Point> listPoint;
    
    

    public Node(int x, int y, int width, int height) {
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
    
    
}
