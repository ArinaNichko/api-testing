package stategies;

public interface FileReaderStrategy {
  <T> T readFile(String path, Class<T> classToCast);
}
