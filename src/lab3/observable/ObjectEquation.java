package lab3.observable;

import java.util.ArrayList;
import java.util.Observable;

public class ObjectEquation extends Observable {

    public void equation(ArrayList<Integer> list) {
        if (list.size()==16) {
            setChanged();
            notifyObservers();
        }
    }
}
