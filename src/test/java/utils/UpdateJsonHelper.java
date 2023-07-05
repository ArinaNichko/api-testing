package utils;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class UpdateJsonHelper {

  public String updateFieldByPath(
          String pathToJsonFile, String fullFieldPath, Object valueToUpdate) {
    DocumentContext documentContext = JsonPath.parse(pathToJsonFile);
    documentContext.set(fullFieldPath, valueToUpdate);

    return documentContext.jsonString();
  }

  public <T> String updateFieldByPath(
          String pathToFile, String fullFieldPath, Object valueToUpdate, Class<T> classToCast) {
    DocumentContext documentContext = JsonPath.parse(pathToFile);
    documentContext.set(fullFieldPath, valueToUpdate);

    return documentContext.jsonString();
  }
}
