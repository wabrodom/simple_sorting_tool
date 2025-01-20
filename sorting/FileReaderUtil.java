package sorting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class FileReaderUtil {
    /**
     * Reads a file and returns a List of type T (either String or Long)
     * @param <T> The type parameter (String or Long)
     * @param filePath Path to the file to read
     * @param type DataType enum of the required type
     * @return List<T> containing the file contents
     * @throws IOException If file reading fails
     * @throws IllegalArgumentException If unsupported type is requested
     */
    public static <T> List<T> readFile(String filePath, DataType type) throws IOException {
        List<T> result = new ArrayList<>();
        String actualPath = "./" + filePath;
        // String actualPath = filePath; // same behavior as ./ + filePath
        
        try (BufferedReader reader = new BufferedReader(new FileReader(actualPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                
                if (type == DataType.LINE) {
                    T value = parseValue(line, type);
                    if (value != null) {
                        result.add(value);
                    }

                } else if (type == DataType.WORD || type == DataType.LONG) {
                    List<String> list = new ArrayList<>(Arrays.asList(line.split("\\s+")));
                    for (String elem : list) {
                        T value = parseValue(elem, type);
                        if (value != null) {
                            result.add(value);
                        }
                    }
                } 

            }
        }
        return result;
    }
    
    /**
     * Helper method to parse string value to required type
     */
    @SuppressWarnings("unchecked")
    private static <T> T parseValue(String value, DataType type) {
        try {
            if (type == DataType.LINE || type == DataType.WORD) {
                return (T) value;
            } else if (type == DataType.LONG) {
                long input;
                try {
                    input = Long.parseLong(value);
                    System.out.println(input);
                    return (T) Long.valueOf(input);
                } catch (Exception e) {
                    System.out.println("value is not long " + " : " + e);
                }
                return null;
            } else {
                throw new IllegalArgumentException("Unsupported type: " + type);
            }
        } catch (NumberFormatException e) {
            System.err.println("Warning: Skipping invalid value: " + value);
            return null;
        }
    }
}