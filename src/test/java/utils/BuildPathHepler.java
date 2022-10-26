package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class BuildPathHepler {

    public static int SUCCESS_CODE = Integer.parseInt(propertiesReader("SUCCESS_CODE"));
    public static String dataMessage = propertiesReader("dataMessage");

    public static String propertiesReader(String get) {


        FileReader reader = null;
        try {
            reader = new FileReader("src/test/resources/config/Path.properties");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Properties p = new Properties();

        try {
            p.load(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return p.getProperty(get);
    }


}
