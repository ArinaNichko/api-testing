package utils;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class UpdateJsonHelper {

  public static String updateFieldByPath(
          String pathToJsonFile, String fullFieldPath, Object valueToUpdate) {
    DocumentContext documentContext = JsonPath.parse(pathToJsonFile);
    documentContext.set(fullFieldPath, valueToUpdate);

    return documentContext.jsonString();

  }
}
