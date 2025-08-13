package ru.rtk.homework06;

import java.util.Objects;

public class Program {
    private String name;
    private double rating;
    private int viewers;

    public Program(String name, double rating, int viewers) {
        this.name = name;
        this.rating = rating;
        this.viewers = viewers;
    }

    @Override
    public String toString() {
        return String.format("Программа '%s', рейтинг %.1f, смотрит %d человек",
                this.name,
                this.rating,
                this.viewers);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if(object instanceof Program program) {
            return Objects.equals(this.name, program.name) &&
                    Double.compare(this.rating, program.rating) == 0 &&
                    this.viewers == program.viewers;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.rating, this.viewers);
    }

    public String getName() {
        return this.name;
    }

    public double getRating() {
        return this.rating;
    }

    public int getViewers() {
        return this.viewers;
    }

    public void changeProgram(String name, double rating, int viewers) {
        this.name = name;
        this.rating = rating;
        this.viewers = viewers;
    }
}
