package de.mgrimpo.adventofcode.year2019.days;

import de.mgrimpo.adventofcode.year2019.intcodemachine.IntCodeProgram;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day5 implements Day {

  @Override
  public String puzzleOneSolution() {
    try {
      IntCodeProgram day5Program = IntCodeProgram.fromString(
          Files.readString(Path.of("input/day5_input.txt")));
      System.out.println("Executing Day5, please input 1 at the next prompt.");
      day5Program.execute();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "Solution is outputted above.";
  }

  @Override
  public String puzzleTwoSolution() {
    return "NOT YET SOLVED!";
  }
}
