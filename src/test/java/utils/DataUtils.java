package utils;

import java.util.stream.Stream;

public class DataUtils {

  private static Stream<Object> provideObjectsData() {
    return Stream.of("Lviv", "Kyiv", "Varna", "Burgas");
  }
}
