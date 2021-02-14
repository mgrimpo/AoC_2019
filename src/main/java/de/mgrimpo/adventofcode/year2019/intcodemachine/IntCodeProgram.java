package de.mgrimpo.adventofcode.year2019.intcodemachine;

import java.util.Arrays;

public class IntCodeProgram implements Cloneable{

  private final int[] programMemory;

  public IntCodeProgram(int[] programMemory) {
    this.programMemory = programMemory;
  }

  public static IntCodeProgram fromString(String programString) {
    var intCodeArray = programString.split(",");
    return new IntCodeProgram(Arrays.stream(intCodeArray)
        .mapToInt(Integer::parseInt)
        .toArray());
  }

  public IntCodeProgram execute() {
    var operatingMemory = programMemory.clone();
    for (int instructionPointer = 0; instructionPointer < operatingMemory.length; instructionPointer += 4) {
      var instruction = InstructionFactory.createInstruction(operatingMemory,instructionPointer);
      if (instruction instanceof HaltingInstruction) {
        break;
      }
      instruction.execute(operatingMemory);
    }
    return new IntCodeProgram(operatingMemory);
  }

  public int valueAt(int index) {
    return programMemory[index];
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    IntCodeProgram that = (IntCodeProgram) o;
    return Arrays.equals(programMemory, that.programMemory);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(programMemory);
  }

  public void setMemory(int index, int value) {
    programMemory[index] = value;
  }

  public IntCodeProgram clone() {
   return new IntCodeProgram(this.programMemory.clone());
  }
}
