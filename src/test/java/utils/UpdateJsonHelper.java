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

public class UpdateJsonHelper {
  private static final String PATH_SEPARATOR_REGEXP = "\\.";
  private static final String PATH_SEPARATOR = ".";

  public static <T> T updateFieldByPath(
      String pathToJsonFile, String fullFieldPath, Object valueToUpdate, Class<T> classToCast) {
    DocumentContext documentContext = JsonPath.parse(JSOnFileToString(pathToJsonFile));

    List<String> pathLevels = Arrays.asList(fullFieldPath.split(PATH_SEPARATOR_REGEXP));
    String path = String.join(PATH_SEPARATOR, limit(pathLevels, pathLevels.size() - 1));
    String fieldToUpdate = getLast(pathLevels);
    documentContext.put(path, fieldToUpdate, valueToUpdate);

    try {
      return new ObjectMapper().readValue(documentContext.jsonString(), classToCast);
    } catch (JsonProcessingException e) {
      throw new TestExecutionException(e.getMessage());
    }
  }
}
