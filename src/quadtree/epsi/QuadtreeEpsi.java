/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadtree.epsi;

/**
 *
 * @author mathieu
 */
public class QuadtreeEpsi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Node node = new Node(new Coordinates(1,1),100,100);
                node.addPoint(new Coordinates(10, 10));
       node.addPoint(new Coordinates(60, 20));
       node.addPoint(new Coordinates(80, 80));
       node.addPoint(new Coordinates(20, 40));
       node.addPoint(new Coordinates(45, 50));
       node.getPointDepth(new Coordinates(80, 80));
    }
    
}
