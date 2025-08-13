package ru.rtk.homework06;

import ru.rtk.att01.Product;

import java.util.*;

public class Tvset {
    private boolean tvIsOn = false;
    private String vendorName = "Noname";
    private String modelName = "Noname";
    private int diagonal;
    private Channel activeChannel = null;
    private ArrayList<Channel> channels;

    public Tvset(String vendorName, String modelName, int diagonal) {
        this.vendorName = vendorName;
        this.modelName = modelName;
        this.diagonal = diagonal;
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

    public void setDiagonal(int diagonal) {
        this.diagonal = diagonal;
    }

    public String getDiagonal() {
        return String.format("%s\"", this.diagonal);
    }

    public void showFullInfo() {
        System.out.println("Телевизор");
        System.out.printf("Производитель: %s\n", this.getVendorName());
        System.out.printf("Модель: %s\n", this.getModelName());
        System.out.printf("Диагональ: %s\n", this.getDiagonal());
        System.out.println();
    }

    public void showShortInfo() {
        System.out.printf("Телевизор %s %s, диагональ %s\n",
                this.getVendorName(), this.getModelName(), this.getDiagonal());
        System.out.println();
    }

    public void addChannel(
            String channelName,
            int channelNumber,
            String programName,
            double rating,
            int viewers) {
        Channel newChannel = new Channel(channelName, channelNumber, programName, rating, viewers);
        this.channels.add(newChannel);
    }

    public void tvOn() {
        if (this.tvIsOn) {
            System.out.println("Телевизор уже работает");
        }
        this.tvIsOn = true;

        if (!this.channels.isEmpty()) {
            this.activeChannel = this.channels.getFirst();
        }
    }

    public void tvOff() {
        if (!this.tvIsOn) {
            System.out.println("Телевизор уже выключен");
        }
        this.tvIsOn = false;
    }

    public String getActiveChannel() {
        if (this.activeChannel == null) {
            return "Канал для показа не настроен.";
        }
        return String.format("Активный канал: %s", this.activeChannel);
    }

    public void switchActiveChannel() {
        if (this.channels.isEmpty()) {
            System.out.println("В списке нет каналов.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nВыберите канал:");
            for (Channel channel : this.channels) {
                System.out.printf("%d) %s\n", channels.indexOf(channel) + 1, channel);
            }
            System.out.println("Q) Выход");

            System.out.print("Выбор ");
            String choiceString = scanner.nextLine();
            if (choiceString.equalsIgnoreCase("Q")) {
                break;
            }

            int choice;
            try {
                choice = Integer.parseInt(choiceString);
            } catch (NumberFormatException | NullPointerException nfe) {
                System.out.printf("Неправильно введен выбор (%s). Повторите ввод.\n", choiceString);
                continue;
            }
            this.activeChannel = this.channels.get(choice - 1);
            break;

        }
    }

    public void changeParams() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
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
                case "5": // diagonal
                    int newDiagonal = this.changeIntValue(
                            String.format("Измените диагональ, целое число (%s): ", this.getDiagonal()));
                    if (newDiagonal != 0)
                        this.setDiagonal(newDiagonal);
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
