package de.mgrimpo.adventofcode.year2019.intcodemachine;

public class InstructionFactory {
  private InstructionFactory() {}

  public static IntCodeInstruction createInstruction(int[] programMemory, int instructionPointer) {
    var opCode = programMemory[instructionPointer] % 100;
    if (opCode == 1 || opCode == 2) {
      return new ArithmeticInstruction(programMemory, instructionPointer);
    } else if (opCode == 99) {
      return new HaltingInstruction(programMemory, instructionPointer);
    } else {
      throw new RuntimeException("Invalid Op Code");
    }
  }
}
