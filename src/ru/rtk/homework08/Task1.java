package ru.rtk.homework08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public void printArrayList(ArrayList<?> list) {
        for (Object item : list) {
            System.out.println(item);
        }
    }

}
