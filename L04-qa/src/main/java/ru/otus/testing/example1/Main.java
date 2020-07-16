package ru.otus.testing.example1;

import ru.otus.testing.example1.model.Family;
import ru.otus.testing.example1.services.HumanGenerator;
import ru.otus.testing.example1.services.HumanGeneratorImpl;
import ru.otus.testing.example1.services.WeddingService;
import ru.otus.testing.example1.services.WeddingServiceImpl;

public class Main {

    public static void main(String[] args) {
        HumanGenerator humanGenerator = new HumanGeneratorImpl();
        WeddingService weddingService = new WeddingServiceImpl(humanGenerator);
        Family family = weddingService.doWedding();
        System.out.println(family);

    }
}
