package ru.rtk.att01;

import java.util.Objects;
import java.util.Scanner;

public class Product {
    private String name = null;
    private float price = -1;

    public Product() {
        this.inputName();
        this.inputPrice();
    }

    public Product(String text) {
        String[] list = text.split("=");
        if (list.length != 2) {
            System.out.printf("ОШИБКА: Строка '%s' должна быть формата 'Продукт = стоимость'\n", text);
            return;
        }

        String name = list[0].trim();
        if (name.isEmpty()) {
            System.out.println("ОШИБКА: Название не может быть пустой строкой");
            return;
        }

        float price;
        try {
            price = Float.parseFloat(list[1].trim());
        } catch (NumberFormatException | NullPointerException nfe) {
            System.out.printf("Неправильно введено число в строке '(%s)'.\n", text);
            return;
        }

        this.name = name;
        this.price = price;
    }

    public Product(String name, float price) {
        if(isValidName(name)) {
            this.name = name;
        } else {
            System.out.println("ОШИБКА: Неправильно введено имя товара");
            this.inputName();
        }

        if(isValidPrice(price)) {
            this.price = price;
        } else {
            System.out.println("ОШИБКА: Неправильно введена стоимость товара");
            this.inputPrice();
        }
    }

    @Override
    public String toString() {
        return String.format("%s, стоимость %.2f", this.name, this.price);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if(object instanceof Product product) {
            // System.out.println("Here");
            return Objects.equals(this.name, product.name) &&
                    Float.compare(this.price, product.price) == 0;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.price);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPrice() {
        return this.price;
    }

    public boolean isValid() {
        return this.isValidName(this.name) && this.isValidPrice(this.price);
    }

    private boolean isValidName(String name) {
        return name != null && !name.isEmpty();
    }

    private boolean isValidPrice(float price) {
        return price >= 0;
    }

    private void inputName() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.printf("Введите название продукта %s", String.format("(%s) ", this.name));
            String name = scanner.nextLine();
            if (this.isValidName(name) && this.isValidName(this.name))
            {
                System.out.println("ОШИБКА: Название не может быть пустой строкой");
                continue;
            }

            if (name.isEmpty()) {
                return;
            }

            this.setName(name);
            return;
        }
    }

    private void inputPrice() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.printf("Введите стоимость продукта %s", String.format("(%f) ", this.price));
            String input = scanner.nextLine();

            if (input.isEmpty() && this.price < 0)
            {
                System.out.println("ОШИБКА: Должна быть указана стоимость товара");
                continue;
            }

            if (input.isEmpty()) {
                return;
            }

            float price;
            try {
                price = Float.parseFloat(input);
            } catch (NumberFormatException nfe) {
                System.out.printf("Неправильно введено число (%s). Повторите ввод.\n", input);
                continue;
            } catch (NullPointerException npe) {
                System.out.println("Неправильно введено число. Повторите ввод.\n");
                continue;
            }

            if (!this.isValidPrice(price))
            {
                System.out.println("ОШИБКА: Стоимость товара не должна быть отрицательным числом");
                continue;
            }

            this.setPrice(price);
        }
    }

}
