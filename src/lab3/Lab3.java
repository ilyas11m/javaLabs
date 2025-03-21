package lab3;

import lab3.files.Handler;
import lab3.observable.FileIn;
import lab3.observable.FileOut;
import lab3.observable.ObjectEquation;
import lab3.observer.FileInWatcher;
import lab3.observer.FileOutWatcher;
import lab3.observer.ObjectEquationWatcher;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Lab3 {

    static int sum1 = 0;
    static int sum2 = 0;
    static ArrayList<String> mesList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();

        Handler handler = new Handler(fileName + ".txt");

        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> evenNegative = new ArrayList<>();
        ArrayList<Integer> oddPositive = new ArrayList<>();

        /* Объявления событий и добавление слушателей */
        FileIn eventIn = new FileIn();
        FileInWatcher eventInWatcher = new FileInWatcher(handler);
        eventIn.addObserver(eventInWatcher);

        FileOut eventOut = new FileOut();
        FileOutWatcher eventOutWatcher = new FileOutWatcher(handler);
        eventOut.addObserver(eventOutWatcher);

        ObjectEquation objectEquation = new ObjectEquation();
        ObjectEquationWatcher objectEquationWatcher = new ObjectEquationWatcher(handler);
        objectEquation.addObserver(objectEquationWatcher);

        eventOut.fileOut();
        String mes2 = "Введите путь до файла в котором находится \nпоследовательность чисел:";
        System.out.println(mes2);
        mesList.add(mes2);
        handler.writeToFile(mesList.get(0));
        String filePath = scanner.nextLine();

        File inputFile = new File(filePath + ".txt");
        try {
            Scanner fileScanner = new Scanner(inputFile);
            while (fileScanner.hasNextInt()) {
                list.add(fileScanner.nextInt());
                eventIn.consoleIn();
                eventOut.fileOut();
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка чтения файла " + filePath);
        }
        eventOut.fileOut();
        String mes3 = "Исходный список: " + list;
        System.out.println(mes3);
        mesList.add(mes3);
        handler.writeToFile(mesList.get(1));

        for (Integer value : list) {
            if (value < 0 || value % 2 == 0) {
                evenNegative.add(value);
            }
        }
        eventOut.fileOut();
        String mes4 = "Список из чётных и отрицательных чисел: " + evenNegative;
        System.out.println(mes4);
        mesList.add(mes4);
        handler.writeToFile(mesList.get(2));

        eventOut.fileOut();
        objectEquation.equation(list);

        for (Integer value : list) {
            if (value > 0 && value % 2 != 0 ) {
                oddPositive.add(value);
            }
        }
        eventOut.fileOut();
        String mes5 = "Список из нечётных и положительных чисел: " + oddPositive;
        System.out.println(mes5);
        mesList.add(mes5);
        handler.writeToFile(mesList.get(3));

        for (Integer integer : oddPositive) {
            sum2 += integer;
        }
        eventOut.fileOut();
        String mes6 = "Сумма нечётных и положительных: " + sum2;
        System.out.println(mes6);
        mesList.add(mes6);
        handler.writeToFile(mesList.get(4));

        for (Integer integer : evenNegative) {
            sum1 += integer;
        }
        eventOut.fileOut();
        String mes7 = "Сумма чётных и отрицательных: " + sum1;
        System.out.println(mes7);
        mesList.add(mes7);
        handler.writeToFile(mesList.get(5));

        handler.closeFile();
    }
}