package ru.rtk.homework07;

import java.util.Objects;

abstract class Product {

    protected String name = null;
    protected double price = -1;

    protected Product() {
    }

    protected Product(String text) {
        String[] list = text.split("=");
        if (list.length != 2) {
            System.out.printf("ОШИБКА: Строка '%s' должна быть формата 'Продукт = стоимость'\n", text);
            return;
        }

        this.setName(list[0].trim());
        this.setPrice(list[1].trim());
    }

    protected void setName(String text) {
        String name = text.trim();
        if (name.isEmpty()) {
            System.out.println("ОШИБКА: Название не может быть пустой строкой");
            return;
        }
        this.name = name;
    }

    protected void setPrice(String text) {
        double price;
        try {
            price = Double.parseDouble(text.trim());
        } catch (NumberFormatException | NullPointerException nfe) {
            System.out.printf("Неправильно введена цена '%s'.\n", text);
            return;
        }

        this.price = price;
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

        if(object instanceof Product entity) {
            return Objects.equals(this.name, entity.name) &&
                    Double.compare(this.price, entity.price) == 0;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.price);
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public boolean isValid() {
        return this.isValidName(this.name) && this.isValidPrice(this.price);
    }

    private boolean isValidName(String name) {
        return name != null && !name.isEmpty();
    }

    private boolean isValidPrice(double price) {
        return price >= 0;
    }
}
