package de.mgrimpo.adventofcode.year2019.days;

import de.mgrimpo.adventofcode.year2019.intcodemachine.IntCodeProgram;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day5 implements Day {

  private final String input;

  public Day5() throws IOException {
    this.input = Files.readString(Path.of("input/day5_input.txt"));
  }

  @Override
  public String puzzleOneSolution() {
    IntCodeProgram day5Program = IntCodeProgram.fromString(input);
    System.out.println("Executing Day5, Puzzle 1, please input 1 at the next prompt.");
    day5Program.execute();
    return "Solution is outputted above.";
  }

  @Override
  public String puzzleTwoSolution() {
    IntCodeProgram day5Program = IntCodeProgram.fromString(input);
    System.out.println("Executing Day5, Puzzle 2, please input 5 at the next prompt.");
    day5Program.execute();
    return "Solution is outputted above.";
  }
}
