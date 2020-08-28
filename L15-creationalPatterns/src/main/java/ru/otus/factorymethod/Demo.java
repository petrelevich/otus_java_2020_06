package ru.otus.factorymethod;

/**
 * @author sergey
 * created on 19.09.18.
 *
 * @author spv
 * edited 28.08.20.
 */
public class Demo {
  public static void main(String[] args) {
    // У нас есть какой-то конфиг
    Configuration config;
    // и мы его хотим читать их разных мест

    // из файла
    config = ConfigurationFactory.getConfiguration("file");
    System.out.println(config.params());

    // или из БД
    config = ConfigurationFactory.getConfiguration("db");
    System.out.println(config.params());

    // или еще откуда-то ...
    // ...
  }
}
