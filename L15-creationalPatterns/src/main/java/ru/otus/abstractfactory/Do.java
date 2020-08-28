package ru.otus.abstractfactory;

import ru.otus.abstractfactory.led.LedFactory;
import ru.otus.abstractfactory.luminescent.LuminescentFactory;

/**
 * @author sergey
 * created on 17.09.18.
 */
public class Do {

    public static void main(String[] args) {
        AbstractFactory ledFactory = configuration("Led");
        new Do(ledFactory);

        AbstractFactory luminescentFactory = configuration("Luminescent");
        new Do(luminescentFactory);
    }

    private Do(AbstractFactory abstractFactory) {
        Bulb bulb = abstractFactory.createBulb();
        Lampholder lampholder = abstractFactory.createLampholder();

        bulb.light();
        lampholder.hold();
    }

    // "Фабрика фабрик"
    public static AbstractFactory configuration(String param) {
        if ("Led".equals(param)) {
            return new LedFactory();
        }
        if ("Luminescent".equals(param)) {
            return new LuminescentFactory();
        }
        throw new IllegalArgumentException("unknown param:" + param);
    }



}
