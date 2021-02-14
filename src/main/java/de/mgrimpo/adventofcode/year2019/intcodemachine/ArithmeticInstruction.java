package de.mgrimpo.adventofcode.year2019.intcodemachine;

import java.util.Optional;

class ArithmeticInstruction extends IntCodeInstruction {

  private final int firstParameter;
  private final int secondParameter;
  private final int thirdParameter;

  ArithmeticInstruction(int[] programMemory, int instructionPointer) {
    super(programMemory, instructionPointer);
    firstParameter = programMemory[instructionPointer + 1];
    secondParameter = programMemory[instructionPointer + 2];
    thirdParameter = programMemory[instructionPointer + 3];
  }

  @Override
  public Optional<Integer> execute(int[] programMemory) {
    int result;
    switch (opCode) {
      case 1:
        result = add(programMemory);
        break;
      case 2:
        result = multiply(programMemory);
        break;
      default:
        throw new RuntimeException("Encountered unimplemented op code");
    }
    programMemory[thirdParameter] = result;
    return Optional.of(result);
  }

  private int multiply(int[] programMemory) {
    int result;
    result =
        getParameterValue(firstParameterMode, firstParameter, programMemory) * getParameterValue(
            secondParameterMode, secondParameter,
            programMemory);
    return result;
  }

  private int add(int[] programMemory) {
    int result;
    result = getParameterValue(firstParameterMode, firstParameter, programMemory)
        + getParameterValue(secondParameterMode, secondParameter, programMemory);
    return result;
  }
}
