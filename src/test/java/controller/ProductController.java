package controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import models.Product;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;



public class ProductController {


    public static Product readJSONFile (String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(path), Product.class);
    }

    public static String JSOnFileToString (String path) throws IOException {
        String result;
        result = new String(Files.readAllBytes(Paths.get(path)));
        return result;

    }
    public static String JSOnFileGetMessage (String path) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONObject data = (JSONObject) parser.parse(
                new FileReader(path));
        return (String) data.get("message");

    }



}
