package ru.rtk.homework08;

public interface PowerfulSetInterface {
    public <T> Set<T> intersection(Set<T> set1, Set<T> set2);

    public <T> Set<T> union(Set<T> set1, Set<T> set2);

    public <T> Set<T> relativeComplement(Set<T> set1, Set<T> set2);
}
