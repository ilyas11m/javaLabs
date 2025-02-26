package lab3.observable;

import java.util.Observable;

public class FileOut extends Observable {

    public void fileOut(String message) {
        setChanged();
        notifyObservers();
        System.out.println(message);
    }
}
