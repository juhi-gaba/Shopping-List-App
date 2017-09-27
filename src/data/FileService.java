package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Juhi Gaba
 */
public class FileService {

    private static String USER_DATA_FILE_PATH = "/Users/juhi.gaba/shoppingListApp/src/dataFile/UserData";

    /**
     * Write to a file
     *
     * @param data Data to be written
     * @return Boolean
     */
    public boolean writeToUserData(String data) {

        return writeToFile(data, USER_DATA_FILE_PATH, true);
    }

    /**
     * Read from a file
     *
     * @return File data
     */
    public String readFileFromUserData() {

        return readFile(USER_DATA_FILE_PATH);
    }

    /**
     * Update user data.
     *
     * @param data Data to be updated.
     * @return Boolean
     */
    public boolean updateUserData(String data) {

        return writeToFile(data, USER_DATA_FILE_PATH, false);
    }

    /**
     * Access the file with the file path.
     *
     * @param filepath Path of the file to be accessed.
     * @return File object.
     */
    public File accessFile(String filepath) {
        return new File(filepath);
    }

    /**
     * Write to a file.
     * @param data      Data to be written to a file.
     * @param filePath  Path of the file.
     * @param isAppend  Boolean.
     * @return Writing to file was success or not.
     */
    private boolean writeToFile(String data, String filePath, boolean isAppend) {
        try {
            File file = accessFile(filePath);
            FileWriter writer = new FileWriter(file, isAppend);
            writer.write(data + "\n");
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Read from the file.
     *
     * @param filePath Path of the file to be read.
     * @return File data.
     */
    private String readFile(String filePath) {
        InputStream is;
        try {
            is = new FileInputStream(filePath);

            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(is, "UTF8"));

            String line = bufferedReader.readLine();

            StringBuilder builder = new StringBuilder();

            while (line != null) {
                builder.append(line).append("\n");
                line = bufferedReader.readLine();
            }

            is.close();
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
