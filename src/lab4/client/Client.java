package lab4.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        try (Socket client = new Socket("127.0.0.1", 8000)) {
            System.out.println("Connected to server");
            PrintWriter printWriter = new PrintWriter(client.getOutputStream());
            while (true){
                printWriter.write("Hello from client");
                printWriter.close();
                printWriter.flush();
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
