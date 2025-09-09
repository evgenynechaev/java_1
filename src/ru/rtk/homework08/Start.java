package ru.rtk.homework08;

import java.util.Scanner;

public class Start {

    public Start() {
        System.out.println("       Домашнее задание по темам");
        System.out.println("'Generics, Коллекции'");
        System.out.println("---------------------");
        while(true) {
            Scanner scanner = new Scanner(System.in);

            System.out.println();
            System.out.println("1) Задание 1");
            System.out.println("2) Задание 2");
            System.out.println("3) Задание 3");
            System.out.println("Q) Выход");
            System.out.print("Выберите пункт: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                new Task1();
            }

            if (choice.equals("2")) {
                new Task2();
            }

            if (choice.equals("3")) {
                new Task3();
            }

            if (choice.equalsIgnoreCase("Q")) {
                break;
            }
        }
    }
}
