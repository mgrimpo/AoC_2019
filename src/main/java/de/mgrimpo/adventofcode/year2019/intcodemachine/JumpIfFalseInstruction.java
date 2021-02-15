package de.mgrimpo.adventofcode.year2019.intcodemachine;

import java.util.Optional;

public class JumpIfFalseInstruction extends IntCodeInstruction{

  JumpIfFalseInstruction(int[] programMemory, int instructionPointer) {
    super(programMemory, instructionPointer);
  }

  @Override
  protected int numberOfParameters() {
    return 2;
  }

  @Override
  public Optional<Integer> execute(int[] programMemory) {
    if (parameters.get(0).interpretParameterValue(programMemory) == 0){
      return Optional.of(
          parameters.get(1).interpretParameterValue(programMemory)
      );
    }
    return Optional.empty();
  }
}
