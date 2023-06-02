package stategies;

import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.TestExecutionException;

import java.io.File;
import java.io.IOException;

public class JsonReader  implements FileReaderStrategy {
  static ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public <T> T readFile(String path, Class<T> classToCast) {
    try {
      return objectMapper.readValue(new File(path), classToCast);
    } catch (IOException ioException) {
      throw new TestExecutionException("Problem with json file: %s", ioException.getCause());
    }
  }
}
