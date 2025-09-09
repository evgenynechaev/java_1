package ru.rtk.homework08;

public interface PowerfulSetInterface<T> {
    public Set<T> intersection(Set<T> set1, Set<T> set2);

    public Set<T> union(Set<T> set1, Set<T> set2);

    public Set<T> relativeComplement(Set<T> set1, Set<T> set2);
}
