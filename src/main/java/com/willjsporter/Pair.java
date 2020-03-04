package com.willjsporter;

public abstract class Pair<T extends Comparable<? super T>> implements Comparable<Pair<T>> {
    private T x;
    private T y;

    private Pair(T x, T y) {
        this.x = x;
        this.y = y;
    }

    public Pair() { }

    public T getX() {
        return x;
    }

    public T getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;
        Pair<T> pair = (Pair<T>) o;
        return getX() == pair.getX() &&
            getY() == pair.getY();
    }

    @Override
    public int compareTo(Pair<T> p) {
        return (x.compareTo(p.getX()) + y.compareTo(p.getY()))/2;
    }
}
