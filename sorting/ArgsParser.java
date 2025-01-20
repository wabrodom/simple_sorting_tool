package sorting;

import java.util.List;

/*
 * implement sort on all type and sort by count
 * -sortingType
 * java SortingTool -sortingType natural -dataType long
 * java SortingTool -dataType word -sortingType byCount
 * */

public class ArgsParser {
    static final String DATA_TYPE       = "-dataType";
    static final String SORTING_TYPE    = "-sortingType";
    static final String INPUT_FILE      = "-inputFile";
    static final String OUTPUT_FILE     = "-outputFile";

    /*
     * Parse args to return DataType or just string
     */

    public static void checkArgFlags(String[] args) {
        List<String> possibleFlags = List.of(
            "-dataType", "-sortingType", "-inputFile", "-outputFile"
        );
        
        for (int i = 0; i < args.length; i++) {
            String currArg = args[i];
            if (currArg.startsWith("-")) {
                if (!possibleFlags.contains(currArg))
                System.out.printf("\"%s\" is not a valid parameter. It will be skipped.\n", currArg);
            }
        }
    }
    
    public static DataType parseDataType(String[] args){
        DataType validTypeOrNull = null;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(DATA_TYPE)) {
                if (i == args.length -1) {
                    System.out.println("No data type defined!");
                    break;
                } else {
                    try {
                        validTypeOrNull = DataType.valueOf(args[i + 1].toUpperCase());
                        return validTypeOrNull;
                    } catch (IllegalArgumentException  e) {
                        System.out.println("Invalid sort type: " + args[i + 1]);
                        break;
                    }
                }
            } 
        }
        // default type is word
        validTypeOrNull = DataType.valueOf("WORD");

        return validTypeOrNull;
    }

    public static String parseSortingType(String[] args) {
        String sortingType = null;

        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals("-sortingType")) {
                sortingType = args[i + 1];
                break;
            }
        }

        if (sortingType == null) {
            System.out.println("No sorting type defined! Default to type \"natural\"");
            sortingType = "natural";
        }
        return sortingType;
    }

    public static String parsePathToInput(String[] args) {
        String inputPathOrNuLL = null;
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals(INPUT_FILE)) {
                inputPathOrNuLL = args[i + 1];
                break;
            }
        }
    
        return inputPathOrNuLL;
    }

    public static String parsePathToOutput(String[] args) {
        String outputPathOrNull = null;
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals(OUTPUT_FILE)) {
                outputPathOrNull = args[i + 1];
                break;
            }
        }
      
        return outputPathOrNull;
    }

}
