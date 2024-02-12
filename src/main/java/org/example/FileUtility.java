package org.example;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileUtility {
    public static final String INTEGER_FILE = "integers.txt";
    public static final String FLOAT_FILE = "floats.txt";
    public static final String STRING_FILE = "strings.txt";

    public static boolean appendMode = false;

    public static List<String> integerList = new ArrayList<>();
    public static List<String> floatList = new ArrayList<>();
    public static List<String> stringList = new ArrayList<>();

    public static void processFile(String filePath) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line = reader.readLine();
            while (line != null) {
                if (isInteger(line)) {
                    integerList.add(line);
                } else if (isFloat(line)) {
                    floatList.add(line);
                } else {
                    stringList.add(line);
                }

                line = reader.readLine();
            }
        }
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isFloat(String s) {
        try {
            Float.parseFloat(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static void printShortStatistics(){
        if (!integerList.isEmpty()) {
            System.out.println("Количество натуральных чисел: " + integerList.size());
        }

        if (!floatList.isEmpty()){
            System.out.println("Количество целых чисел: " + floatList.size());
        }

        if (!stringList.isEmpty()){
            System.out.println("Количество строк: " + stringList.size() + "\n") ;
        }
    }

    private static void printFullStatistics(){
        printShortStatistics();

        if (!integerList.isEmpty()) {
            int sum = (int)getSum(integerList);
            double averageValue = getAverageValue(integerList);

            System.out.println("Минимальное натуральное число: " + Collections.min(integerList));
            System.out.println("Максимальное натуральное число: " + Collections.max(integerList));
            System.out.println("Сумма натуральных чисел: " + sum);
            System.out.println("Среднее значение натуральных чисел: " + averageValue + "\n");
        }

        if (!floatList.isEmpty()){
            double sum = getSum(floatList);
            double averageValue = getAverageValue(floatList);

            System.out.println("Минимальное целое число: " + Collections.min(floatList));
            System.out.println("Максимальное целое число: " + Collections.max(floatList));
            System.out.println("Сумма целых чисел: " + sum);
            System.out.println("Среднее значение целых чисел: " + averageValue + "\n");
        }

        if (!integerList.isEmpty() && !floatList.isEmpty()){
            List<String> integerAndFloatList = new ArrayList<>();

            integerAndFloatList.addAll(integerList);
            integerAndFloatList.addAll(floatList);

            double sum = integerAndFloatList.stream().mapToDouble(Double::parseDouble).sum();
            double averageValue = integerAndFloatList.stream().mapToDouble(Double::parseDouble).average().getAsDouble();

            System.out.println("Минимальное среди целых и натуральных число: " + Collections.min(integerAndFloatList));
            System.out.println("Максимальное среди целых и натуральных число: " + Collections.max(integerAndFloatList));
            System.out.println("Сумма целых и натуральных чисел чисел: " + sum);
            System.out.println("Среднее значение среди целых и натуральных чисел: " + averageValue + "\n");
        }

        if (!stringList.isEmpty()){
            int minLength = stringList.stream().mapToInt(String::length).min().orElse(0);
            int maxLength = stringList.stream().mapToInt(String::length).max().orElse(0);

            System.out.println("Длина самой короткой строки: " + minLength);
            System.out.println("Длина самой длинной строки: " + maxLength + "\n");
        }
    }

    private static double getSum(List<String> list){
        return list.stream().mapToDouble(Double::parseDouble).sum();
    }

    private static double getAverageValue(List<String> list){
        return list.stream().mapToDouble(Double::parseDouble).average().getAsDouble();
    }

    public static void printStatistics(boolean shortStatistics, boolean fullStatistics){
        if (fullStatistics){
            printFullStatistics();
        }
        else if (shortStatistics){
            printShortStatistics();
        }
    }

    public static void writeToFile(String outputPath, String fileName, List<String> content) throws IOException {
        if (!content.isEmpty()) {
            String filePath = Paths.get(outputPath, fileName).toString();
            FileWriter writer = new FileWriter(filePath, appendMode);
            for (String line : content) {
                writer.write(line + "\n");
            }
            writer.close();
        }
    }
}
