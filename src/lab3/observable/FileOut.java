package lab3.observable;

import java.util.Observable;

public class FileOut extends Observable {

    public void fileOut() {
        setChanged();
        notifyObservers();
    }
}
