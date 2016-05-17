/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadtree;

import model.Node;
import model.Point;

/**
 *
 * @author Tuan Nguyen <tuan.nguyen at tuannguyen.epsi.fr>
 */
public class Quadtree {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // new root node   
        Node node = new Node(0,0,100,100);
        
        //generate points
        for(int i = 0;i <= 100; i++){
            int x = (int) Math.floor(Math.random() * 101);
            int y = (int) Math.floor(Math.random() * 101);
            node.push(new Point(x,y));
        }
        //end of generate points
        
        //point to test
        Point point = new Point(5,5);
        node.push(point);
        //EO point to test
        
        //interface print
        System.out.println("\n****************** QUADTREE GRAPHIQUE *******************\n");
        node.toString(1,"");
        System.out.println("\n****************** END OF QUADTREE GRAPHIQUE *******************\n");
        //EO interface print
        
        //questions print
        System.out.println("\n$$$$$$$$ QUADTREE ANSWERS $$$$$$$$\n");
        System.out.println("\nLa profondeur de l\'arbre est : "+node.getMaxDepth());
        System.out.println("\nLa profondeur du point "+point.toString()+
                " est : " + node.getDepthLevelByPoint(point));
        System.out.println("\nLes voisins de "+point.toString()+
                " sont : "+ node.getNeighboursOfPoint(point)+"\n");
        System.out.println("\n$$$$$$$$ END OF QUADTREE ANSWERS $$$$$$$$");
        //EO questions print
    }
}
