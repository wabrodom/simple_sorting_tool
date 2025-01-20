package sorting.sort_type;

import java.util.*;


import sorting.DataTypeMethod;
import sorting.SaveInOut;

/*
 * data: 
 * List<String>                      input,  can be sorted inplace
 * HashMap<String, Integer>          init map: itself and count
 * List<Map.Entry<String, Integer>>  sort when call instance.sortByCount
 */

public class LineType implements DataTypeMethod<String> {

    private long size;
    private String longestLine;
    private long countLongestLine;
    private long proportion;

    private List<String> lines;
    private HashMap<String, Integer> linesMap;
    private List<Map.Entry<String, Integer>> sortedLinesMap;

    public LineType(List<String> lines) {
        this.lines = lines;
        this.linesMap = new HashMap<>();
        for (String line : lines) {
            this.linesMap.merge(line, 1, Integer::sum);
        }

        this.size = lines.size();
        this.longestLine = getLongestLine();
        this.countLongestLine = getCountLongestLine();
        this.proportion = countLongestLine * 100 / size;
    }

    private String getLongestLine() {
        return lines.stream().max(Comparator.comparing(String::length)).orElseGet(()-> "");
    }

    private long getCountLongestLine() {
        return lines.stream().filter(line -> line.equals(longestLine)).count();
    }


    public void addToMap(String key) {
        linesMap.put(key, linesMap.get(key) + 1);
    }



    @Override
    public void sort() {
        lines.sort(Comparator.naturalOrder());
    }

    @Override
    public void sortByCount() {
        sortedLinesMap = new ArrayList<>(linesMap.entrySet());

        sortedLinesMap.sort((e1, e2) -> {
            int countCompare = e2.getValue().compareTo(e1.getValue());
            return countCompare != 0 ? countCompare : e1.getKey().compareTo(e2.getKey());
        });

    }

    /*
     *
     * Print Print Print
     *
     * */
    @Override
    public void printInfo(boolean printToFile) {
        SaveInOut.println(printToFile, "Total lines: " + size);
        SaveInOut.println(printToFile, "The longest line:");
        SaveInOut.println(printToFile, longestLine);
        SaveInOut.printf(printToFile, "(%d time(s), %d%%).", countLongestLine, proportion);
    }

    // Total words: 7.
    // Sorted data: -2 1 1 1 33 4 42
    @Override
    public void printSort(boolean printToFile) {
        SaveInOut.printf(printToFile, "Total lines: %d\n", size);
        SaveInOut.println(printToFile, "Sorted data: ");
        for (String str : lines) {
            SaveInOut.println(printToFile, str);
        }
    }

    @Override
    public void printSortByCount(boolean printToFile) {
        SaveInOut.println(printToFile, "Total lines: " + size);
        for (Map.Entry<String, Integer> entry : sortedLinesMap ) {
            SaveInOut.printf(printToFile, "%s: %d time(s), %d%%\n",
                    entry.getKey(), entry.getValue(), entry.getValue()*100 / size);
        }
    }

    @Override
    public List<String> getList() {
        return lines;
    }
}
