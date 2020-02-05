package com.willjsporter;

import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;

public class FileStreamerUtilTest {

  @Test
  public void shouldPutFileLinesIntoStream() {
    Stream<String> fileStream = FileStreamerUtil.streamFile("src/test/resources/stringTestInput.txt");
    List<String> expectedFileContents = List.of("hello", "world", "testing");

    boolean doFileContentsMatchExpectedList = fileStream.allMatch(expectedFileContents::contains);
    assertTrue(doFileContentsMatchExpectedList);
  }
}
