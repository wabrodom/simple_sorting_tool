package sorting;

import java.util.List;

public interface DataTypeMethod<T> {
    void sort();
    void sortByCount();

    void printInfo(boolean printToFile);
    void printSort(boolean printToFile);
    void printSortByCount(boolean printToFile);

    List<T> getList();
}
