package utils;

import exceptions.TestExecutionException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesHelper {
    private static final Properties properties = new Properties();

    public static void initializeProperties() {
        String path = "src/test/resources/config/config.properties";
        try {
            properties.load(new FileReader(path));
        } catch (FileNotFoundException fileNotFoundException) {
            throw new TestExecutionException("There is NO file with properties in path: %s", path);
        } catch (IOException ioException) {
            throw new TestExecutionException("Problem with properties file: %s", ioException.getCause());
        }
    }

    public static String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }
}
