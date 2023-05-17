package sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DataInput {
    List<String> list = new ArrayList<>();
    List<Long> longList = new ArrayList<>();
    int totalInput = 0;

    DataInput(Scanner scanner, String dataTypeInput) {
        if (dataTypeInput.equals("line")) {
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
                totalInput++;
            }
        } else {
            while (scanner.hasNext()) {
                String input = scanner.next();
                if (dataTypeInput.equals("long")) {
                    try {
                        longList.add(Long.parseLong(input));
                        totalInput++;
                    } catch (Exception e) {
                        System.out.println("\"" + input + "\"" +  "is not a long. It will be skipped.");
                    }
                } else {
                    list.add(input);
                    totalInput++;
                }
            }
        }

        scanner.close();

        if (dataTypeInput.equals("long")) {
            Collections.sort(longList);
            for (Long l : longList) {
                list.add(String.valueOf(l));
            }
        } else {
            Collections.sort(list);
        }
    }

    public List<String> getList() {
        return list;
    }

    public List<Long> getLongList() {
        return longList;
    }

    public int getTotalInput() {
        return totalInput;
    }
}
