package lab3.observable;

import java.util.Observable;

public class FileIn extends Observable {

    public void consoleIn() {
        setChanged();
        notifyObservers();
    }

}
