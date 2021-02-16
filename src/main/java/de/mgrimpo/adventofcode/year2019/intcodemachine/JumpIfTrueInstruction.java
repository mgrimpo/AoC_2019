package de.mgrimpo.adventofcode.year2019.intcodemachine;

import java.util.Optional;

public class JumpIfTrueInstruction extends IntCodeInstruction {

  JumpIfTrueInstruction(int[] programMemory, int instructionPointer) {
    super(programMemory, instructionPointer);
  }

  @Override
  protected int numberOfParameters() {
    return 2;
  }

  @Override
  public Optional<Integer> execute(int[] programMemory) {
    if (parameters.get(0).interpret(programMemory) != 0) {
      return Optional.of(
          parameters.get(1).interpret(programMemory));
    }
    return Optional.empty();
  }
}
