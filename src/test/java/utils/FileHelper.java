package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import exceptions.TestExecutionException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileHelper {
  static ObjectMapper objectMapper = new ObjectMapper();
  static CsvMapper csvMapper = new CsvMapper();

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

  public static <T> List<T> readCsvFileAsObject(File file, Class<T> classToCast) {
    CsvSchema classSchema = csvMapper.schemaFor(classToCast).withHeader();
    try {
      MappingIterator<T> it = csvMapper
              .readerFor(classToCast)
              .with(classSchema)
              .readValues(file);
      return it.readAll();
    } catch (IOException e) {
      throw new TestExecutionException(e.getMessage());
    }
  }
}

