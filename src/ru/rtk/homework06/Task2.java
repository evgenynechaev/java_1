package ru.rtk.homework06;

import java.util.Scanner;

public class Task2 {
    Tvset tv;

    public Task2() {
        System.out.println("Задача 2. Класс Телевизор");
        System.out.println("-------------------------");
        System.out.println();
        while(true) {
            Scanner scanner = new Scanner(System.in);

            System.out.println();
            System.out.println("1) Загрузить тестовые данные талевизора и каналов");
            System.out.println("2) Включить телевизор");
            System.out.println("3) Выключить телевизор");
            System.out.println("4) Список каналов");
            System.out.println("5) Текущий канал");
            System.out.println("6) Сменить канал");
            System.out.println("7) Добавить канал");
            System.out.println("8) Сменить марку телевизора");
            System.out.println("9) Сменить модель телевизора");
            System.out.println("0) Очистить данные");
            System.out.println("Q) Выход");
            System.out.print("Выберите пункт: ");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("1")) {
                this.fillData();
            }

            /*
            if (choice.equals("2")) {
                this.fillCustomData();
            }

            if (choice.equals("3")) {
                this.showProducts();
            }

            if (choice.equals("4")) {
                this.showPersons();
            }

            if (choice.equals("5")) {
                this.menuShopping();
            }

            if (choice.equals("6")) {
                this.manualShopping();
            }

            if (choice.equals("9")) {
                this.clearData();
            }

            if (choice.equals("0")) {
                break;
            }
            */
        }

        // Проверка работы сообщений

    }

    private void fillData() {
        this.tv = new Tvset("Samsung", "TV-1000", 40);
        this.tv.addChannel("Первый канал", 1, "Новости", 8.0, 1000);
        this.tv.addChannel("Россия", 2, "Кино", 7.0, 500);
        this.tv.addChannel("НТВ", 4, "Сериал", 6.0, 200);
        this.tv.addChannel("Пятый канал", 5, "Обзор", 5.0, 100);
    }

    private void tvOn() {
        this.tv.tvOff();
        this.tv.switchActiveChannel();
        this.tv.tvOn();
    }
}
