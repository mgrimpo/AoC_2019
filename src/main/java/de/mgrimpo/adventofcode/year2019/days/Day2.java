/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package de.mgrimpo.adventofcode.year2019.days;


import de.mgrimpo.adventofcode.year2019.intcodemachine.HaltingInstruction;
import de.mgrimpo.adventofcode.year2019.intcodemachine.InstructionFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Day2 implements Day {

  private final int[] puzzleInput;

  public static int[] executeIntCodeProgram(int[] programMemory) {
    programMemory = programMemory.clone();
    for (int instructionPointer = 0; instructionPointer < programMemory.length; instructionPointer += 4) {
      var instruction = InstructionFactory.createInstruction(programMemory,instructionPointer);
      if (instruction instanceof HaltingInstruction) {
        break;
      }
      instruction.execute(programMemory);
    }
    return programMemory;
  }


  public Day2() throws IOException {
    puzzleInput = readPuzzleInput(Paths.get("input/day2_input.txt"));
  }

  @Override
  public String puzzleTwoSolution() {
    final var desiredOutput = 19690720;
    final var nounVerbCode = findInputForOutput(desiredOutput, puzzleInput);
    return String.format("The desired 'noun * 100 + verb' code is: %s", nounVerbCode);
  }

  private static int findInputForOutput(int desiredOutput, int[] programMemory) {
    for (int nounVerbCode = 0; nounVerbCode < 10000; nounVerbCode++) {
      var noun = nounVerbCode / 100;
      var verb = nounVerbCode % 100;
      var memoryCopy = programMemory.clone();
      setInputMemory(memoryCopy, noun, verb);
      var resultMemory = executeIntCodeProgram(memoryCopy);
      if (resultMemory[0] == desiredOutput) return nounVerbCode;
    }
    throw new RuntimeException("No noun/verb combination for the desired output was found.");
  }

  private static void setInputMemory(int[] programMemory, int noun, int verb) {
    programMemory[1] = noun;
    programMemory[2] = verb;
  }

  public String puzzleOneSolution() {
    restore1202AlarmState(puzzleInput);
    var resultIntCodeState = executeIntCodeProgram(puzzleInput);
    var puzzleOneSolution = resultIntCodeState[0];
    return String.format("The value at position 0 is: %s", puzzleOneSolution);
  }

  private static void restore1202AlarmState(int[] programMemory) {
    setInputMemory(programMemory, 12, 02);
  }

  private static int[] readPuzzleInput(Path path) throws IOException {
    var intCodeArray = Files.readString(path).split(",");
    return Arrays.stream(intCodeArray)
        .mapToInt(Integer::parseInt)
        .toArray();
  }

}