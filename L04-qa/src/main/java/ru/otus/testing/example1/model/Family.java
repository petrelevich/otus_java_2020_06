package ru.otus.testing.example1.model;

public class Family {
    private final Human husband;
    private final Human wife;

    public Family(Human husband, Human wife) {
        this.husband = husband;
        this.wife = wife;
    }

    public Human getHusband() {
        return husband;
    }

    public Human getWife() {
        return wife;
    }

    @Override
    public String toString() {
        return "Семья: \n" +
                husband + "\n" + wife;
    }
}
