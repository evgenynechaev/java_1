package ru.rtk.homework08;

import java.util.Arrays;

public class Task3 {
    public Task3() {
        System.out.println();
        System.out.println("Задание 3");

        Set<Integer> elements1 = new Set<>();
        elements1.addAll(Arrays.asList(1, 2, 3));

        Set<Integer> elements2 = new Set<>();
        elements2.addAll(Arrays.asList(0, 1, 2, 4));

        Set<String> elementsStrings1 = new Set<>() {{
            add("один");
            add("два");
            add("три");
        }};
        Set<String> elementsStrings2 = new Set<>() {{
            add("ноль");
            add("один");
            add("два");
            add("четыре");
        }};

        PowerfulSet ps = new PowerfulSet();

        System.out.println();
        System.out.println("Integer Intersection:");
        Set<Integer> elementsIntersection = ps.intersection(elements1, elements2);
        System.out.println(elementsIntersection);

        System.out.println();
        System.out.println("Integer Union");
        Set<Integer> elementsUnion = ps.union(elements1, elements2);
        System.out.println(elementsUnion);

        System.out.println();
        System.out.println("Integer Relative Complement");
        Set<Integer> elementsRelative = ps.relativeComplement(elements1, elements2);
        System.out.println(elementsRelative);

        System.out.println();
        System.out.println("String Intersection:");
        Set<String> elementsStringsIntersection = ps.intersection(elementsStrings1, elementsStrings2);
        System.out.println(elementsStringsIntersection);

        System.out.println();
        System.out.println("String Union");
        Set<String> elementsStringsUnion = ps.union(elementsStrings1, elementsStrings2);
        System.out.println(elementsStringsUnion);

        System.out.println();
        System.out.println("String Relative Complement");
        Set<String> elementsStringsRelative = ps.relativeComplement(elementsStrings1, elementsStrings2);
        System.out.println(elementsStringsRelative);
    }
}
