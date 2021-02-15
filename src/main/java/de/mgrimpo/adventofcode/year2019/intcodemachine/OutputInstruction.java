package de.mgrimpo.adventofcode.year2019.intcodemachine;

import java.util.Optional;

public class OutputInstruction extends IntCodeInstruction{

  OutputInstruction(int[] programMemory, int instructionPointer) {
    super(programMemory, instructionPointer);
  }

  @Override
  protected int numberOfParameters() {
    return 1;
  }

  @Override
  public Optional<Integer> execute(int[] programMemory) {
    var outputValue = programMemory[parameters.get(0).getUninterpretedParameterValue()];
    System.out.printf("Encountered an OutputInstruction. The output is: %s\n", outputValue);
    return Optional.empty();
  }
}
