package de.mgrimpo.adventofcode.year2019.intcodemachine;

import java.util.Optional;

public class EqualsInstruction extends IntCodeInstruction {

  EqualsInstruction(int[] programMemory, int instructionPointer) {
    super(programMemory, instructionPointer);
  }

  @Override
  protected int numberOfParameters() {
    return 3;
  }

  @Override
  public Optional<Integer> execute(int[] programMemory) {
    if (parameters.get(0).interpretParameterValue(programMemory) ==
        parameters.get(1).interpretParameterValue(programMemory)) {
      programMemory[parameters.get(2).getUninterpretedParameterValue()] = 1;
    }
    return Optional.empty();
  }
}
