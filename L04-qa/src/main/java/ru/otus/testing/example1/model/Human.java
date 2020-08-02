package ru.otus.testing.example1.model;

import java.util.UUID;

public class Human {
    private final String name;
    private final char sex;

    public Human(char sex) {
        this.name = UUID.randomUUID().toString();
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public char getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return "Член семьи {" +
                "имя: '" + name + '\'' +
                ", пол: " + sex +
                '}';
    }
}
