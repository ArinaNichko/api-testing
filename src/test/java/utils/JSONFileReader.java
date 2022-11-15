package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Product;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;


public class JSONFileReader {
    static ObjectMapper objectMapper = new ObjectMapper();

    public static Product readJSONFile(String path) {
        try {
            return objectMapper.readValue(new File(path), Product.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String JSOnFileToString(String path) {
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String JSOnFileGetMessage(String path) {
        try {
            return (String) objectMapper.readValue(path, Map.class).get("message");
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}