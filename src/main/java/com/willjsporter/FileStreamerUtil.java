package com.willjsporter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FileStreamerUtil {

    static IntStream streamFileInputAsIntegers(String filepath) {
        String projectRootDirectory = System.getProperty("user.dir") + "/";
        try {
            String fileContents = Files
                .readString(Paths.get(projectRootDirectory + filepath), StandardCharsets.UTF_8)
                .trim();
            return Stream.of(fileContents.split(","))
                .mapToInt(Integer::valueOf);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static Stream<String> streamFileLines(String filepath) {
        String projectRootDirectory = System.getProperty("user.dir") + "/";
        try {
            return Files.lines(Paths.get(projectRootDirectory + filepath));
        } catch (IOException e) {
            return Stream.of(e.getMessage());
        }
    }

    public static IntStream fileLinesAsIntegers(String filepath) {
        return streamFileLines(filepath)
            .mapToInt(Integer::valueOf);
    }
}
