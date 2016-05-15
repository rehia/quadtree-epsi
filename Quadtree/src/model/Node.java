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
    
    public boolean isLeaf(){
        return this.children.size() == 0;
    }
    
    public void createLeaves(){
        double middleX = (this.x + this.width) / 2;
        double middleY = (this.y + this.height) / 2;
        double halfWidth = this.width / 2;
        double halfHeight = this.height / 2;
        
        //NW
        this.children.add(new Node(this.x,this.y,halfWidth,halfHeight));
        //NE
        this.children.add(new Node(middleX,this.y,halfWidth,halfHeight));
        //SE
        this.children.add(new Node(middleX,middleY,halfWidth,halfHeight));
        //SW
        this.children.add(new Node(this.x,middleY,halfWidth,halfHeight));
    }

    public boolean push(Point point) {
        if(this.isOutOfBounds(point)){
            return false;
        }
        if(this.hasReachedCapacity()){
            this.pushToChildren(point);
        }
        else {
            this.points.add(point);
        }
        return true;
    }
    
    public boolean isOutOfBounds(Point point){
        return point.getX() < this.x ||
                point.getX() >= this.x + width ||
                point.getY() < this.y ||
                point.getY() >= this.y + height;
    }
    
    public boolean hasReachedCapacity(){
        return !this.isLeaf() || this.countPoints() == 4;
    }
    
    public void splitInLeaves(){
        //create Node leaves
        this.createLeaves();
        //push all points in children
        for (Point point : this.points){
            this.children.get(0).push(point);
            this.children.get(1).push(point);
            this.children.get(2).push(point);
            this.children.get(3).push(point);
        }
        //clear node points before push in children
        this.points.clear();
    }
    
    public Node getChildNodeByPosition(int leafPosition){
        return this.children.get(leafPosition);
    }
    
    public void pushToChildren(Point point){
        if(this.isLeaf()){
            this.splitInLeaves();
        }
        for(Node childNode : this.children){
            childNode.push(point);
        }
    }
    
    public int getMaxDepth(){
        if(this.isLeaf()){
           return 1; 
        } 
        else {
            int depth = this.getChildNodeByPosition(0).getMaxDepth();
            depth = Math.max(depth,this.getChildNodeByPosition(1).getMaxDepth());
            depth = Math.max(depth,this.getChildNodeByPosition(2).getMaxDepth());
            depth = Math.max(depth,this.getChildNodeByPosition(3).getMaxDepth());
            return depth + 1;
        }
    }
    
    public int getDepthLevelByPoint(Point point){
        if(this.isLeaf()){
            return 1;
        }
        return 0;
    }
    
}
