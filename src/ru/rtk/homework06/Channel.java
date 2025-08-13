package ru.rtk.homework06;

import java.util.Objects;

public class Channel {
    private final String name;
    private final int number;
    private final Program program;

    public Channel(String name, int number, String programName, double rating, int viewers) {
        this.name = name;
        this.number = number;
        this.program = new Program(programName, rating, viewers);
    }

    @Override
    public String toString() {
        return String.format("Канал '%s', номер %d, идет %s",
                this.name,
                this.number,
                this.program);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if(object instanceof Channel channel) {
            return Objects.equals(this.name, channel.name) &&
                    this.number == channel.number &&
                    Objects.equals(this.program, channel.program);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.number, this.program.hashCode());
    }

    public void changeProgram(String name, float rating, int viewers) {
        this.program.changeProgram(name, rating, viewers);
    }

    public String getName() {
        return this.name;
    }
    public int getNumber() {
        return this.number;
    }

}
