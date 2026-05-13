package read_write_files.write_files;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        /* How to write files using java (4 options)
        *
        * FileWriter -> Good for small and medium size text files
        * BufferedWriter -> better performance for large text
        * PrintWriter -> structured data like reports or logs
        * FileOutputStream -> for music and images (binary files)
        * */

        String path = "/Users/samarthtikotkar/Desktop/test.txt";
        String textContent = """
                Ayo long time no see huh?
                Where u been busy man?
                Its like i havent seen u forever
                Bye!!
                """;

        try(FileWriter writer = new FileWriter(path)) {
            writer.write(textContent);
            System.out.println("Wrote in the file");
        }
        catch(FileNotFoundException ex) {
            System.out.printf("Could not find the file at path: %s", path);
        }
        catch(IOException ex) {
            System.out.println("Could not write file");
        }

    }
}
