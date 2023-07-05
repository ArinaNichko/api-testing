package stategies;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import exceptions.TestExecutionException;

import java.io.File;
import java.io.IOException;

public class CsvReader implements FileReaderStrategy {
  static CsvMapper csvMapper = new CsvMapper();

  @Override
  public <T> T readFile(String path, Class<T> classToCast) {
    CsvSchema classSchema = csvMapper.schemaFor(classToCast).withHeader();
    try {
      MappingIterator<T> it = csvMapper
              .readerFor(classToCast)
              .with(classSchema)
              .readValues(new File(path));
      if (it.hasNext()) {
        return it.next();
      }
    } catch (IOException e) {
      throw new TestExecutionException(e.getMessage());
    }
     throw new TestExecutionException("No object found in the CSV file: " + path);
  }
}
