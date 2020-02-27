package com.willjsporter;

import com.willjsporter.util.FileStreamerUtil;

import java.util.List;

public class Main {
  public static void main(String[] args) {
    List<Integer> day2Input = FileStreamerUtil.streamFileInputAsIntegers("puzzleInput/day2a.txt");
    int _100TimesNounTimesVerb = 0;
    for(int i = 0; i < day2Input.size(); i ++) {
      for(int j = 0; j < day2Input.size(); j++) {
        day2Input.set(1, i);
        day2Input.set(2, j);
        if (new IntcodeProgram(day2Input).run().get(0) == 19690720) {
          _100TimesNounTimesVerb = 100 * day2Input.get(1) + day2Input.get(2);
          break;
        }
      }
    }
    System.out.println(_100TimesNounTimesVerb);
  }
}
