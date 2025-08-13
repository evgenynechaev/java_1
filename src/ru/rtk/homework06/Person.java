package ru.rtk.homework06;

import java.util.*;

public class Person {
    private String name = null;
    private float wallet = -1;
    private final ArrayList<Product> products = new ArrayList<>();

    private static final int minNameSize = 3;

    public Person() {
        this.inputName();
        this.inputWallet();
    }

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

        if (name.length() < minNameSize) {
            System.out.printf("ОШИБКА: Имя не может быть короче %d символов\n",  minNameSize);
            return;
        }

        float wallet;
        try {
            wallet = Float.parseFloat(list[1].trim());
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

    public Person(String name, float wallet) {
        if(isValidName(name)) {
            this.name = name;
        } else {
            System.out.println("ОШИБКА: Неправильно введено имя покупателя");
            this.inputName();
        }

        if(isValidWallet(wallet)) {
            this.wallet = wallet;
        } else {
            System.out.println("ОШИБКА: Неправильно введена сумма денег");
            this.inputWallet();
        }
    }

    @Override
    public String toString() {
        return String.format("%s, в кошельке %.2f. %s",
                this.name,
                this.wallet,
                !this.products.isEmpty() ? String.format("Покупки: %s", this.delimiterString(this.products)) : "Ничего не куплено");
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if(object instanceof Person person) {
            return Objects.equals(this.name, person.name) &&
                    Float.compare(this.wallet, person.wallet) == 0 &&
                    Arrays.equals(this.products.toArray(), person.products.toArray());
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.wallet) * Arrays.hashCode(this.products.toArray());
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setWallet(float wallet) {
        this.wallet = wallet;
    }

    public float getWallet() {
        return this.wallet;
    }

    public boolean isValid() {
        return this.isValidName(this.name) && this.isValidWallet(this.wallet);
    }

    private boolean isValidName(String name) {
        return name != null && name.length() >= minNameSize;
    }

    private boolean isValidWallet(float wallet) {
        return wallet >= 0;
    }

    private void inputName() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.printf("Введите имя покупателя %s", String.format("(%s) ", this.name));
            String name = scanner.nextLine();
            if (name.isEmpty()) {
                System.out.println("Имя не может быть пустым");
                continue;
            }

            if (name.length() < minNameSize) {
                System.out.println("Имя не может быть короче 3 символов");
                continue;
            }

            this.setName(name);
            return;
        }
    }

    private void inputWallet() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.printf("Введите сумму денег %s\n", String.format("(%.2f) ", this.wallet));
            String input = scanner.nextLine();

            if (input.isEmpty() && this.wallet < 0)
            {
                System.out.println("ОШИБКА: Должна быть указана сумма денег");
                continue;
            }

            if (input.isEmpty())
                return;

            float wallet;
            try {
                wallet = Float.parseFloat(input);
            } catch (NumberFormatException nfe) {
                System.out.printf("Неправильно введено число (%s). Повторите ввод.\n", input);
                continue;
            } catch (NullPointerException npe) {
                System.out.println("Неправильно введено число. Повторите ввод.\n");
                continue;
            }

            if (!this.isValidWallet(wallet))
            {
                System.out.println("Деньги не могут быть отрицательными");
                continue;
            }

            this.setWallet(wallet);
        }
    }

    public void shopping(Product product) {
        if (product == null) {
            System.out.println("Продукт не найден и не может быть добавлен");
            return;
        }

        if(this.purchasedSum() + product.getPrice() <= this.wallet) {
            Product newProduct = new Product(product.getName(), product.getPrice());
            this.products.add(newProduct);
            System.out.printf("'%s' купил '%s'\n", this.name, newProduct.getName());
        } else  {
            System.out.printf("%s не может позволить себе %s\n", this.name, product.getName());
        }
    }

    private float purchasedSum()
    {
        float sum = 0;
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

    public String delimiterString(ArrayList<Product> list) {
        return String.join("; ", list.stream().map(Object::toString).toList());
    }

}
