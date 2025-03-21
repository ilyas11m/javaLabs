package lab3.observer;

import lab3.files.Handler;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class FileInWatcher implements Observer {

    String message = "Событие: ввод с указанного файла";

    Handler handler;

    public FileInWatcher(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void update(Observable o, Object arg)  {
        System.out.println(message);
        try {
            handler.writeToFile(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
