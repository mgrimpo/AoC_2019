package de.mgrimpo.adventofcode.year2019.intcodemachine;

import java.util.Optional;

class ArithmeticInstruction extends IntCodeInstruction {

  private final int firstOperandAddress;
  private final int secondOperandAddress;
  private final int resultAddress;

  ArithmeticInstruction(int[] programMemory, int instructionPointer) {
    super(programMemory, instructionPointer);
    firstOperandAddress = programMemory[instructionPointer + 1];
    secondOperandAddress = programMemory[instructionPointer + 2];
    resultAddress = programMemory[instructionPointer + 3];
  }

  @Override
  public Optional<Integer> execute(int[] programMemory) {
    int result;
    switch (opCode) {
      case 1:
        result = programMemory[firstOperandAddress] + programMemory[secondOperandAddress];
        break;
      case 2:
        result = programMemory[firstOperandAddress] * programMemory[secondOperandAddress];
        break;
      default:
        throw new RuntimeException("Encountered unimplemented op code");
    }
    programMemory[resultAddress] = result;
    return Optional.of(result);
  }
}
