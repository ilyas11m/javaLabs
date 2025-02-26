package lab3.observer;

import java.util.Observable;
import java.util.Observer;

public class FileInWatcher implements Observer {
    @Override
    public void update(Observable o, Object arg)  {
        System.out.println("Событие: ввод с указанного файла");
    }
}
