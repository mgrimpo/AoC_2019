package de.mgrimpo.adventofcode.year2019.intcodemachine;

import java.util.Optional;

public class OutputInstruction extends IntCodeInstruction {

  public static final String outputSeparator = "\n";

  OutputInstruction(int[] programMemory, int instructionPointer) {
    super(programMemory, instructionPointer);
  }

  @Override
  protected int numberOfParameters() {
    return 1;
  }


  @Override
  public Optional<Integer> execute(int[] programMemory) {
    var outputValue = programMemory[parameters.get(0).uninterpretedValue()];
    System.out.printf("%s%s", outputValue, outputSeparator);
    return Optional.empty();
  }
}
