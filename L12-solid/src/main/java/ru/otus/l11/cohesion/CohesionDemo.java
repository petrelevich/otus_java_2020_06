package ru.otus.l11.cohesion;

/**
 * @author spv
 * created on 11.02.20.
 */

// Где CohesionDemo больше: A или B (класс B ниже)?

class A {
    private String message;
    // Инициализация message опущена

    public void process() {
        //...
        send();
    }

    private void send() {
        System.out.println(
                "Send: " + this.message);
    }
}


// См. ниже CohesionDemo2


class B {
    private String message;
    // Инициализация message опущена

    public void process() {
        //...
        send(message);
    }

    private void send(String message) {
        System.out.println(
                "Send: " + message);
    }
}















