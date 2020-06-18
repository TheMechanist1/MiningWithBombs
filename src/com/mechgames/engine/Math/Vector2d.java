package com.mechgames.engine.Math;

import java.util.Objects;

public class Vector2d {
    private double doubleX, doubleY;

    public Vector2d(double doubleX, double doubleY) {
        this.doubleX = doubleX;
        this.doubleY = doubleY;
    }

    public double getX() {
        return doubleX;
    }

    public void setX(double doubleX) {
        this.doubleX = doubleX;
    }

    public double getY() {
        return doubleY;
    }

    public void setY(double doubleY) {
        this.doubleY = doubleY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2d vector2d = (Vector2d) o;
        return Double.compare(vector2d.doubleX, doubleX) == 0 &&
                Double.compare(vector2d.doubleY, doubleY) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(doubleX, doubleY);
    }

    public void add(Vector2d v2d) {
        this.setX(this.getX() + v2d.getX());
        this.setY(this.getY() + v2d.getY());
    }

    public String toString() {
        return "[" + getX() + ", " + getY() + "]";
    }

}
