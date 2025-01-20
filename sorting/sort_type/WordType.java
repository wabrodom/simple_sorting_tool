package sorting.sort_type;

import java.util.*;
import java.util.stream.Collectors;

import sorting.DataTypeMethod;
import sorting.SaveInOut;

/*
 * data: 
 * List<String>                      input,  can be sorted inplace
 * HashMap<String, Integer>          init map: itself and count
 * List<Map.Entry<String, Integer>>  sort when call instance.sortByCount
 */

public class WordType implements DataTypeMethod<String> {

    private long size;
    private String longestWord;
    private long countLongestWord;
    private long proportion;

    private HashMap<String, Integer> words;
    private List<String> sortedAll;
    private List<Map.Entry<String, Integer>> sortedEntries;


    public WordType(List<String> words) {
        this.words = new HashMap<>();
        for (String str : words) {
            this.words.merge(str, 1, Integer::sum);
        }

        this.size = words.size();
        this.longestWord   = getLongestWord();
        this.countLongestWord = getCountLongestWord();
    }

    private String getLongestWord() {
        return words.keySet().stream().max(Comparator.comparing(String::length)).orElseGet(()-> "");
    }

    private long getCountLongestWord() {
        String longestWord = getLongestWord();
        return words.get(longestWord);
    }


    
    /*
        Total words: 7.
        The longest word: 333 (1 time(s), 14%).
     */



    @Override
    public void sort() {
        sortedAll = words.entrySet().stream()
                .flatMap(entry ->
                            Collections.nCopies(entry.getValue(), entry.getKey()).stream()
                        )
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public void sortByCount() {
        sortedEntries = new ArrayList<>(words.entrySet());

        sortedEntries.sort((e1, e2) -> {
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
        SaveInOut.printf(printToFile, "Total words: %d.", size);
        SaveInOut.printf(printToFile, "The longest word: %s (%d time(s), %d%%).",
                longestWord, countLongestWord, proportion);
    }


    @Override
    public void printSort(boolean printToFile) {
        SaveInOut.printf(printToFile, "Total words: %d\n", size);
        SaveInOut.print(printToFile, "Sorted data: ");
        for (String str : sortedAll) {
            SaveInOut.printf(printToFile, "%s ", str);
        }
    }

    @Override
    public void printSortByCount(boolean printToFile) {
        SaveInOut.println(printToFile,"Total words: " + size);
        for (Map.Entry<String, Integer> entry : sortedEntries ) {
            SaveInOut.printf(printToFile, "%s: %d time(s), %d%%\n",
                    entry.getKey(), entry.getValue(), entry.getValue()*100 / size);
        }
    }

    
    @Override
    public List<String> getList() {
        return sortedAll;
    }

}
