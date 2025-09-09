package ru.rtk.homework08;

import java.util.Scanner;

public class Task2 {

    public Task2() {
        System.out.println();
        System.out.println("Задание 2");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите первую строку: ");
        String first = scanner.nextLine();
        System.out.print("Введите вторую строку: ");
        String second = scanner.nextLine();
        // String first = "Бейсбол";
        // String second = "бобслей";
        // String first = "Героин";
        // String second = "регион";
        // String first = "Клоака";
        // String second = "околка";
        String analyse = "не анаграма";
        if(this.isAnagram(first, second)) {
            analyse = "являются анаграмой";
        }
        System.out.printf("Слова %s и %s %s.\n", first, second, analyse);
    }

    private boolean isAnagram(String first, String second) {
        String firstSorted = this.sort(first);
        String secondSorted = this.sort(second);
        return firstSorted.equals(secondSorted);
    }

    private String sort(String word) {
        StringBuilder result = new StringBuilder();
        word.toLowerCase().chars().sorted().forEach(result::appendCodePoint);
        return result.toString();
    }
}
