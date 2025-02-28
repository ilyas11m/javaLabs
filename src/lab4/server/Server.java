package lab4.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {

    public static void main(String[] args) {
        new Server(args);
    }

    public static int[][] arr1 = new int[2][5];
    public static double[][] arr2 = new double[2][5];
    public static String[][] arr3 = new String[2][5];
    static String chars = "abcdefghijklmnopqrstuvwxyz";

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
                arr2[i][j] = Math.random() * 100;
            }
        }
        Random random = new Random();
        for (int i = 0; i < arr3.length; i++) {
            for (int j = 0; j < arr3[i].length; j++) {
                arr3[i][j] = String.valueOf(chars.charAt(random.nextInt(chars.length())));
            }
        }
    }

    public static String changeStrArr(int row, int column, String elem) {
        arr3[row][column] = elem;
        return Arrays.deepToString(arr3);
    }

    public static String changeDoubleArr(int row, int column, double elem) {
        arr2[row][column] = elem;
        return Arrays.deepToString(arr2);
    }

    public static String changeIntArr(int row, int column, int elem) {
        arr1[row][column] = elem;
        return Arrays.deepToString(arr1);
    }

    public Server(String[] args) {

        List<Integer> restricted = new ArrayList<>();

        for (String arg : args) {
            int elem = Integer.parseInt(arg);
            restricted.add(elem);
        }

        int restrictRow = restricted.get(0);
        int restrictCol = restricted.get(1);

        System.out.println("Запрещенный для изменения индекс массивов: " + restricted);

        System.out.println("Введите путь до файла, в котором хранится значение порта: ");
        String portFile = scanner.nextLine();

        File file = new File(portFile + ".txt");

        try {
            Scanner scannerFile = new Scanner(file);

            while (scannerFile.hasNextInt()){
                SERVER_PORT.append(scannerFile.nextInt());
            }

            System.out.println(SERVER_PORT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        try (ServerSocket server = new ServerSocket(Integer.parseInt(String.valueOf(SERVER_PORT)))) {
            System.out.println("Server started!");
            fillArray();
            Socket socket = server.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
            while (true) {
                String response = "200-OK";
                writer.println(response);
                String request = reader.readLine();
                System.out.println("Request: " + request);
                if (request.equals("int")){
                    try {
                        int row = Integer.parseInt(reader.readLine());
                        int column = Integer.parseInt(reader.readLine());
                        int elem = Integer.parseInt(reader.readLine());

                        if (row == restrictRow && column == restrictCol) {
                            writer.println("Индекс массива запрещен для изменений!");
                        }
                        else {
                            writer.println(changeIntArr(row,column,elem));
                        }
                    } catch (Exception e) {
                       writer.println(e.getMessage());
                       writer.flush();
                    }
                }
                else if (request.equals("double")){
                    try {
                        int row = Integer.parseInt(reader.readLine());
                        int column = Integer.parseInt(reader.readLine());
                        double elem = Double.parseDouble(reader.readLine());

                        if (row == restrictRow && column == restrictCol) {
                            writer.println("Индекс массива запрещен для изменений!");
                        }
                        else {
                            writer.println(changeDoubleArr(row,column,elem));
                        }
                    } catch (Exception e) {
                        writer.println(e.getMessage());
                        writer.flush();
                    }
                }
                else if (request.equals("str")){
                    try {
                        int row = Integer.parseInt(reader.readLine());
                        int column = Integer.parseInt(reader.readLine());
                        String elem = reader.readLine();

                        if (row == restrictRow && column == restrictCol) {
                            writer.println("Индекс массива запрещен для изменений!");
                        }
                        else {
                            writer.println(changeStrArr(row, column, elem));
                        }
                    } catch (Exception e) {
                        writer.println(e.getMessage());
                        writer.flush();
                    }
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
}
