package ru.rtk.homework06;

import java.util.ArrayList;
import java.util.Scanner;

public class Task1 {
    private final ArrayList<Product> products = new ArrayList<>();
    private final ArrayList<Person> persons = new ArrayList<>();

    public Task1() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Задача 1. Покупатели-Продукты");
        System.out.println("-----------------------------");
        System.out.println();
        System.out.println("Введите Покупателей в формате 'Покупатель1 = Деньги1; Покупатель2 = Деньги2'");
        String personsText = scanner.nextLine();
        this.addPersonString(personsText);

        System.out.println();
        System.out.println("Введите Продукты в формате 'Продукт1 = Стоимость1; Продукт2 = Стоимость2'");
        String productsText = scanner.nextLine();
        this.addProductString(productsText);

        this.shopping();
    }

    private void addPersonString(String input) {
        String[] list = input.split(";");
        System.out.println();
        for(String item: list)
        {
            Person person = new Person(item);
            if(person.isValid()) {
                this.persons.add(person);
                System.out.printf("Добавлен покупатель '%s'\n", person);
            }
        }
    }

    private void addProductString(String input) {
        String[] list = input.split(";");
        System.out.println();
        for(String item: list)
        {
            Product product = new Product(item);
            if(product.isValid())
            {
                this.products.add(product);
                System.out.printf("Добавлен продукт '%s'\n", product);
            }
        }
    }

    private void shopping() {
        boolean error = false;

        System.out.println("Совершите покупки (формат: Покупатель Продукт)");
        System.out.println("END - закончить покупки");

        Scanner scanner = new Scanner(System.in);
        while (true) {

            if(error) {
                System.out.println("ОШИБКА: Строка должна быть формата 'Покупатель Товар'");
                System.out.print("Введите строку правильно ");

                error = false;
            }

            String text = scanner.nextLine().trim();
            if (text.equalsIgnoreCase("end")) {
                break;
            }

            Person selectedPerson = null;
            String productName = "";
            for(Person person: this.persons) {
                String personName = person.getName();
                if(text.startsWith(personName)) {
                    selectedPerson = person;
                    System.out.printf("Выбран покупатель '%s'\n", selectedPerson.getName());
                    productName = text.substring(personName.length()).trim();
                    System.out.println(productName);
                    break;
                }
            }

            if (selectedPerson == null) {
                System.out.printf("ОШИБКА: Покупатель в строке '%s' не найден. Повторите ввод\n", text);
                error = true;
                continue;
            }

            if (productName.isEmpty()) {
                System.out.printf("ОШИБКА: Продукт в строке '%s' не найден. Повторите ввод\n", text);
                error = true;
                continue;
            }

            Product selectedProduct = null;
            for(Product product: this.products) {
                if(product.getName().equals(productName)) {
                    selectedProduct = product;
                    System.out.printf("Выбран продукт '%s'\n", selectedProduct.getName());
                    break;
                }
            }

            if (selectedProduct == null) {
                System.out.printf("ОШИБКА: Товар в строке '%s' не найден. Повторите ввод\n", text);
                error = true;
                continue;
            }

            selectedPerson.shopping(selectedProduct);
        }
    }
}
