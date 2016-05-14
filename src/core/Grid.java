package core;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by J on 30/04/2016.
 */
public class Grid {

    private ArrayList<Dot> _listOfDots;
    private Repere _repereGrid;
    private Grid _upperLeft;
    private Grid _upperRight;
    private Grid _lowerLeft;
    private Grid _lowerRight;

    public Grid() {
        this._repereGrid = new Repere();
        _listOfDots = new ArrayList<>();
    }

    //constructor for subgrids
    public Grid(Repere repere) {
        this._repereGrid = repere;
        _listOfDots = new ArrayList<>();
    }

    public boolean addDot(Dot dotToAdd) {
        if (GridHasSubGrid()) {
            return this.addDotToSubGrid(dotToAdd);
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
        if (this.dotIsInRange(dotToAdd)) {
            _listOfDots.add(dotToAdd);
            return true;
        } else {
            return false;
        }

    }

    public boolean dotIsInRange(Dot dot) {
        return (this.dotIsInXRange(dot) && this.dotIsInYRange(dot));
    }

    private boolean dotIsInYRange(Dot dot) {
        return this._repereGrid.isInYRange(dot.getY());
    }

    private boolean dotIsInXRange(Dot dot) {
        return this._repereGrid.isInXRange(dot.getX());
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
        _upperLeft = new Grid(new Repere("upperLeft", this._repereGrid));
        _upperRight = new Grid(new Repere("upperRight", this._repereGrid));
        _lowerLeft = new Grid(new Repere("lowerLeft", this._repereGrid));
        _lowerRight = new Grid(new Repere("lowerRight", this._repereGrid));
    }

    public String afficheGrid() {
        String affichage = "";
        affichage += "grille de range " + this._repereGrid.getRange() + " qui commence : " + this._repereGrid.affiche();
        for (Dot d : this._listOfDots) {
            affichage += "\n";
            affichage += d.affiche();
        }

        if (this._listOfDots.isEmpty()) {
            affichage += "\nempty";
        }
        if (GridHasSubGrid()) {
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

    public Grid getUpperLeft() {
        return this._upperLeft;
    }

    public int numberOfDots() {
        return _listOfDots.size();
    }

    public Grid getUpperRight() {
        return this._upperRight;
    }

    public int depthOf(Dot dot) {

        if (GridHasNoSubGrid()) {
            return this.doesNotOwn(dot) ? 0 : 1;
        } else {
            return depthOfSubGrid(dot);
        }
    }

    public boolean GridHasSubGrid() {
        return this._upperLeft != null;
    }

    public boolean GridHasNoSubGrid() {
        return this._upperLeft == null;
    }

    public int depthOfSubGrid(Dot dot) {
        if (this._upperLeft.dotIsInRange(dot)) {
            return 1 + this._upperLeft.depthOf(dot);
        }
        if (this._upperRight.dotIsInRange(dot)) {
            return 1 + this._upperRight.depthOf(dot);
        }
        if (this._lowerLeft.dotIsInRange(dot)) {
            return 1 + this._lowerLeft.depthOf(dot);
        }
        if (this._lowerRight.dotIsInRange(dot)) {
            return 1 + this._lowerRight.depthOf(dot);
        }
        return 0;
    }

    public float getRange() {
        //use to test method : SubgridIsHalfRange
        return this._repereGrid.getRange();
    }

    public ArrayList<Dot> findNeighbourhood(Dot dot) {
        ArrayList<Dot> Neighbour = this._listOfDots;
        if (GridHasSubGrid()) {
            return findNeighbourhoodInSubGrids(dot);
        }
        if (Neighbour.contains(dot)) {
            Neighbour.remove(dot);
            return Neighbour;
        } else {
            return new ArrayList<Dot>();
        }
    }

    private ArrayList<Dot> findNeighbourhoodInSubGrids(Dot dot) {
        if (_upperLeft.dotIsInRange(dot)) {
            return _upperLeft.findNeighbourhood(dot);
        }
        if (_upperRight.dotIsInRange(dot)) {
            return _upperRight.findNeighbourhood(dot);
        }
        if (_lowerLeft.dotIsInRange(dot)) {
            return _lowerLeft.findNeighbourhood(dot);
        }
        if (_lowerRight.dotIsInRange(dot)) {
            return _lowerRight.findNeighbourhood(dot);
        }
        return new ArrayList<Dot>();
    }

}
