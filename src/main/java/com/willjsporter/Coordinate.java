package com.willjsporter;

import java.util.Objects;

public class Coordinate extends Pair<Integer> {
    private int x;
    private int y;

    private Coordinate(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    public static Coordinate of(int x, int y) {
        return new Coordinate(x, y);
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public int manhattanLength() {
        return Math.abs(this.x) + Math.abs(this.y);
    }

    @Override
    public String toString() {
        return "Coordinate{" +
            "x=" + x +
            ", y=" + y +
            '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate)) return false;
        Coordinate pair = (Coordinate) o;
        return getX().equals(pair.getX()) &&
            getY().equals(pair.getY());
    }

    @Override
    public int compareTo(Pair anotherCoord) {
        return Integer.compare(this.manhattanLength(), ((Coordinate) anotherCoord).manhattanLength());
    }

    public Coordinate add(Coordinate anotherCoord) {
        return Coordinate.of(x + anotherCoord.getX(), y + anotherCoord.getY());
    }
}
