package model.util;

public abstract class Frontier<T> {
    public abstract boolean isEmpty();

    public abstract void put(T item);

    public abstract T get() throws FrontierException;

    public abstract void display();
}
