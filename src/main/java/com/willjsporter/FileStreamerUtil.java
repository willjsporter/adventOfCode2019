package com.willjsporter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FileStreamerUtil {

    static Stream<String> streamFile(String filepath) {
      String projectRootDirectory = System.getProperty("user.dir") + "/";
      try {
          return Files.lines(Paths.get(projectRootDirectory + filepath));
      } catch (IOException e) {
          return Stream.of(e.getMessage());
      }
}

    public static IntStream fileLinesAsIntegers(String filepath) {
        return streamFile(filepath)
            .mapToInt(Integer::valueOf);
    }
}
