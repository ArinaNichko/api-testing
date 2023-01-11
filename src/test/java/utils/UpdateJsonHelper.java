package utils;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class UpdateJsonHelper {
//  private static final String PATH_SEPARATOR_REGEXP = "\\.";
//  private static final String PATH_SEPARATOR = ".";

//  public static <T> T updateFieldByPath(
//      String pathToJsonFile, String fullFieldPath, Object valueToUpdate, Class<T> classToCast) {
//    DocumentContext documentContext = JsonPath.parse(pathToJsonFile);
//    System.out.println(documentContext.jsonString());
//
//    List<String> pathLevels = Arrays.asList(fullFieldPath.split(PATH_SEPARATOR_REGEXP));
//    System.out.println(pathLevels);
//    String path = String.join(PATH_SEPARATOR, limit(pathLevels, pathLevels.size() - 1));
//    System.out.println(path);
//    String fieldToUpdate = getLast(pathLevels);
//    System.out.println(fieldToUpdate);
//     documentContext.put(path, fieldToUpdate, valueToUpdate);
//
//    try {
//      return new ObjectMapper().readValue(documentContext.jsonString(), classToCast);
//    } catch (JsonProcessingException e) {
//      throw new TestExecutionException(e.getMessage());
//    }
//  }

  public static String updateFieldByPath(
          String pathToJsonFile, String fullFieldPath, Object valueToUpdate) {
    DocumentContext documentContext = JsonPath.parse(pathToJsonFile);
    documentContext.set(fullFieldPath, valueToUpdate);

    return documentContext.jsonString();

  }
}
