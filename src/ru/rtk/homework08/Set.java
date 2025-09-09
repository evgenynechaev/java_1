package ru.rtk.homework08;

import java.util.*;
import java.util.function.Consumer;

public class Set<T> implements Iterable<T> {
    private final List<T> list = new ArrayList<T>();

    public boolean add(T item) {
        return list.add(item);
    }

    public boolean addAll(Collection<? extends T> items) {
        return list.addAll(items);
    }

    public boolean addAll(int index, Collection<? extends T> items) {
        return list.addAll(index, items);
    }

    public boolean contains(T object) {
        return list.contains(object);
    }

    @Override
    public Iterator<T> iterator() {
        return this.list.iterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }

    @Override
    public String toString() {
        return String.format("%s",
                !this.list.isEmpty() ? String.format("%s", this.delimiterString(this.list)) : "");
    }

    private String delimiterString(List<T> list) {
        return String.join(", ", list.stream().map(Object::toString).toList());
    }
}
