package sorting;

import java.io.PrintWriter;
import java.util.*;

public class ResultPrinter {
    String dataTypeInput;
    String sortingTypeInput;
    PrintWriter printWriter;
    int total;
    DataInput dataInput;

    ResultPrinter(String dataTypeInput, String sortingTypeInput, int total, DataInput dataInput) {
        this.dataTypeInput = dataTypeInput;
        this.sortingTypeInput = sortingTypeInput;
        this.total = total;
        this.dataInput = dataInput;
    }

    ResultPrinter(String dataTypeInput, String sortingTypeInput, int total, PrintWriter printWriter, DataInput dataInput) {
        this(dataTypeInput, sortingTypeInput, total, dataInput);
        this.printWriter = printWriter;

    }

    void print() {
        if (printWriter != null) {
            printWriter.println("Total " + (dataTypeInput.equals("long") ? "number" : dataTypeInput) + "s: " + total + ".");
        } else {
            System.out.println("Total " + (dataTypeInput.equals("long") ? "number" : dataTypeInput) + "s: " + total + ".");
        }

        switch (sortingTypeInput) {
            case "natural" -> {
                if (printWriter != null) {
                    printWriter.print("Sorted data: ");
                    for (String n : dataInput.getList()) {
                        printWriter.print(n + " " + ((sortingTypeInput.equals("line")) ? "\n" : ""));
                    }
                } else {
                    System.out.print("Sorted data: ");
                    for (String n : dataInput.getList()) {
                        System.out.print(n + " " + ((sortingTypeInput.equals("line")) ? "\n" : ""));
                    }
                }
            }
            case "byCount" -> {
                var list = dataTypeInput.equals("long") ? dataInput.getLongList() : dataInput.getList();
                var sortedMap = sortByValue(list);

                // Output
                for (var entry : sortedMap.entrySet()) {
                    long percent = Math.round(((double) entry.getValue() / dataInput.getTotalInput()) * 100);
                    if (printWriter != null) {
                        printWriter.println(entry.getKey() + ": " + entry.getValue() + " time(s), " + percent + "%");
                    } else {
                        System.out.println(entry.getKey() + ": " + entry.getValue() + " time(s), " + percent + "%");
                    }

                }
            }
        }

    }

    private <K> Map<K, Integer> sortByValue(List<K> inputList) {
        TreeMap<K, Integer> map = new TreeMap<>();
        for (K n : inputList) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        List<Map.Entry<K, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Map<K, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<K, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }


}
