package de.mgrimpo.adventofcode.year2019.intcodemachine;

import java.util.Optional;
import java.util.Scanner;

public class InputInstruction extends IntCodeInstruction {

  InputInstruction(int[] programMemory, int instructionPointer) {
    super(programMemory, instructionPointer);
  }

  @Override
  protected int numberOfParameters() {
    return 1;
  }

  @Override
  public Optional<Integer> execute(int[] programMemory) {
    Scanner in = new Scanner(System.in);
    var input = Integer.parseInt(in.nextLine().trim());
    writeToAddressInParameter(0, input, programMemory);
    return Optional.empty();
  }
}
