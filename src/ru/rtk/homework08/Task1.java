package ru.rtk.homework08;

import java.util.ArrayList;
import java.util.Collections;

public class Task1 {

    public Task1() {
        System.out.println();
        System.out.println("Задание 1");

        ArrayList<String> list = new ArrayList<String>() {{
            add("один");
            add("два");
            add("три");
        }};
        Collections.addAll(list, "четыре", "пять");

        this.printArrayList(list);
    }

    public <T> void printArrayList(ArrayList<T> list) {
        list.forEach(System.out::println);
    }

}
