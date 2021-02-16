package de.mgrimpo.adventofcode.year2019.intcodemachine;

import java.util.Optional;

public class LessThanInstruction extends IntCodeInstruction {


  LessThanInstruction(int[] programMemory, int instructionPointer) {
    super(programMemory, instructionPointer);
  }

  @Override
  protected int numberOfParameters() {
    return 3;
  }

  @Override
  public Optional<Integer> execute(int[] programMemory) {
    if (parameters.get(0).interpret(programMemory) <
        parameters.get(1).interpret(programMemory)) {
      writeToAddressInParameter(2, 1, programMemory);
    } else {
      writeToAddressInParameter(2, 0, programMemory);
    }
    return Optional.empty();
  }
}
