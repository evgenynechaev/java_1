package ru.rtk.homework07;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class DiscountProduct extends Product {
    private double discountPercent = 0;
    private LocalDate endDate = LocalDate.MIN;

    public DiscountProduct(String text) {
        String[] list = text.split("=");
        if (list.length != 2 && list.length != 4) {
            System.out.printf("ОШИБКА: Строка '%s' должна быть формата 'Продукт = Цена' или 'Продукт = Цена = процент_скидки = Дата_окончания_скидки_ГГГГ-ММ-ДД'\n", text);
            return;
        }

        super.setName(list[0]);
        super.setPrice(list[1]);

        if (list.length == 4) {
            this.setDiscountPercent(list[2]);
            this.setEndDate(list[3]);
        }
    }

    @Override
    public String toString() {
        if(this.isActiveDiscount()) {
            return String.format("%s, стоимость %.2f (скидка %.1f%% до %s)",
                    this.name,
                    this.getPrice(),
                    this.discountPercent,
                    this.endDate);
        }
        return super.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if(object instanceof DiscountProduct entity) {
            return Objects.equals(this.name, entity.name) &&
                    Double.compare(this.price, entity.price) == 0 &&
                    Double.compare(this.discountPercent, entity.discountPercent) == 0 &&
                    this.endDate.equals(entity.endDate);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.price, this.discountPercent, this.endDate);
    }


    private boolean isActiveDiscount() {
        LocalDate currentDate = LocalDate.now();
        // System.out.printf("Current date = '%s'", currentDate);
        return !currentDate.isAfter(this.endDate);
    }

    private void setDiscountPercent(String text) {
        double discountPercent;
        try {
            discountPercent = Double.parseDouble(text.trim());
        } catch (NumberFormatException | NullPointerException nfe) {
            System.out.printf("Неправильно введена скидка '%s'.\n", text);
            return;
        }

        this.discountPercent = discountPercent;
    }

    private void setEndDate(String text) {
        LocalDate endDate;
        try {
            endDate = LocalDate.parse(text.trim());
        }  catch (DateTimeParseException nfe) {
            System.out.printf("Неправильно введена дата ГГГГ-ММ-ДД в строке '%s'.\n", text);
            return;
        }

        this.endDate = endDate;
    }

    @Override
    public boolean isValid() {
        return super.isValid();
    }

    private boolean isValidDiscountPercent(double percent) {
        return percent >= 0 && percent < 100;
    }

    private boolean isValidEndDate(LocalDate date) {
        return date.isAfter(LocalDate.MIN);
    }

    @Override
    public double getPrice() {
        if(this.isActiveDiscount()) {
            return super.getPrice()*(1-discountPercent/100);
        }
        return super.getPrice();
    }
}
