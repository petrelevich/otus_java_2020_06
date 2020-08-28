package ru.otus.factorymethod2;

import ru.otus.factorymethod.Configuration;

public class Demo {
  public static void main(String[] args) {
    // У нас есть какой-то конфиг
    Configuration config;
    // и мы его хотим читать их разных мест

    // из файла
    var factory1 = ConfigurationFactory.getConfiguration("file");
    config = factory1.buildConfiguration();
    System.out.println(config.params());

    // или из БД
    var factory2 = ConfigurationFactory.getConfiguration("db");
    config = factory1.buildConfiguration();
    System.out.println(config.params());

    // или еще откуда-то ...
    // ...
  }
}
