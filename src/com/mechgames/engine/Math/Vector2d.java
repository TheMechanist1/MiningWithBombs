package com.mechgames.engine.Math;

public class Vector2d {
    private int intX, intY;
    private double doubleX, doubleY;
    private boolean isInt = false;
    private boolean isDouble = false;

    public Vector2d(int intX, int intY) {
        isInt = true;
        this.intX = intX;
        this.intY = intY;
    }

    public Vector2d(double doubleX, double doubleY) {
        isDouble = true;
        this.doubleX = doubleX;
        this.doubleY = doubleY;
    }

    public double getDoubleX() {
        return doubleX;
    }

    public void setX(double doubleX) {
        this.doubleX = doubleX;
    }

    public double getDoubleY() {
        return doubleY;
    }

    public void setY(double doubleY) {
        this.doubleY = doubleY;
    }

    public int getIntX() {
        return intX;
    }

    public void setX(int intX) {
        this.intX = intX;
    }

    public int getIntY() {
        return intY;
    }

    public void setY(int intY) {
        this.intY = intY;
    }

    public String toString() {
        if(isInt) {
            return "[" + getIntX() + ", " + getIntY() + "]";
        } else if(isDouble) {
            return "[" + getDoubleX() + ", " + getDoubleY() + "]";
        } else {
            return "It should be impossible to get here but if it does, add 1 to this number: 0";
        }
    }

}
