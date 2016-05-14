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
 * @author Tuan Nguyen <tuan.nguyen at tuannguyen.epsi.fr>
 */
public class Node {
    private double x, y, width, height;    
    
    private List<Point> points;
    
    private List<Node> children;
    
    public Node(double startX, double startY, double width, double height){
        this.x = startX;
        this.y = startY;
        this.width = width;
        this.height = height;
        this.points = new ArrayList<>();
        this.children = new ArrayList<>();
    }  
    
    public int countPoints(){
        return this.points.size();
    } 
}
