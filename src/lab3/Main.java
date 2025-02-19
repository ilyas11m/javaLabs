package lab3;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int sum1 = 0;
    static int sum2 = 0;
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

//        System.out.print("Введите кол-во элементов в списке: ");
//        int size = scanner.nextInt();
//        scanner.nextLine();

        ArrayList<Integer> list = new ArrayList<>();

        System.out.println("Введите путь до файла: ");
        String filePath = scanner.nextLine();

        File inputFile = new File(filePath);
        try {
            Scanner fileScanner = new Scanner(inputFile);
            while (fileScanner.hasNextInt()) {
                list.add(fileScanner.nextInt());
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка чтения файла " + filePath);
        }
//        System.out.println("Введите элементы.");
//        int count = 1;
//        while (list.size() < size) {
//            System.out.print(count + "-ое число: ");
//            ++count;
//            list.add(scanner.nextInt());
//        }
//
//        System.out.println("Исходный список: " + list);

        ArrayList<Integer> evenNegative = new ArrayList<>();
        ArrayList<Integer> oddPositive = new ArrayList<>();

        for (Integer value : list) {
            if (value < 0 || value % 2 == 0) {
                evenNegative.add(value);
            }
        }
        System.out.println("Список из чётных и отрицательных чисел " + evenNegative);

        for (Integer value : list) {
            if (value > 0 && value % 2 != 0 ) {
                oddPositive.add(value);
            }
        }
        System.out.println("Список из нечётных и положительных чисел " + oddPositive);

        for (Integer integer : oddPositive) {
            sum2 += integer;
        }
        System.out.println("Сумма нечётных и положительных: " + sum2);

        for (Integer integer : evenNegative) {
            sum1 += integer;
        }
        System.out.println("Сумма чётных и отрицательных: " + sum1);

        try {
            File outputFile = new File("src/lab3/files/output.txt");
            FileWriter fileWriter = new FileWriter(outputFile, true);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Список:");
            fileWriter.append(stringBuilder);

            for (int i = 0; i < list.size(); i++) {
                fileWriter.append(String.valueOf(list.get(i))).append(" ");
            }
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