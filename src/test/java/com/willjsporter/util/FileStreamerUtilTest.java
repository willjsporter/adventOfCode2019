package com.willjsporter.util;

import org.junit.Test;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class FileStreamerUtilTest {

  @Test
  public void shouldPutListOfIntegersFromFileIntoStream() {
    List<Integer> fileStream = FileStreamerUtil.streamFileInputAsIntegers("src/test/resources/integerTestInput_List.txt");
    List<Integer> expectedFileContents = List.of(1, 2, 5, 6, 2, 4, 200);

    assertThat(fileStream, is(expectedFileContents));
  }
  @Test
  public void shouldPutListOfDirectionsFromFile_IntoAListOfStreams() {
    List<Stream<String>> fileStream = FileStreamerUtil.streamFileInputAsDirections("src/test/resources/directionsTestInput.txt");

    List<String> expectedFirstDirections = List.of("U1","L3","U10","R1","D10");
    List<String> expectedSecondDirections = List.of("D3","L4","U1","R5");

    boolean doFirstDirectionsMatchExpectedList = fileStream.get(0).allMatch(expectedFirstDirections::contains);
    boolean doSecondDirectionsMatchExpectedList = fileStream.get(1).allMatch(expectedSecondDirections::contains);
    assertTrue(doFirstDirectionsMatchExpectedList && doSecondDirectionsMatchExpectedList);
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
