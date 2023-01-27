package utils;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class ObjectsArgumentsProvider implements ArgumentsProvider {
  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext){
    return Stream.of(
            Arguments.of("Arina"),
            Arguments.of("Anna"),
            Arguments.of("Sofia")
    );
  }
}
