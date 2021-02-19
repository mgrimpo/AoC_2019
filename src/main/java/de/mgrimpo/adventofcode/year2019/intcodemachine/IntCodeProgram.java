package de.mgrimpo.adventofcode.year2019.intcodemachine;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class IntCodeProgram implements Cloneable {

  private final int[] memory;

  public IntCodeProgram(int[] memory) {
    this.memory = memory;
  }

  public static IntCodeProgram fromString(String programString) {
    var intCodeArray = programString.trim().split(",");
    return new IntCodeProgram(Arrays.stream(intCodeArray)
        .mapToInt(Integer::parseInt)
        .toArray());
  }

  /**
   * executes the program in {@code memory}. This potentially alters the {@code memory}
   */
  public void execute() {
    int instructionPointer = 0;
    while (instructionPointer < memory.length) {
      var instruction = InstructionFactory.createInstruction(memory, instructionPointer);
      if (instruction instanceof HaltingInstruction) {
        break;
      }
      var newInstructionPointer = instruction.execute(memory);
      instructionPointer = newInstructionPointer
          .orElse(instructionPointer + instruction.length());
    }
  }

  /**
   * execute the program in {@code memory}, cf. {@link #execute}. Additionally, this method sets the
   * input that the program will read in its {@link InputInstruction}s to the parameter {@code
   * input}. Standard input will be ignored during the execution of this method. Standard output
   * will be captured and is contained in the return value.
   *
   * @param input the list of values read by {@link InputInstruction}s, separated by newlines
   * @return the output that is generated by {@link OutputInstruction}s
   */
  public String executeWithPresetInput(String input) {
    var in = new ByteArrayInputStream(input.getBytes());
    var originalOut = System.out;
    var originalIn = System.in;
    var outByteStream = new ByteArrayOutputStream();
    var out = new PrintStream(outByteStream);
    System.setIn(in);
    System.setOut(out);
    this.execute();
    System.setOut(originalOut);
    System.setIn(originalIn);
    return outByteStream.toString();
  }

  /**
   * read the value of the {@code memory} at {@code index}
   *
   * @return the value of the {@code memory} at {@code index}
   */
  public int valueAt(int index) {
    return memory[index];
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    IntCodeProgram that = (IntCodeProgram) o;
    return Arrays.equals(memory, that.memory);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(memory);
  }

  public void setMemory(int index, int value) {
    memory[index] = value;
  }

  public IntCodeProgram clone() {
    return new IntCodeProgram(this.memory.clone());
  }
}
