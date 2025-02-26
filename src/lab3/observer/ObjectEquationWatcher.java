package lab3.observer;

import java.util.Observable;
import java.util.Observer;

public class ObjectEquationWatcher implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Событие: равенство объекта некоторому " +
                "значению (Длина списка = 16)");
    }
}
