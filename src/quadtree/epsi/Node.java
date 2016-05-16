 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadtree.epsi;

import java.util.ArrayList;

/**
 *
 * @author mathieu
 */
public class Node {

    private ArrayList<Node> sons;
    private ArrayList<Coordinates> points;
    private Coordinates origin;
    private int lenght;
    private int width;

    Node(Coordinates center, int lenght, int width) {
        this.origin = center;
        this.lenght = lenght;
        this.width = width;
        points = new ArrayList<>();
        sons = new ArrayList<>();

    }

    public ArrayList<Node> getSons() {
        return sons;
    }

    public void createSons() {
        int newL=lenght/2;
        int newW=width/2;
        if(lenght%newL!=0){
            newL++;
        }
         if(lenght%newW!=0){
            newW++;
        }
        sons.add(new Node(origin, newL,newW));
        sons.add(new Node(new Coordinates(getOriginX()+newL++, getOriginY() ), lenght / 2,newW));
        sons.add(new Node(new Coordinates( getOriginX(),  getOriginY()+newW),newL,width/2));
        sons.add(new Node(new Coordinates( getOriginX()+newL++, getOriginY()+newW),lenght / 2,width/2));
    }



    public int getWidthInSons(int idSon) {
        return this.sons.get(idSon).getWidth();
    }

    public Coordinates getOrigin() {
        return origin;
    }
public Coordinates getOriginInSons(int idSon) {
        return sons.get(idSon).getOrigin();
    }
    public int getWidth() {
        return width;
    }

    public int getLenghtInSons(int idSon) {
        return this.sons.get(idSon).getLenght();
    }

    public int getOriginX() {
        return origin.getX();
    }
    public int getOriginY() {
        return origin.getY();
    }

    public ArrayList<Coordinates> getPoints() {
        return points;
    }

    public int getLenght() {
        return lenght;
    }

    void addPoint(Coordinates point) {
       
    }

}
