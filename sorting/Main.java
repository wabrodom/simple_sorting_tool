package sorting;

import java.util.Arrays;

public class Main {
    /*
     *  available args flag, and values
     * "-dataType":     long, line, word
     * "-sortingType":  byCount, natural
     * "-inputFile":    filename e.g input.txt
     * "-outputFile":   filename e.g out.txt
     */
    public static void main(final String[] args) {
        DataType dataType = ArgsParser.parseDataType(args); 
        String sortingType = ArgsParser.parseSortingType(args);
        String inputFilePath = ArgsParser.parsePathToInput(args);
        String outputFilePath = ArgsParser.parsePathToOutput(args);

        GiveType typeOfSort = null;

        if (dataType != null) {
            if (inputFilePath == null) {
                typeOfSort = new GiveType(dataType);
            } else {
                typeOfSort = new GiveType(dataType, inputFilePath);
            }
        }

        if (typeOfSort == null) {
            return;
        }


        // sort
        switch (sortingType) {
            case "byCount":
                typeOfSort.sortByCount();
                break;
            case "natural":
                typeOfSort.sort();
                break;
            default:
                typeOfSort.sort();
                break;
        }

        if (outputFilePath == null) {
            // print to console
            switch (sortingType) {
                case "byCount":
                    typeOfSort.printSortByCount(false); 
                    break;
                case "natural":
                    typeOfSort.printSort(false);
                    break;
                default:
                    typeOfSort.printSort(false);
                    break;
            }
        } else {
            // print output to file
            switch (sortingType) {
                case "byCount":
                    typeOfSort.printSortByCount(true); 
                    break;
                case "natural":
                    typeOfSort.printSort(true);
                    break;
                default:
                    typeOfSort.printSort(true);
                    break;
            }

            FileExport.export(outputFilePath);

        }

    }
}

