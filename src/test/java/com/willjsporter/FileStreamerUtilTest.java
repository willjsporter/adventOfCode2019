package com.willjsporter;

import org.junit.Test;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;

public class FileStreamerUtilTest {

  @Test
  public void shouldPutListOfIntegersFromFileIntoStream() {
    IntStream fileStream = FileStreamerUtil.streamFileInputAsIntegers("src/test/resources/integerTestInput_List.txt");
    List<Integer> expectedFileContents = List.of(1, 2, 5, 6, 2, 4, 200);

    boolean doFileContentsMatchExpectedList = fileStream.allMatch(expectedFileContents::contains);
    assertTrue(doFileContentsMatchExpectedList);
  }

  @Test
  public void shouldPutFileLinesIntoStream() {
    Stream<String> fileStream = FileStreamerUtil.streamFileLines("src/test/resources/stringTestInput_Lines.txt");
    List<String> expectedFileContents = List.of("hello", "world", "testing");

    boolean doFileContentsMatchExpectedList = fileStream.allMatch(expectedFileContents::contains);
    assertTrue(doFileContentsMatchExpectedList);
  }

  @Test
  public void shouldBeAbleToGetIntegersFromFileLines() {
    IntStream fileAsIntStream = FileStreamerUtil.fileLinesAsIntegers("src/test/resources/integerTestInput_Lines.txt");
    List<Integer> expectedFileContentsAsIntegers = List.of(2, 11, 111, 1111);

    boolean doFileContentsMatchExpectedList = fileAsIntStream.allMatch(expectedFileContentsAsIntegers::contains);
    assertTrue(doFileContentsMatchExpectedList);
  }
}
