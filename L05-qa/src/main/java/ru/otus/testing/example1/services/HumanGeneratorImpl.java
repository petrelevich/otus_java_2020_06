package ru.otus.testing.example1.services;

import ru.otus.testing.example1.model.Human;

public class HumanGeneratorImpl implements HumanGenerator {

    @Override
    public Human generate(char sex) {
        return new Human(sex);
    }
}
