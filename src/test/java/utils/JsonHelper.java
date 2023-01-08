package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import exceptions.TestExecutionException;

import java.util.Arrays;
import java.util.List;

import static com.google.common.collect.Iterables.getLast;
import static com.google.common.collect.Iterables.limit;
import static utils.JSONFileReader.JSOnFileToString;

public class JsonHelper {
  private static final String PATH_SEPARATOR_REGEXP = "\\.";
  private static final String PATH_SEPARATOR = ".";

  public static <T> T updateFieldsByPath(
      String jsonPath, String fullPath, Object valueToUpdate, Class<T> classToCast) {
    DocumentContext documentContext = JsonPath.parse(JSOnFileToString(jsonPath));

    List<String> pathLevels = Arrays.asList(fullPath.split(PATH_SEPARATOR_REGEXP));
    String path = String.join(PATH_SEPARATOR, limit(pathLevels, pathLevels.size() - 1));
    String fieldToUpdate = getLast(pathLevels);
    Object field = documentContext.read(fullPath);
    if (field instanceof List) {
      documentContext.put(path, fieldToUpdate, valueToUpdate);
    } else if (field instanceof String) {
      documentContext.put(path, fieldToUpdate, valueToUpdate);
    }

    try {
      return new ObjectMapper().readValue(documentContext.jsonString(), classToCast);
    } catch (JsonProcessingException e) {
      throw new TestExecutionException(e.getMessage());
    }
  }
}
