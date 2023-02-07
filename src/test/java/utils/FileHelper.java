package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.TestExecutionException;
import models.Product;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileHelper {

  static ObjectMapper objectMapper = new ObjectMapper();
  private static final String PATH_SEPARATOR_REGEXP = ", ";

  public static <T> T readJsonFileAsObject(String path, Class<T> classToCast) {
    try {
      return objectMapper.readValue(new File(path), classToCast);
    } catch (IOException ioException) {
      throw new TestExecutionException("Problem with json file: %s", ioException.getCause());
    }
  }

  public static <T> T readJsonStringAsObject(String jsonString, Class<T> classToCast) {
    try {
      return objectMapper.readValue(jsonString, classToCast);
    } catch (JsonProcessingException e) {
      throw new TestExecutionException(e.getMessage());
    }
  }

  public static String readJsonFileAsString(String path) {
    try {
      return Files.readString(Path.of(path));
    } catch (IOException ioException) {
      throw new TestExecutionException(ioException.getMessage());
    }
  }

  public static List<Product> readFileAsProduct(String path) {
    try (Stream<String> br = new BufferedReader(new FileReader(path)).lines()) {
      return br.skip(1).map(line -> new Product(line.split(PATH_SEPARATOR_REGEXP))).collect(Collectors.toList());
    } catch (FileNotFoundException e) {
      throw new TestExecutionException(e.getMessage());
    }
  }
}
