package ru.rtk.homework08;

import java.util.HashSet;

public class PowerfulSet implements PowerfulSetInterface {

    public <T> Set<T> intersection(Set<T> set1, Set<T> set2) {
        // Возвращает пересечение двух наборов
        // Пример: set1 = {1, 2, 3}, set2 = {0, 1, 2, 4}. Вернуть {1, 2}

        Set<T> result = new Set<>();
        for(T item: set1) {
            if(set2.contains(item)) {
                result.add(item);
            }
        }

        return result;
    }

    public <T> Set<T> union(Set<T> set1, Set<T> set2) {
        // Возвращает объединение двух наборов
        // Пример: set1 = {1, 2, 3}, set2 = {0, 1, 2, 4}. Вернуть {0, 1, 2, 3, 4}

        HashSet<T> hs = new HashSet<>();
        set1.forEach(hs::add);
        set2.forEach(hs::add);

        Set<T> result = new Set<>();
        hs.forEach(result::add);

        return result;
    }

    public <T> Set<T> relativeComplement(Set<T> set1, Set<T> set2) {
        // Возвращает элементы первого набора без тех, которые находятся также и во втором наборе.
        // Пример: set1 = {1, 2, 3}, set2 = {0, 1, 2, 4}. Вернуть {3}

        HashSet<T> hs = new HashSet<>();
        set1.forEach(hs::add);
        set2.forEach(hs::remove);

        Set<T> result = new Set<>();
        hs.forEach(result::add);

        return result;
    }
}
