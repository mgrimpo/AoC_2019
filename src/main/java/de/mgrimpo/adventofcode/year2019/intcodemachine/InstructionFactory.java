package de.mgrimpo.adventofcode.year2019.intcodemachine;

public class InstructionFactory {

  private InstructionFactory() {
  }

  public static IntCodeInstruction createInstruction(int[] programMemory, int instructionPointer) {
    var opCode = programMemory[instructionPointer] % 100;
    if (opCode == 1 || opCode == 2) {
      return new ArithmeticInstruction(programMemory, instructionPointer);
    } else if (opCode == 3) {
      return new InputInstruction(programMemory, instructionPointer);
    } else if (opCode == 4) {
      return new OutputInstruction(programMemory, instructionPointer);
    } else if (opCode == 5) {
      return new JumpIfTrueInstruction(programMemory, instructionPointer);
    } else if (opCode == 6) {
      return new JumpIfFalseInstruction(programMemory, instructionPointer);
    } else if (opCode == 7) {
      return new LessThanInstruction(programMemory, instructionPointer);
    } else if (opCode == 8) {
      return new EqualsInstruction(programMemory, instructionPointer);
    } else if (opCode == 99) {
      return new HaltingInstruction(programMemory, instructionPointer);
    } else {
      throw new RuntimeException("Invalid Op Code: " + opCode);
    }
  }
}
