package com.mechgames.engine.math;

import java.util.Objects;

public class Vector2d {
    private double x, y;

    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2d vector2d = (Vector2d) o;
        return Double.compare(vector2d.x, x) == 0 &&
                Double.compare(vector2d.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public void add(Vector2d v2d) {
        this.setX(this.getX() + v2d.getX());
        this.setY(this.getY() + v2d.getY());
    }

    public String toString() {
        return "[" + getX() + ", " + getY() + "]";
    }

}
