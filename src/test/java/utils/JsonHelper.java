package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.TestExecutionException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonHelper {

  static ObjectMapper objectMapper = new ObjectMapper();

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
}
