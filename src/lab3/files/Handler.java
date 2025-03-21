package lab3.files;

import java.io.FileWriter;
import java.io.IOException;

public class Handler {

    String fileName;
    FileWriter fileWriter;

    public Handler(String fileName) throws IOException {
        this.fileName = fileName;
        fileWriter = new FileWriter(fileName, true);
    }

    public void writeToFile(String message) throws IOException {
        fileWriter.write("\n" + message);
    }

    public void closeFile() throws IOException {
        fileWriter.flush();
    }

}
