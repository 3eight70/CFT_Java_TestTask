package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.example.FileUtility.*;

public class Main {
    public static void main(String[] args) {
        List<String> files = new ArrayList<>();
        String outputPath = ".";
        String filePrefix = "";

        boolean shortStatistics = false;
        boolean fullStatistics = false;
        boolean filePrefixFlag = false;
        boolean appendMode = false;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-o":
                    outputPath = args[++i];
                    break;
                case "-p":
                    if (i + 1 < args.length) {
                        filePrefix = args[++i];
                    }

                    filePrefixFlag = true;
                    break;
                case "-a":
                    appendMode = true;
                    break;
                case "-s":
                    shortStatistics = true;
                    break;
                case "-f":
                    fullStatistics = true;
                    break;
                default:
                    files.add(args[i]);
            }
        }

        try {
            for (String file : files) {
                processFile(file);
            }

            if (filePrefixFlag && filePrefix == "") {
                System.out.println("После -p необходимо указать префикс для конечных файлов");
                return;
            }

            writeToFile(outputPath, filePrefix + INTEGER_FILE, integerList, appendMode);
            writeToFile(outputPath, filePrefix + FLOAT_FILE, floatList, appendMode);
            writeToFile(outputPath, filePrefix + STRING_FILE, stringList, appendMode);

            if (shortStatistics || fullStatistics) {
                printStatistics(shortStatistics, fullStatistics);
            }

            System.out.println("Программа выполнена успешно.");

        } catch (IOException e) {
            System.err.println("Ошибка произошла с: " + e.getMessage());
        }
    }
}