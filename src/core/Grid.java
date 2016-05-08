package core;

import java.awt.Point;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Iterator;

/**
 * Created by J on 30/04/2016.
 */
public class Grid {

    private ArrayList<Dot> _listOfDots;
    private Repere _repereDebutGrid;
    private float _range;
    private Grid _upperLeft;
    private Grid _upperRight;
    private Grid _lowerLeft;
    private Grid _lowerRight;

    public Grid() {
        this._range = 100;
        this._repereDebutGrid = new Repere(0, 0);
        _listOfDots = new ArrayList<>();
    }

    public Grid(float range, Repere repere) {
        _range = range;
        this._repereDebutGrid = repere;
        _listOfDots = new ArrayList<>();
    }

    public boolean addDot(Dot dotToAdd) {

        if (this._upperLeft != null) {
            System.err.println("ventilation!");
            this.addDotToSubGrid(dotToAdd);
        }

        if (this.addDotWithinRange(dotToAdd)) {

            if (this.listeOfDotIsFull()) {
                return split();
            } else {
                return true;
            }

        }
        return false;
    }

    public boolean listeOfDotIsFull() {
        return this.numberOfDots() > 4;
    }

    public boolean addDotWithinRange(Dot dotToAdd) {
        System.err.println(dotToAdd.getY());
        if (this.dotIsInRange(dotToAdd)) {
            System.err.println("dot added" + dotToAdd.getY());
            _listOfDots.add(dotToAdd);
            return true;
        } else {
            return false;
        }

    }

    public boolean dotIsInRange(Dot dot) {
        if (this.dotIsInXRange(dot) && this.dotIsInYRange(dot)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean dotIsInYRange(Dot dot) {
        return dot.getY() < this.getRangeY() && dot.getY() >= this._repereDebutGrid.getY();
    }

    private boolean dotIsInXRange(Dot dot) {
        return dot.getX() < this.getRangeX() && dot.getX() >= this._repereDebutGrid.getX();
    }

    private boolean split() {
        this.createSubGrids();
        this.ventileMyDotsToSubGrid();
        return true;
    }

    private void ventileMyDotsToSubGrid() {
        Iterator<Dot> parcours_list_of_dots = _listOfDots.iterator();
        while (parcours_list_of_dots.hasNext()) {
            Dot dotToAdd = parcours_list_of_dots.next();
            if (addDotToSubGrid(dotToAdd)) {
                parcours_list_of_dots.remove();
            }
        }
    }

    public boolean addDotToSubGrid(Dot dotToAdd) {
        if (this._upperLeft.dotIsInRange(dotToAdd)) {
            return this._upperLeft.addDot(dotToAdd);

        }
        if (this._upperRight.dotIsInRange(dotToAdd)) {
            return this._upperRight.addDot(dotToAdd);

        }
        if (this._lowerLeft.dotIsInRange(dotToAdd)) {
            return this._lowerLeft.addDot(dotToAdd);

        }
        if (this._lowerRight.dotIsInRange(dotToAdd)) {
            return this._lowerRight.addDot(dotToAdd);

        }
        return false;
    }

    private void createSubGrids() {
        _upperLeft = new Grid(this._range / 2, this._repereDebutGrid);
        _upperRight = new Grid(this._range / 2, this.makeRepere("upperRight"));
        _lowerLeft = new Grid(this._range / 2, this.makeRepere("lowerLeft"));
        _lowerRight = new Grid(this._range / 2, this.makeRepere("lowerRight"));
    }

    public Repere makeRepere(String locationNewGrid) {
        Repere newRepere = new Repere();
        switch (locationNewGrid) {
            case "upperRight":
                newRepere.setX(this._repereDebutGrid.getX() + this._range / 2);
                newRepere.setY(this._repereDebutGrid.getY());
                break;
            case "lowerLeft":
                newRepere.setX(this._repereDebutGrid.getX());
                newRepere.setY(this._repereDebutGrid.getY() + this._range / 2);
                break;
            case "lowerRight":
                newRepere.setX(this._repereDebutGrid.getX() + this._range / 2);
                newRepere.setY(this._repereDebutGrid.getY() + this._range / 2);
                break;

        }
        return newRepere;
    }
    
    public String afficheGrid(){
       String affichage = "";
        affichage += "grille de range " + this._range + " qui commence : " + this._repereDebutGrid.affiche();
       for(Dot d : this._listOfDots){
           affichage += "\n";
        affichage += d.affiche();
        }
       if(this._listOfDots.isEmpty()){
           affichage+="\nempty";
       }
       if(this._upperLeft != null){
          affichage += "\n" + this._upperLeft.afficheGrid();
          affichage += "\n" + this._lowerLeft.afficheGrid();
          affichage += "\n" + this._lowerRight.afficheGrid();
       }
       return affichage;
    }

    public boolean doesNotOwn(Dot dot) {
        for (Dot dotInList : _listOfDots) {
            return dotInList.isNotTwin(dot);
        }
        return true;
    }

    public ArrayList<Dot> getListOfDots() {
        return _listOfDots;
    }

    private float getRangeY() {
        return (float) this._repereDebutGrid.getY() + this._range;
    }

    public float getRangeX() {
        return (float) this._repereDebutGrid.getX() + this._range;
    }

    public Grid getUpperLeft() {
        return this._upperLeft;
    }

    public float getRange() {
        return this._range;
    }

    public int numberOfDots() {
        return _listOfDots.size();
    }

    public Grid getUpperRight() {
        return this._upperRight;
    }

}
