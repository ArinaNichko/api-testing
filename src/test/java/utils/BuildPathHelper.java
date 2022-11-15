package utils;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class BuildPathHelper extends RuntimeException {

    public BuildPathHelper(String error) {
        super(error);
    }

    public static int SUCCESS_CODE = Integer.parseInt(propertiesReader("SUCCESS_CODE"));

    public static String propertiesReader(String get) {

        FileReader reader;
        try {
            reader = new FileReader("src/test/resources/config/Path.properties");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Properties p = new Properties();

        try {
            p.load(reader);
        } catch (IOException e) {
            throw new BuildPathHelper("Reader parameter is null");
        }

        return p.getProperty(get);
    }
}