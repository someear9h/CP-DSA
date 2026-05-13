package read_write_files.read_files;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        /* How to read files using java (3 options)
         *
         * BufferedReader + FileReader -> best for reading text files line by line
         * FileInputStream -> for music and images (binary files)
         * RandomAccessFile -> Best for read/write specific portions of large file
         * */

        String filePath = "/Users/samarthtikotkar/Desktop/test.txt";

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            System.out.println("That file exists");

            String line;
            while((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
        catch(FileNotFoundException ex) {
            System.out.printf("Could not locate the file on path: %s\n", filePath);
            System.out.println("Check is the path is correct and file exists");
        }
        catch(IOException ex) {
            System.out.println("Something went wrong");
        }
    }
}
