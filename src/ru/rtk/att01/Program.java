package ru.rtk.att01;

import java.util.*;

public class Program {
    private final ArrayList<Product> products = new ArrayList<>();
    private final ArrayList<Person> persons = new ArrayList<>();

    public Program() {
        System.out.println("Промежуточная аттестация 1");
        System.out.println("--------------------------");
        while(true) {
            Scanner scanner = new Scanner(System.in);

            System.out.println();
            System.out.println("1) Загрузить тестовые данные");
            System.out.println("2) Ручной ввод информации");
            System.out.println("3) Просмотреть список продуктов");
            System.out.println("4) Просмотреть список покупателей");
            System.out.println("5) Совершить покупки (меню)");
            System.out.println("6) Совершить покупки (ручной ввод)");
            System.out.println("9) Очистить данные");
            System.out.println("0) Выход");
            System.out.print("Выберите пункт: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                this.fillTestData();
            }

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
        }

    }

    private void fillTestData() {
        /*
        Павел Андреевич = 10000; Анна Петровна = 2000; Борис = 10
        Хлеб = 40; Молоко = 60; Торт = 1000; Кофе растворимый = 879; Масло = 150
        */
        this.clearData();

        String products = "Хлеб = 40; Молоко = 60; Торт = 1000; Кофе растворимый = 879; Масло = 150; = ;;";
        this.addProductString(products);

        String persons = "Павел Андреевич = 10000; Анна Петровна = 2000; Борис = 10;";
        this.addPersonString(persons);

        this.showProducts();
        this.showPersons();
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

    private void fillCustomData() {
        System.out.println("Ручной ввод данных");

        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println();
            System.out.println("Введите Продукты в формате 'Продукт1 = Стоимость1; Продукт2 = Стоимость2'");
            System.out.println("END - закончить ввод");
            String text = scanner.nextLine();
            if (text.trim().equalsIgnoreCase("end")) {
                break;
            }

            this.addProductString(text);
        } while (true);


        do {
            System.out.println();
            System.out.println("Введите Покупателей в формате 'Покупатель1 = Деньги1; Покупатель2 = Деньги2'");
            System.out.println("END - закончить ввод");
            String text = scanner.nextLine();
            if (text.trim().equalsIgnoreCase("end")) {
                break;
            }

            this.addPersonString(text);
        } while (true);
    }

    private void showProducts() {
        if (this.products.isEmpty()) {
            System.out.println("\nСписок продуктов пустой");
        } else {
            System.out.println();
            System.out.println("Список продуктов");
            System.out.println("----------------");
        }

        for (Product product : this.products) {
            System.out.println(product);
        }
    }

    private void showPersons() {
        if (this.persons.isEmpty()) {
            System.out.println("\nСписок покупателей пустой");
        } else {
            System.out.println();
            System.out.println("Список покупателей");
            System.out.println("------------------");
        }

        for (Person person : this.persons) {
            System.out.println(person);
        }
    }

    private void menuShopping() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nВыберите покупателя:");
            for (Person person : this.persons) {
                System.out.printf("%d) %s\n", persons.indexOf(person) + 1, person);
            }
            System.out.println("Q) Выход");

            System.out.print("Выбор ");
            String inputPerson = scanner.nextLine();
            if (inputPerson.equalsIgnoreCase("Q")) {
                break;
            }

            int choicePerson;
            try {
                choicePerson = Integer.parseInt(inputPerson);
            } catch (NumberFormatException nfe) {
                System.out.printf("Неправильно введен выбор (%s). Повторите ввод.\n", inputPerson);
                continue;
            } catch (NullPointerException npe) {
                System.out.println("Неправильно введено число. Повторите ввод.");
                continue;
            }

            Person person = this.persons.get(choicePerson - 1);
            person.showPersonAndProducts();

            while (true) {
                System.out.println("\nВыберите продукт:");
                for (Product product : this.products) {
                    System.out.printf("%d) %s\n", products.indexOf(product) + 1, product);
                }
                System.out.println("Q) Выход");

                System.out.print("Выбор ");
                String inputProduct = scanner.nextLine();
                if (inputProduct.equalsIgnoreCase("Q")) {
                    break;
                }

                int choiceProduct;
                try {
                    choiceProduct = Integer.parseInt(inputProduct);
                } catch (NumberFormatException nfe) {
                    System.out.printf("Неправильно введен выбор (%s). Повторите ввод.\n", inputPerson);
                    continue;
                } catch (NullPointerException npe) {
                    System.out.println("Неправильно введено число. Повторите ввод.\n");
                    continue;
                }
                Product product = this.products.get(choiceProduct - 1);

                person.shopping(product);
                break;
            }
        }
    }

    private void manualShopping() {
        System.out.println("Введите строку в формате 'Имя покупателя - Продукт'");
        System.out.println("END - закончить ввод");

        String[] list;
        boolean error = false;

        Scanner scanner = new Scanner(System.in);
        while (true) {

            if(error) {
                System.out.println("ОШИБКА: Строка должна быть формата 'Покупатель - Товар'");
                System.out.print("Введите строку правильно ");

                error = false;
            }

            System.out.println("Совершить покупку (END - выход)");
            String text = scanner.nextLine();
            if (text.trim().equalsIgnoreCase("end")) {
                break;
            }

            list = text.split("-");
            if (list.length != 2) {
                error = true;
                continue;
            }

            String personName = list[0].trim();
            if (personName.isEmpty()) {
                System.out.println("ОШИБКА: Покупатель не может быть пустой строкой");
                error = true;
                continue;
            }

            String productName = list[1].trim();
            if (productName.isEmpty()) {
                System.out.println("ОШИБКА: Товар не может быть пустой строкой");
                error = true;
                continue;
            }

            Product selectedProduct = null;
            for(Product product : this.products) {
                if(product.getName().equalsIgnoreCase(productName)) {
                    selectedProduct = product;
                    System.out.printf("Выбран продукт '%s'\n", selectedProduct.getName());
                    break;
                }
            }

            if (selectedProduct == null) {
                System.out.printf("ОШИБКА: Товар '%s' не найден. Повторите ввод\n", productName);
                error = true;
                continue;
            }

            Person selectedPerson = null;
            for(Person person : this.persons) {
                if (person.getName().equalsIgnoreCase(personName)) {
                    selectedPerson = person;
                    System.out.printf("Выбран покупатель '%s'\n", selectedPerson.getName());
                    break;
                }
            }

            if (selectedPerson == null) {
                System.out.printf("ОШИБКА: Покупатель '%s' не найден. Повторите ввод\n", personName);
                error = true;
                continue;
            }

            selectedPerson.shopping(selectedProduct);
        }
    }

    private void clearData() {
        this.persons.clear();
        this.products.clear();
    }

}
