/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadtree.xp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hippolyte
 */
public class Node 
{
    private double width;
    private double height;
    private double x;
    private double y;
    private int depth;
    
    private List<Point> points;
    private Map<Side, Node> childNodes;
    
    // QuadTree sides
    public enum Side
    {
        TL, TR, BL, BR;
    }

    public Node(double x, double y, double width, double height, int depth)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.points = new ArrayList<Point>() {};
        this.childNodes = new HashMap<>();
        this.depth = depth;
    }
    
    public boolean IsNotInBounds(Point point)
    {
        return point.getX() < this.x || point.getX() >= this.x + width || point.getY() < this.y || point.getY() >= this.y + height;
    }
    
    private boolean ChildIsEmpty()
    {
        return this.childNodes.isEmpty();
    }
    
    public boolean IsMoreThanThree()
    {
        return !this.ChildIsEmpty() || this.points.size() > 3;
    }

    
    public Node findNodeByPoint(Point pointToFind)
    {
        Node node = null;

        if (!points.isEmpty() && hasPoint(pointToFind))
            return this;
        else
        {
            for (Map.Entry<Side, Node> entry : childNodes.entrySet())
            {
                node = entry.getValue().findNodeByPoint(pointToFind);
                if (node != null)
                    break;
            }
        }
        return node;
    }
    
    public boolean hasPoint(Point pointToFind)
    {
        for (Point point : points)
        {
            if (point.getX() == pointToFind.getX() && point.getY() == pointToFind.getY())
                return true;
        }
        return false;
    }
    
    
    public boolean Push(Point point) 
    {
        if (this.IsNotInBounds(point))
            return false;

        if (this.IsMoreThanThree())
        {
            if (this.isOnLimit(point))
                this.points.add(point);
            else
                this.PushToChildren(point);
        }
        else
            this.points.add(point);
        
        return true;
    }
    
    public boolean isOnLimit(Point point)
    {
        return this.IsMoreThanThree() && (point.getX() == this.x + (this.width / 2) || point.getY() == this.y + (this.height / 2));
    }
    
    private void PushToChildren(Point point)
    {
        if (this.ChildIsEmpty())
            this.SplitInFourSide();
        
        this.childNodes.get(Side.TL).Push(point);
        this.childNodes.get(Side.TR).Push(point);
        this.childNodes.get(Side.BL).Push(point);
        this.childNodes.get(Side.BR).Push(point);
    }
    
    private void SplitInFourSide()
    {
        this.CreateSides();
        
        for (Point point : this.points)
        {
            this.childNodes.get(Side.TL).Push(point);
            this.childNodes.get(Side.TR).Push(point);
            this.childNodes.get(Side.BL).Push(point);
            this.childNodes.get(Side.BR).Push(point);
        }
        this.points.clear();
    }
    
    public void CreateSides()
    {
        double middleX = this.x + (this.width / 2);
        double middleY = this.y + (this.height / 2);
        double middleWidth = this.width / 2;
        double middleHeight = this.height / 2;
        
        this.childNodes.put(Side.TL, new Node(this.x, this.y, middleWidth, middleHeight, depth + 1));
        this.childNodes.put(Side.TR, new Node(middleX, this.y, middleWidth, middleHeight, depth + 1));
        this.childNodes.put(Side.BL, new Node(this.x, middleY, middleWidth, middleHeight, depth + 1));
        this.childNodes.put(Side.BR, new Node(middleX, middleY, middleWidth, middleHeight, depth + 1));
    }
    
    public int countPoint()
    {
        return this.points.size();
    }
     
    public String getNode()
    {
        return "Node: (" + this.x + "," + this.y + ")" + "-(" + this.width + "," + this.height + ")";
    }

    public int getDepth() {
        return depth;
    }

    public void increaseDepth() {
        this.depth += 1;
    }

    public List<Point> getPoints() {
        return points;
    }

    public Map<Side, Node> getChildNodes() {
        return childNodes;
    }
}
