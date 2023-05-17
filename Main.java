package sorting;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(final String[] args) {
        List<String> argsList = new ArrayList<>(Arrays.asList(args));
        String dataTypeInput = "word";
        String sortingTypeInput = "natural";
        String inputFileName;
        String outputFileName;
        Scanner scanner = new Scanner(System.in);
        DataInput dataInput;
        ResultPrinter resultPrinter = null;

        if (argsList.contains("-dataType")) {
            try {
                dataTypeInput = args[argsList.indexOf("-dataType") + 1];
            } catch (Exception e) {
                System.out.println("No data type defined!");
            }
        }

        if (argsList.contains("-sortingType")) {
            try {
                sortingTypeInput = args[argsList.indexOf("-sortingType") + 1];
            } catch (Exception e) {
                System.out.println("No sorting type defined!");
            }
        }

        if (argsList.contains("-inputFile")) {
            try {
                inputFileName = args[argsList.indexOf("-inputFile") + 1];
                File file = new File(inputFileName);
                try {
                    scanner = new Scanner(file);
                } catch (IOException e) {
                    System.out.println("File not found.");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("No input file defined!");
            }
        }
        dataInput = new DataInput(scanner, dataTypeInput);

        if (argsList.contains("-outputFile")) {
            try {
                outputFileName = args[argsList.indexOf("-outputFile") + 1];
                File file = new File(outputFileName);
                try (PrintWriter printWriter = new PrintWriter(file)){
                    resultPrinter = new ResultPrinter(dataTypeInput, sortingTypeInput, dataInput.getTotalInput(), printWriter, dataInput);
                }
            } catch (Exception e) {
                System.out.println("No input file defined!");
            }
        } else {
            resultPrinter = new ResultPrinter(dataTypeInput, sortingTypeInput, dataInput.getTotalInput(), dataInput);
        }

        for (int i = 0; i < args.length; i = i + 2) {
            if (!args[i].equals("-dataType") && !args[i].equals("-sortingType") && !args[i].equals("-outputFile") && !args[i].equals("-inputFile")) {
                System.out.println("\"" + args[i] + "\"" + "is not a valid parameter. It will be skipped.");
            }
        }

        resultPrinter.print();

    }
}


