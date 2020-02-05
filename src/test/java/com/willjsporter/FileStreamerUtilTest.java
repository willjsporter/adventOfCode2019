package com.willjsporter;

import org.junit.Test;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class FileStreamerUtilTest {

  @Test
  public void shouldPutFileLinesIntoStream() {
    Stream<String> fileStream = FileStreamerUtil.streamFile("src/test/resources/stringTestInput.txt");
    List<String> expectedFileContents = List.of("hello", "world", "testing");

    boolean doFileContentsMatchExpectedList = fileStream.allMatch(expectedFileContents::contains);
    assertTrue(doFileContentsMatchExpectedList);
  }

  @Test
  public void shouldBeAbleToGetIntegersFromFileLines() {
    IntStream fileAsIntStream = FileStreamerUtil.fileLinesAsIntegers("src/test/resources/integerTestInput.txt");
    List<Integer> expectedFileContentsAsIntegers = List.of(2, 11, 111, 1111);

    boolean doFileContentsMatchExpectedList = fileAsIntStream.allMatch(expectedFileContentsAsIntegers::contains);
    assertTrue(doFileContentsMatchExpectedList);
  }
}
