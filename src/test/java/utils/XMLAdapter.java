package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import exceptions.TestExecutionException;

import java.io.File;
import java.io.IOException;

public class XMLAdapter extends UpdateJsonHelper {
  private static final XmlMapper xmlMapper = new XmlMapper();
  private static final ObjectMapper objectMapper = new ObjectMapper();
  private final UpdateXmlHelper xmlHelper;

  public XMLAdapter(UpdateXmlHelper xmlHelper) {
    this.xmlHelper = xmlHelper;
  }

  public static <T> String convertXmlToJsonNode(String pathToFile, Class<T> classToCast) {
    try {
      T data = xmlMapper.readValue(new File(pathToFile), classToCast);

      return objectMapper.writeValueAsString(data);

    } catch (IOException e) {
      throw new TestExecutionException("Problem with xml file: %s", e.getCause());
    }
  }

  @Override
  public <T> String updateFieldByPath(
          String pathToFile, String fullFieldPath, Object valueToUpdate, Class<T> classToCast) {
    String jsonString = convertXmlToJsonNode(pathToFile, classToCast);
    DocumentContext documentContext = JsonPath.parse(jsonString);
    documentContext.set(fullFieldPath, valueToUpdate);

    return documentContext.jsonString();
  }
}
