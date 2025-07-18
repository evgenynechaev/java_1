package ru.rtk.homework9;

import java.util.Scanner;

public class Tv {
    private String vendorName = "Noname";
    private String modelName = "Noname";
    private String description = "No description";
    private String hd = "";
    private int diagonal;
    private int price;

    public Tv() {
    }

    public Tv(String vendorName, String modelName) {
        this.vendorName = vendorName;
        this.modelName = modelName;
    }

    public Tv(String vendorName, String modelName, String description, String hd, int diagonal, int price) {
        this.vendorName = vendorName;
        this.modelName = modelName;
        this.description = description;
        this.hd = hd;
        this.diagonal = diagonal;
        this.price = price;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorName() {
        return this.vendorName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelName() {
        return this.modelName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setHd(String hd) {
        this.hd = hd;
    }

    public String getHd() {
        return this.hd;
    }

    public void setDiagonal(int diagonal) {
        this.diagonal = diagonal;
    }

    public String getDiagonal() {
        return String.format("%s\"", this.diagonal);
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPrice() {
        return String.format("%s руб.", this.price);
    }

    public void showFullInfo() {
        System.out.println("Телевизор");
        System.out.printf("Производитель: %s\n", this.getVendorName());
        System.out.printf("Модель: %s\n", this.getModelName());
        System.out.printf("Описание: %s\n", this.getDescription());
        System.out.printf("Формат: %s\n", this.getHd());
        System.out.printf("Диагональ: %s\n", this.getDiagonal());
        System.out.printf("Цена: %s\n", this.getPrice());
        System.out.println();
    }

    public void showShortInfo() {
        System.out.printf("Телевизор %s %s, диагональ %s (%s), цена %s\n",
                this.getVendorName(), this.getModelName(),
                this.getDiagonal(), this.getHd(), this.getPrice());
        System.out.println();
    }

    public void showParams() {
        System.out.println("Телевизор");
        System.out.printf("1) Производитель: %s\n", this.getVendorName());
        System.out.printf("2) Модель: %s\n", this.getModelName());
        System.out.printf("3) Описание: %s\n", this.getDescription());
        System.out.printf("4) Формат: %s\n", this.getHd());
        System.out.printf("5) Диагональ: %s\n", this.getDiagonal());
        System.out.printf("6) Цена: %s\n", this.getPrice());
        System.out.println("0) Выход");
    }

    public void changeParams() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            this.showParams();
            System.out.print("Что изменить? ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1": // vendor
                    String newVendor = this.changeStringValue(
                            String.format("Измените производителя (%s): ", this.getVendorName()));
                    if (!newVendor.isEmpty())
                        this.setVendorName(newVendor);
                    break;
                case "2": // model
                    String newModel = this.changeStringValue(
                            String.format("Измените модель (%s): ", this.getModelName()));
                    if (!newModel.isEmpty())
                        this.setModelName(newModel);
                    break;
                case "3": // description
                    System.out.printf("Описание: %s\n", this.getDescription());
                    String newDescription = this.changeStringValue("Измените описание: ");
                    if (!newDescription.isEmpty())
                        this.setDescription(newDescription);
                    break;
                case "4": // hd
                    String newHd = this.changeStringValue(
                            String.format("Измените HD (%s): ", this.getHd()));
                    if (!newHd.isEmpty())
                        this.setHd(newHd);
                    break;
                case "5": // diagonal
                    int newDiagonal = this.changeIntValue(
                            String.format("Измените диагональ, целое число (%s): ", this.getDiagonal()));
                    if (newDiagonal != 0)
                        this.setDiagonal(newDiagonal);
                    break;
                case "6": // price
                    int newPrice = this.changeIntValue(
                            String.format("Измените цену, целое число (%s): ", this.getPrice()));
                    if (newPrice != 0)
                        this.setPrice(newPrice);
                    break;
                case "0": return;
            }
        }
    }

    private String changeStringValue(String info) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(info);
        return scanner.nextLine();
    }

    private int changeIntValue(String info) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(info);
            String inputValue = scanner.nextLine();
            int intValue;
            try {
                intValue = Integer.parseInt(inputValue);
            } catch (NumberFormatException nfe) {
                System.out.printf("Неправильно введено число (%s). Повторите ввод.\n", inputValue);
                continue;
            }
            return intValue;
        }
    }
}
