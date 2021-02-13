package de.mgrimpo.adventofcode.year2019.intcodemachine;

import java.util.Optional;

public abstract class IntCodeInstruction {

  public final int opCode;

  IntCodeInstruction(int[] programMemory, int instructionPointer) {
    this.opCode = programMemory[instructionPointer];
  }

  abstract public Optional<Integer> execute(int[] programMemory);
}
