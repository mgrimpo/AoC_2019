package de.mgrimpo.adventofcode.year2019.intcodemachine;

import java.util.Optional;

public class HaltingInstruction extends IntCodeInstruction {

  HaltingInstruction(int[] programMemory, int instructionPointer) {
    super(programMemory, instructionPointer);
  }

  @Override
  protected int numberOfParameters() {
    return 0;
  }

  @Override
  public Optional<Integer> execute(int[] programMemory) {
    return Optional.empty();
  }
}
