package lab4.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Server {
    public static int[][] arr1 = new int[2][5];
    public static double[][] arr2 = new double[2][5];
    public static String[][] arr3 = new String[2][5];
    static String chars = "abcdefghijklmnopqrstuvwxyz0987654321";

    Scanner scanner = new Scanner(System.in);
    StringBuilder SERVER_PORT = new StringBuilder();

    public static void fillArray() {
        for (int i = 0; i < arr1.length; i ++) {
            for (int j = 0; j < arr1[i].length; j ++) {
                arr1[i][j] = (int) (Math.random()*100);
            }
        }
        for (int i = 0; i < arr2.length; i ++) {
            for (int j = 0; j < arr2[i].length; j ++) {
                arr2[i][j] = Math.random()*100;
            }
        }
        Random random = new Random();
        for (int i = 0; i < arr3.length; i++) {
            for (int j = 0; j < arr3[i].length; j++) {
                arr3[i][j] = String.valueOf((chars.charAt(random.nextInt(chars.length()))));
            }
        }
    }

    public static void changeCell(int row, int column, int elem) {
        arr1[row][column] = elem;
        System.out.println(Arrays.deepToString(arr1));
    }

    public Server() {

        System.out.println("Введите путь до файла, в котором хранится значение порта: ");
        String portFile = scanner.nextLine();

        File file = new File(portFile + ".txt");

        try {
            Scanner scannerFile = new Scanner(file);

            while (scannerFile.hasNextInt()){
                SERVER_PORT.append(scannerFile.nextInt());
            }

            System.out.println(SERVER_PORT);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (ServerSocket server = new ServerSocket(Integer.parseInt(String.valueOf(SERVER_PORT)))) {
            System.out.println("Server started!");
            fillArray();
            Socket socket = server.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            while (true) {
                String response = "200-OK";
                writer.println(response);
                writer.flush();
                String request = reader.readLine();
                System.out.println("Request: " + request);
                if (request.equals("int")){
                    int row = Integer.parseInt(reader.readLine());
                    int column = Integer.parseInt(reader.readLine());
                    int elem = Integer.parseInt(reader.readLine());
                    changeCell(row,column,elem);
                }
                if (request.equals("close-socket")) {
                    reader.close();
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        new Server();
    }
}
