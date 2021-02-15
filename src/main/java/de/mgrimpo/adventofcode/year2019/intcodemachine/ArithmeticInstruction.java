package de.mgrimpo.adventofcode.year2019.intcodemachine;

import java.util.Optional;

class ArithmeticInstruction extends IntCodeInstruction {


  ArithmeticInstruction(int[] programMemory, int instructionPointer) {
    super(programMemory, instructionPointer);
  }

  @Override
  protected int numberOfParameters() {
    return 3;
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
    programMemory[parameters.get(2).getUninterpretedParameterValue()] = result;
    return Optional.empty();
  }

  private int multiply(int[] programMemory) {
    return parameters.get(0).interpretParameterValue(programMemory) *
        parameters.get(1).interpretParameterValue(programMemory);
  }

  private int add(int[] programMemory) {
    return parameters.get(0).interpretParameterValue(programMemory) +
        parameters.get(1).interpretParameterValue(programMemory);
  }
}
