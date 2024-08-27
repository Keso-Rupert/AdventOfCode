package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class InputReader {

    Supplier<Stream<String>> inputSupplier;

    public InputReader(String inputLocation) {
        inputSupplier = () -> {
            try {
                return Files.lines(Path.of(inputLocation));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        };
    }

    public Stream<String> getInputStreamForLines() {
        return inputSupplier.get();
    }

}
