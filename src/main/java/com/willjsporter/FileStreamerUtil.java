package com.willjsporter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FileStreamerUtil {

    static List<Integer> streamFileInputAsIntegers(String filepath) {
        String projectRootDirectory = System.getProperty("user.dir") + "/";
        try {
            String fileContents = Files
                .readString(Paths.get(projectRootDirectory + filepath), StandardCharsets.UTF_8)
                .trim();
            return List.of(fileContents.split(","))
                .stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());
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
