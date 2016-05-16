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
        Node node = new Node(new Coordinates(0,0),25,25);
        node.createSons();
    }
    
}
