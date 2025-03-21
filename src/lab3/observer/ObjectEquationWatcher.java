package lab3.observer;

import lab3.files.Handler;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class ObjectEquationWatcher implements Observer {

    Handler handler;

    String message = "Событие: равенство объекта некоторому значению (Длина списка = 16)";

    public ObjectEquationWatcher(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(message);
        try {
            handler.writeToFile(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
