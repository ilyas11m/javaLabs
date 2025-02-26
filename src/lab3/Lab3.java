package lab3;

import lab3.observable.FileIn;
import lab3.observable.FileOut;
import lab3.observable.ObjectEquation;
import lab3.observer.FileInWatcher;
import lab3.observer.FileOutWatcher;
import lab3.observer.ObjectEquationWatcher;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class Lab3 {

    static int sum1 = 0;
    static int sum2 = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();

        /* Объявления событий и добавление слушателей */
        FileIn eventIn = new FileIn();
        FileInWatcher eventInWatcher = new FileInWatcher();
        eventIn.addObserver(eventInWatcher);

        FileOut eventOut = new FileOut();
        FileOutWatcher eventOutWatcher = new FileOutWatcher();
        eventOut.addObserver(eventOutWatcher);

        ObjectEquation objectEquation = new ObjectEquation();
        ObjectEquationWatcher objectEquationWatcher = new ObjectEquationWatcher();
        objectEquation.addObserver(objectEquationWatcher);

        eventIn.consoleIn("Введите путь до файла в котором находится \nпоследовательность чисел:");
        String filePath = scanner.nextLine();

        File inputFile = new File(filePath + ".txt");
        try {
            Scanner fileScanner = new Scanner(inputFile);
            while (fileScanner.hasNextInt()) {
                list.add(fileScanner.nextInt());
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка чтения файла " + filePath);
        }

        System.out.println("Исходный список: " + list);
        objectEquation.equation(list);

        ArrayList<Integer> evenNegative = new ArrayList<>();
        ArrayList<Integer> oddPositive = new ArrayList<>();

        for (Integer value : list) {
            if (value < 0 || value % 2 == 0) {
                evenNegative.add(value);
            }
        }
        System.out.println("Список из чётных и отрицательных чисел: " + evenNegative);

        for (Integer value : list) {
            if (value > 0 && value % 2 != 0 ) {
                oddPositive.add(value);
            }
        }
        System.out.println("Список из нечётных и положительных чисел: " + oddPositive);

        for (Integer integer : oddPositive) {
            sum2 += integer;
        }
        System.out.println("Сумма нечётных и положительных: " + sum2);

        for (Integer integer : evenNegative) {
            sum1 += integer;
        }
        System.out.println("Сумма чётных и отрицательных: " + sum1);

        try {
            System.out.println("Введите путь до файла журнала: ");
            Scanner scanner1 = new Scanner(System.in);
            String outputFile = scanner1.nextLine();
            FileWriter fileWriter = new FileWriter(outputFile, true);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Список:");
            fileWriter.append(stringBuilder);

            for (int i = 0; i < list.size(); i++) {
                fileWriter.append(String.valueOf(list.get(i))).append(" ");
            }

            eventOut.fileOut("Вывод в файл.");
            fileWriter.append("\nСписок из чётных и отрицательных чисел " + evenNegative);
            fileWriter.append("\nСписок из нечётных и положительных чисел " + oddPositive);
            fileWriter.append("\nСумма чётных и отрицательных: " + sum1);
            fileWriter.append("\nСумма нечётных и положительных: " + sum2);
            fileWriter.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}