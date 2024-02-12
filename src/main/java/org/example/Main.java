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

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-o":
                    outputPath = args[++i];
                    break;
                case "-p":
                    filePrefix = args[++i];
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

            writeToFile(outputPath, filePrefix + INTEGER_FILE, integerList);
            writeToFile(outputPath, filePrefix + FLOAT_FILE, floatList);
            writeToFile(outputPath, filePrefix + STRING_FILE, stringList);

            if (shortStatistics || fullStatistics) {
               printStatistics(shortStatistics, fullStatistics);
            }
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}