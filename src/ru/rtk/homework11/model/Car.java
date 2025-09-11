package ru.rtk.homework11.model;

import java.io.Serializable;
import java.util.Scanner;

public class Car implements Serializable {
    private String number;
    private String model;
    private String color;
    private long mileage;
    private long cost;

    public Car(String pattern) {
        if(!this.parsePattern(pattern)) {
            System.out.printf("Данные '%s' не распознаны\n", pattern);
        }
    }

    public Car(String number, String model, String color, long mileage, long cost) {
        this.number = number;
        this.model = model;
        this.color = color;
        this.mileage = mileage;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %d, %d",
                this.number,
                this.model,
                this.color,
                this.mileage,
                this.cost
        );
    }

    public boolean isValid() {
        return number != null && !number.isEmpty() &&
                model != null && !model.isEmpty() &&
                color != null && !color.isEmpty() &&
                mileage >= 0 && cost > 0;
    }


    private boolean parsePattern(String input) {
        String[] list = input.split("\\|");
        if (list.length != 5) {
            System.out.println("Строка не соответствует шаблону 'Номер|Модель|Цвет|Пробег|Цена'");
            return false;
        }
        this.number = list[0].trim();
        this.model = list[1].trim();
        this.color = list[2].trim();
        this.mileage = Long.parseLong(list[3]);
        this.cost = Long.parseLong(list[4]);
        return true;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public long getMileage() {
        return this.mileage;
    }

    public void setMileage(long mileage) {
        this.mileage = mileage;
    }

    public long getCost() {
        return this.cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public void edit() {
        while(true) {
            Scanner scanner = new Scanner(System.in);

            System.out.printf("Автомобиль: %s\n", this);
            System.out.println("Выберите параметр для редактирования:");
            System.out.printf("1) Номер (%s)\n", this.number);
            System.out.printf("2) Модель (%s)\n", this.model);
            System.out.printf("3) Цвет (%s)\n", this.color);
            System.out.printf("4) Пробег (%d)\n", this.mileage);
            System.out.printf("5) Цена (%d)\n", this.cost);
            System.out.println("Q) Выход в меню");
            System.out.print("Выберите пункт: ");
            String choice = scanner.nextLine().trim();
            if (choice.equals("1")) {
                this.setNumber(this.inputString("Введите новый номер авто (%s): ", this.number));
                continue;
            }
            if (choice.equals("2")) {
                this.setModel(this.inputString("Введите новую модель авто (%s): ", this.model));
                continue;
            }
            if (choice.equals("3")) {
                this.setColor(this.inputString("Введите новый цвет авто (%s): ", this.color));
                continue;
            }
            if (choice.equals("4")) {
                this.setMileage(this.inputLong("Введите новый пробег авто (%d): ", this.mileage));
                continue;
            }
            if (choice.equals("5")) {
                this.setCost(this.inputLong("Введите новую стоимость авто (%d): ", this.cost));
                continue;
            }
            if (choice.equalsIgnoreCase("Q")) {
                break;
            }
        }
    }

    private String inputString(String title, String oldValue) {
        System.out.printf(title, oldValue);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            return oldValue;
        }

        return input;
    }

    private long inputLong(String title, long oldValue) {
        System.out.printf(title, oldValue);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();
        if (!input.isEmpty()) {
            try {
                return Long.parseLong(input);
            } catch (NumberFormatException | NullPointerException ignored) {
                System.out.printf("Неправильно введено число '%s'.\n", input);
            }
        }

        return oldValue;
    }
}
