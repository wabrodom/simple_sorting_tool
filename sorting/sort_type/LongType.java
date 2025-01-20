package sorting.sort_type;

import java.util.*;
import java.util.stream.Collectors;

import sorting.DataTypeMethod;
import sorting.SaveInOut;

/*
 * data: 
 * List<Long>                      input,  can be sorted inplace
 * HashMap<Long, Integer>          init map: itself and count
 * List<Map.Entry<Long, Integer>>  sort when call instance.sortByCount
 */

public class LongType implements DataTypeMethod<Long> {

    private long size;
    private long maxNumber;
    private long countMaxNumber;
    private long porprotion;

    private HashMap<Long, Integer> numbers;
    private List<Long> sortedAll;
    private List<Map.Entry<Long, Integer>> sortedEntries;

    public LongType(List<Long> numbers) {
        this.numbers = new HashMap<>();
        for(Long num : numbers) {
            this.numbers.merge(num, 1, Integer::sum);
        }
        this.size = numbers.size();
        this.maxNumber = getMaxNumber();
        this.countMaxNumber = countMaxNumber();
        this.porprotion = countMaxNumber * 100 / size;
    }

    private long getMaxNumber() {
        return numbers.keySet().stream()
                .max(Long::compare)
                .orElseGet(() -> 0L);
    }

    private long countMaxNumber() {
        long maxNumber = getMaxNumber();
        return numbers.get(maxNumber);
    }

    @Override
    public void sort(){
        sortedAll = numbers.entrySet().stream()
                .flatMap(entry ->
                        Collections.nCopies(entry.getValue(), entry.getKey()).stream()
                )
                .sorted()
                .collect(Collectors.toList());
    }


    @Override
    public void sortByCount() {
        sortedEntries = new ArrayList<>(numbers.entrySet());

        sortedEntries.sort((e1, e2) -> {
            int countCompare = e1.getValue().compareTo(e2.getValue());
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
        SaveInOut.println(printToFile, "Total numbers: " + size);
        SaveInOut.printf(printToFile, "The greatest number: %d (%d time(s), %d%%).\n", maxNumber, countMaxNumber, porprotion);
    }

    @Override
    public void printSort(boolean printToFile) {
        SaveInOut.println(printToFile, "Total numbers: " + size + ".");
        SaveInOut.print(printToFile, "Sorted data: ");
        SaveInOut.println(printToFile, Arrays.toString(sortedAll.toArray()));
    }


    // Total numbers: 7.
    // -2: 1 time(s), 14%
    @Override
    public void printSortByCount(boolean printToFile) {
        SaveInOut.println(printToFile, "Total numbers: " + size + ".");
        for (Map.Entry<Long, Integer> entry : sortedEntries ) {
            SaveInOut.printf(printToFile, "%d: %d time(s), %d%%\n",
                    entry.getKey(), entry.getValue(), entry.getValue()*100 / size);
        }
    }

    
    @Override
    public List<Long> getList() {
        return sortedAll;
    }
}
