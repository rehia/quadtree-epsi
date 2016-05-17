/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author Gautier
 */
public class Repere {

    private float X;
    private float Y;
    private float _range;

    public Repere() {
        this.X = 0;
        this.Y = 0;
        this._range = 100;
    }

    public Repere(float x, float y, float range) {
        this.X = x;
        this.Y = y;
        this._range = range;
    }

    public Repere(String newLocation, Repere oldRepere) {

        this._range = oldRepere.getRange() / 2;
        switch (newLocation) {
            case "upperLeft":
                this.setX(oldRepere.getX());
                this.setY(oldRepere.getY());
                break;
            case "upperRight":
                this.setX(oldRepere.getX() + oldRepere.getRange() / 2);
                this.setY(oldRepere.getY());
                break;
            case "lowerLeft":
                this.setX(oldRepere.getX());
                this.setY(oldRepere.getY() + oldRepere.getRange() / 2);
                break;
            case "lowerRight":
                this.setX(oldRepere.getX() + oldRepere.getRange() / 2);
                this.setY(oldRepere.getY() + oldRepere.getRange() / 2);
                break;
        }
    }

    public float getRangeY() {
        return (float) this.getY() + this.getRange();
    }

    public float getRangeX() {
        return (float) this.getX() + this.getRange();
    }

    public boolean isInYRange(float y) {
        return y < this.getRangeY() && y >= this.getY();
    }

    public boolean isInXRange(float x) {
        return x < this.getRangeX() && x >= this.getX();
    }

    public String affiche() {
        return this.X + ";" + this.Y;
    }

    public Repere(float x, float y) {
        this.X = x;
        this.Y = y;
    }

    /**
     * @return the X
     */
    public float getX() {
        return X;
    }

    /**
     * @param X the X to set
     */
    public void setX(float X) {
        this.X = X;
    }

    /**
     * @return the Y
     */
    public float getY() {
        return Y;
    }

    /**
     * @param Y the Y to set
     */
    public void setY(float Y) {
        this.Y = Y;
    }

    /**
     * @return the _range
     */
    public float getRange() {
        return _range;
    }

    /**
     * @param _range the _range to set
     */
    public void setRange(float _range) {
        this._range = _range;
    }

}
