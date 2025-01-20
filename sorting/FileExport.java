package sorting;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class FileExport {
    public static void export(String fileName) {
        File fileToExport = new File("./" + fileName);

        try (PrintWriter writer = new PrintWriter(fileToExport)) {
            for (String line : SaveInOut.getInputOutput()) {
                writer.printf("%s", line);
            }
            System.out.println("Save file completed");
        } catch (IOException e) {
            SaveInOut.println(false, "error : " + e.getMessage());
        }

    }
}
