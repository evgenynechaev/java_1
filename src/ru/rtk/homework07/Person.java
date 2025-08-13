package ru.rtk.homework07;

import java.util.*;

public class Person {
    private String name = null;
    private double wallet = -1;
    private final ArrayList<DiscountProduct> products = new ArrayList<>();

    private static final int MIN_NAME_SIZE = 3;

    public Person(String text) {
        String[] list = text.split("=");
        if (list.length != 2) {
            System.out.printf("ОШИБКА: Строка '%s' должна быть формата 'Покупатель = деньги'\n", text);
            return;
        }

        String name = list[0].trim();
        if (name.isEmpty()) {
            System.out.println("ОШИБКА: Имя не может быть пустым");
            return;
        }

        if (name.length() < MIN_NAME_SIZE) {
            System.out.printf("ОШИБКА: Имя не может быть короче %d символов\n", MIN_NAME_SIZE);
            return;
        }

        double wallet;
        try {
            wallet = Double.parseDouble(list[1].trim());
        } catch (NumberFormatException | NullPointerException nfe) {
            System.out.printf("ОШИБКА: Неправильно введено число в строке '(%s)'.\n", text);
            return;
        }

        if(wallet < 0) {
            System.out.println("ОШИБКА: Деньги не могут быть отрицательными.");
            return;
        }

        this.name = name;
        this.wallet = wallet;
    }

    @Override
    public String toString() {
        return String.format("%s, в кошельке %.2f.\n\t%s",
                this.name,
                this.wallet,
                !this.products.isEmpty() ? String.format("Покупки:\n\t%s", this.delimiterString(this.products)) : "Ничего не куплено");
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if(object instanceof Person person) {
            return Objects.equals(this.name, person.name) &&
                    Double.compare(this.wallet, person.wallet) == 0 &&
                    Arrays.equals(this.products.toArray(), person.products.toArray());
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.wallet) * Arrays.hashCode(this.products.toArray());
    }

    public String getName() {
        return this.name;
    }

    public boolean isValid() {
        return this.isValidName(this.name) && this.isValidWallet(this.wallet);
    }

    private boolean isValidName(String name) {
        return name != null && name.length() >= MIN_NAME_SIZE;
    }

    private boolean isValidWallet(double wallet) {
        return wallet >= 0;
    }

    public void shopping(DiscountProduct product) {
        if (product == null) {
            System.out.println("Продукт не найден и не может быть добавлен");
            return;
        }

        if(this.purchasedSum() + product.getPrice() <= this.wallet) {
            // DiscountProduct newProduct = new DiscountProduct(product.getName(), product.getPrice());
            // this.products.add(newProduct);
            this.products.add(product);
            System.out.printf("'%s' купил '%s'\n", this.name, product.getName());
        } else  {
            System.out.printf("%s не может позволить себе %s\n", this.name, product.getName());
        }
    }

    private double purchasedSum()
    {
        double sum = 0;
        for(Product product : this.products) {
            sum += product.getPrice();
        }
        return sum;
    }

    public void showPersonAndProducts() {
        System.out.printf("%s, денег %.2f\n", this.name, this.wallet);
        if(this.products.isEmpty()) {
            System.out.println("\tНичего не куплено");
        } else {
            System.out.println("\tПокупки:");
            for(Product product : this.products) {
                System.out.printf("\t%s\n", product);
            }
        }
    }

    public String delimiterString(ArrayList<DiscountProduct> list) {
        return String.join("\n\t", list.stream().map(Object::toString).toList());
    }

}
