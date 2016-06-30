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
        this.nodeChildren = new ArrayList<>();
        
    }

    public void pushPoint(Point point1) {
        this.listPoint.add(point1);
    }

    int countPoint() {
        return this.listPoint.size();
    }
    
      public boolean isLeaf(){
        return this.nodeChildren.isEmpty();
    }
      
      public void createLeaves(){
        double middleX = (this.x + this.width) / 2;
        double middleY = (this.y + this.height) / 2;
        double halfWidth = this.width / 2;
        double halfHeight = this.height / 2;

        //NW
        this.nodeChildren.add(new Node(this.x,this.y,halfWidth,halfHeight));
        //NE
        this.nodeChildren.add(new Node(middleX,this.y,halfWidth,halfHeight));
        //SE
        this.nodeChildren.add(new Node(middleX,middleY,halfWidth,halfHeight));
        //SW
        this.nodeChildren.add(new Node(this.x,middleY,halfWidth,halfHeight));
    }
      
      public boolean hasReachedCapacity(){
        return !this.isLeaf() || this.countPoint() == 4;
    }
      
      public void splitInLeaves(){
        //create Node leaves
        this.createLeaves();
        //push all points in children
        for (Point point : this.listPoint){
            this.nodeChildren.get(0).pushPoint(point);
            this.nodeChildren.get(1).pushPoint(point);
            this.nodeChildren.get(2).pushPoint(point);
            this.nodeChildren.get(3).pushPoint(point);
        }
        //clear node points before push in children
        this.listPoint.clear();
    }
      
      public Node getChildNodeByPosition(int leafPosition){
        return this.nodeChildren.get(leafPosition);
    }
      
      public void pushToChildren(Point point){
        if(this.isLeaf()){
            this.splitInLeaves();
        }
        for(Node childNode : this.nodeChildren){
            childNode.pushPoint(point);
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
        
        int nodePosition = this.getNodePositionByPoint(point);
        int depth = this.getChildNodeByPosition(nodePosition)
                .getDepthLevelByPoint(point);    
        
        return depth + 1;
    }
   
   public int getNodePositionByPoint(Point point) {

        //0 : NW, 1 : NE, 2 : SE, 3 : SW
        int nodePosition = 0;

        if (point.getX() > (this.x + this.width) / 2) {
            nodePosition += 1;
        }
        if (point.getY() > (this.y + this.height) / 2) {
            nodePosition += 2;
        }
        if (nodePosition == 2){
            nodePosition = 3;
        } else if(nodePosition == 3){
            nodePosition = 2;
        }

        return nodePosition;
    }
   
   public List<Point> getNeighboursOfPoint(Point point){
        if(this.isLeaf()){
            return this.listPoint;
        }
        else {
            int nodePosition = this.getNodePositionByPoint(point);
            return this.getChildNodeByPosition(nodePosition)
                .getNeighboursOfPoint(point);    
        }
    }
   
   public void toString(int depth, String indentation){
        if(this.isLeaf()){
            String strPoints = "";
            if(this.listPoint.isEmpty()){
                strPoints = "EMPTY";
            }
            else {
                for(Point point : this.listPoint){
                    strPoints += point.toString()+" ";
                }
            }
            System.out.println(indentation+"----------------");
            System.out.println(indentation + "Leaf "+depth+": "+strPoints);
            System.out.println(indentation+"----------------");
        }
        else {
            this.getChildNodeByPosition(0)
                    .toString(depth + 1, indentation+"\t");
            this.getChildNodeByPosition(1)
                    .toString(depth + 1, indentation+"\t");
            System.out.println(indentation+"----------------");
            System.out.println(indentation + "Node " + depth);
            System.out.println(indentation+"----------------");
            this.getChildNodeByPosition(2)
                    .toString(depth + 1, indentation+"\t");
            this.getChildNodeByPosition(3)
                    .toString(depth + 1, indentation+"\t");
            
        }
   }
      
      
    
      
}
