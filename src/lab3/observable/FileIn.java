package lab3.observable;

import java.util.Observable;

public class FileIn extends Observable {

    public void consoleIn(String message) {
        setChanged();
        notifyObservers();
        System.out.println(message);
    }

}
