package files;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import org.apache.commons.io.FileUtils;

/**
 * This is a helper class used for file operations
 */
public class Helper {
    private static FileReader fileReader;
    private static BufferedReader bufferedReader;
    private static BufferedWriter bufferedWriter;

    /**
     * Method to returna n absolute path
     * @param path path from resources
     * @return the absolute path
     * @throws URISyntaxException if the uri is null
     */
    public static String getAbsPath(String path) throws URISyntaxException {
        //get absolute path
        URL url = Helper.class.getClassLoader().getResource(path);
        File file = Paths.get(url.toURI()).toFile();
        return file.getAbsolutePath();
    }


    /**
     * Reads file fromresources
     * @param filename filename to read
     * @return String of file contents
     * @throws IOException if the file cannot be obtained
     * @throws URISyntaxException if the path is incorrect
     */
    public static String readResourcesFileAsString(String filename) throws IOException, URISyntaxException {
        //get absolute path
        String absolutePath =getAbsPath(filename);

        //read file
        Helper.fileReader = new FileReader(absolutePath);
        Helper.bufferedReader = new BufferedReader(Helper.fileReader);
        StringBuilder content = new StringBuilder();
        String line;
        //add contents line by line
        while((line = Helper.bufferedReader.readLine()) != null)
        {
            content.append(line);
        }

        return content.toString();
    }


    /**
     * Method to write file
     * @param directory directory
     * @param filename filename
     * @param content content
     * @throws IOException if the contents cannot be written
     */
    public static void writeFile(String directory, String filename, String content) throws IOException {

        File dir = new File(directory);

        //if directory doe snot exist, create it
        if(!dir.exists())
            dir.mkdir();

        filename = directory + "/" + filename;
        Helper.bufferedWriter = new BufferedWriter(new FileWriter(filename));
        Helper.bufferedWriter.write(content);
        Helper.bufferedWriter.close();
    }

    /**
     * Method to copy directory
     * @param sourceFilename source
     * @param destinationFilename destination
     * @throws IOException if the directory cannot be copied
     */
    public static void copyDirectory(String sourceFilename, String destinationFilename) throws IOException, URISyntaxException {
        //get absolute path
        String absolutePath =getAbsPath(sourceFilename);
        File src = new File(absolutePath);
        File dest = new File(destinationFilename);

        FileUtils.copyDirectory(src, dest);
    }

    /**
     * Method to copy file
     * @param sourceFilename source
     * @param destinationFilename destination
     * @throws IOException if the file cannot be copied
     */
    public static void copyFile(String sourceFilename, String destinationFilename) throws IOException, URISyntaxException {
        //get absolute path
        String absolutePath =getAbsPath(sourceFilename);
        File src = new File(absolutePath);
        File dest = new File(destinationFilename);

        FileUtils.copyFile(src, dest);
    }

    /**
     * Method to create directory
     * @param directory new dir
     */
    public static void createDirectory(String directory){
        File file = new File(directory);
        file.mkdir();
    }

    /**
     * Method used by tests to check how many times a sequence occurs in a string
     * @param string actual string
     * @param sequence sequence to check for
     * @return number of occurences
     */
    public static int getOccurences(String string, String sequence)
    {
        int occurences = 0;
        string = string.replaceAll(" ","");

        //get words
        String words[] = string.split("><");


        for (String word: words)
        {
            //if equal
            if (sequence.equals(word))
                occurences++;
        }

        return occurences;
    }

}
