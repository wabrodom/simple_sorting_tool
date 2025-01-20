package sorting;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


import sorting.sort_type.WordType;
import sorting.sort_type.LongType;
import sorting.sort_type.LineType;



public class GiveType {
    DataTypeMethod<?> dataType; // interface that concrete need to implement
    private DataType type;   // enum : LONG, LINE, WORD
    Scanner scanner = new Scanner(System.in);

    public GiveType(DataType type) {
        this.type = type;
        this.dataType = assignType(type);
    }

    public GiveType(DataType type, String pathToFile) {
        this.type = type;
        this.dataType = assignType(type, pathToFile);
    }

    private DataTypeMethod<?> assignType(DataType type) {
        switch (type) {
            case LONG -> {
                return new LongType(getMaterial());
            } 
            case LINE -> {
                return new LineType(getMaterial());
            }
            case WORD -> {
                return new WordType(getMaterial());
            }
            default -> {
                return new WordType(getMaterial()); // redirect to word
            }
        }
    }

    private DataTypeMethod<?> assignType(DataType type, String pathToFile) {
        try {
            switch (type) {
                case LONG -> {
                    List<Long> list = FileReaderUtil.readFile(pathToFile, DataType.LONG);
                    return new LongType(list);
                } 
                case LINE -> {
                    List<String> list = FileReaderUtil.readFile(pathToFile, DataType.LINE);
                    return new LineType(list);
                }
                case WORD -> {
                    List<String> list = FileReaderUtil.readFile(pathToFile, DataType.WORD);
                    return new LineType(list);
                }
                default -> {
                    throw new IllegalArgumentException("Import the unsupported type: " + type);
                }
            }
            
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        
        System.out.println("Import file failed, try again");
        return null;
    
    }

    
    @SuppressWarnings("unchecked")
    private <T> List<T> getMaterial() {
        return switch(type)  {
            case LONG -> (List<T>) getLongs();
            case LINE -> (List<T>) getLines();
            case WORD -> (List<T>) getWords();
        }; 
    }

    private List<Long> getLongs() {
        List<Long> numbers = new ArrayList<>();

        while(scanner.hasNext()) {
            String input = scanner.next();
            try {
                long number = Long.parseLong(input);
                numbers.add(number);
            } catch (NumberFormatException e) {
                System.out.printf("\"%s\" is not a long. It will be skipped.\n", input);
            }
        }
        return numbers;
    }


    private List<String> getLines() {
        List<String> lines = new ArrayList<>();

        while(scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
        return lines;
    }

    private List<String> getWords() {
        List<String> words = new ArrayList<>();

        while(scanner.hasNext()) {
            words.add(scanner.next());
        }
        return words;
    }


    public void sort() {
        dataType.sort();
    }

    public void sortByCount() {
        dataType.sortByCount();
    }




    /*
    *
    * Print Print Print
    *
    * */
    public void printInfo(boolean printToFile ) {
        dataType.printInfo(printToFile);
    }


    public void printSort(boolean printToFile) {
        dataType.printSort(printToFile);
    }

    public void printSortByCount(boolean printToFile) {
        dataType.printSortByCount(printToFile);
    }


    /*
     * send list to export file
     */

    

}