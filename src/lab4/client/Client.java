package lab4.client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите адрес сокета: ");
        String address = scanner.nextLine();
        System.out.println("Введите порт сокета: ");
        int port = scanner.nextInt();
        try (Socket client = new Socket(address, port)) {
            scanner.nextLine();
            System.out.println("Connected to server");
            PrintWriter writer = new PrintWriter(client.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.println("Введите массив, в котором хотите изменить значение ячейки: ");
            while (true) {
                String request = scanner.nextLine();
                writer.println(request);
                writer.flush();
                String response = reader.readLine();
                System.out.println("Response: " + response);
                switch (request) {
                    case "int":
                        System.out.println("Введите строку в массиве: ");
                        int row = scanner.nextInt();
                        writer.println(row);
                        writer.flush();
                        System.out.println("Введите колонку в массиве: ");
                        int column = scanner.nextInt();
                        writer.println(column);
                        writer.flush();
                        System.out.println("Введите новое значение: ");
                        int elem = scanner.nextInt();
                        writer.println(elem);
                        writer.flush();;
                        break;
                    case "double":
                    case "str":
                }
                if (request.equals("close-socket")) {
                    writer.close();
                    break;
                }
            }

        } catch (UnknownHostException e) {

            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
