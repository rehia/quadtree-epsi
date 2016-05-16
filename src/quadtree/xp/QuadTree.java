/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadtree.xp;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Hippolyte
 */
public class QuadTree 
{ 
    private Map<String, Point> points;
    private Node rootNode;
    
    public QuadTree(int width, int height)
    {
        this.rootNode = new Node(0, 0, width, height, 0);
        this.points = new HashMap<>();
    }
    
    public void displayNodeFromPoint(Point pointToFind)
    {
        Node node = rootNode.findNodeByPoint(pointToFind);
        
        if (node != null)
        {
            System.out.println("\nProfondeur du point : " + node.getDepth());
            System.out.println("Liste des points de la même zone : ");
            for (Point point : node.getPoints())
            {
                System.out.println("-" + point.getPoint());
            }
        }
        else
            System.out.println("Aucun point n'existe à ces coordonnées");
    }
    
    public void display()
    {
        for (Entry<String, Point> entry : getPoints().entrySet())
        {
            System.out.println(entry.getValue().getPoint()); 
        }
        System.out.println(this.rootNode.getNode());
    }
    
    public void Push(Point point)
    {
        if (getPoints().containsValue(point)) 
            return;

        boolean pointAddInTree = this.getRootNode().Push(point);

        if (pointAddInTree)
            this.getPoints().put(point.getName(), point);
    }

    public Map<String, Point> getPoints() {
        return points;
    }

    public Node getRootNode() {
        return rootNode;
    }
    
    
}
