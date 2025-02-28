package lab4.client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите путь до файла журнала: ");
        String filePath = scanner.nextLine();

        System.out.println("Введите адрес сокета: ");
        String address = scanner.nextLine();

        System.out.println("Введите порт сокета: ");
        int port = scanner.nextInt();
        scanner.nextLine();

        try (Socket client = new Socket(address, port)) {

            System.out.println("Connected to server");

            PrintWriter writer = new PrintWriter(client.getOutputStream(),true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            FileWriter fileWriter = new FileWriter(filePath, true);

            System.out.println("Введите массив, в котором хотите изменить значение ячейки: ");

            while (true) {
                String request = scanner.nextLine();
                writer.println(request);
                writer.flush();
                String response = reader.readLine();
                System.out.println(response);
                fileWriter.append(response + "\n");

                switch (request) {
                    case "int":
                        try {
                            System.out.println("Введите строку в целочисленном массиве: ");
                            int row = scanner.nextInt();
                            writer.println(row);
                            writer.flush();

                            System.out.println("Введите колонку в целочисленном массиве: ");
                            int column = scanner.nextInt();
                            writer.println(column);
                            writer.flush();

                            System.out.println("Введите новое значение: ");
                            int elem = scanner.nextInt();
                            scanner.nextLine();
                            writer.println(elem);
                            writer.flush();

                            String log = reader.readLine();
                            System.out.println("Log: " + log);
                            fileWriter.append(log + "\n");
                            break;

                        } catch (Exception e) {
                            e.getMessage();
                        }
                        break;

                    case "double":
                        try {
                            System.out.println("Введите строку в вещественном массиве: ");
                            int row = scanner.nextInt();
                            writer.println(row);
                            writer.flush();

                            System.out.println("Введите колонку в вещественном массиве: ");
                            int column = scanner.nextInt();
                            writer.println(column);
                            writer.flush();

                            System.out.println("Введите новое значение: ");
                            double elem = scanner.nextDouble();
                            scanner.nextLine();
                            writer.println(elem);
                            writer.flush();

                            String log = reader.readLine();
                            System.out.println("Log: " + log);
                            fileWriter.append(log + "\n");
                            break;

                        } catch (Exception e) {
                            e.getMessage();
                        }
                        break;

                    case "str":
                        try {
                            System.out.println("Введите строку в строковом массиве: ");
                            int row = scanner.nextInt();
                            writer.println(row);
                            writer.flush();

                            System.out.println("Введите колонку в строковом массиве: ");
                            int column = scanner.nextInt();
                            scanner.nextLine();
                            writer.println(column);
                            writer.flush();

                            System.out.println("Введите новое значение: ");
                            String elem = scanner.nextLine();
                            writer.println(elem);
                            writer.flush();

                            String log = reader.readLine();
                            System.out.println("Log: " + log);
                            fileWriter.append(log + "\n");
                            break;

                        } catch (Exception e) {
                            e.getMessage();
                        }
                        break;
                }
                if (request.equals("close-socket")) {
                    writer.close();
                    fileWriter.close();
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
