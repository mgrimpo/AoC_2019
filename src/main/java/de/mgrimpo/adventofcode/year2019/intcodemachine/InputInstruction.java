package de.mgrimpo.adventofcode.year2019.intcodemachine;

import java.util.Optional;
import java.util.Scanner;

public class InputInstruction extends IntCodeInstruction {


  private final Scanner scanner;


  InputInstruction(Scanner scanner, int[] programMemory, int instructionPointer) {
    super(programMemory, instructionPointer);
    this.scanner = scanner;
  }

  @Override
  protected int numberOfParameters() {
    return 1;
  }

  @Override
  public Optional<Integer> execute(int[] programMemory) {
    var input = Integer.parseInt(scanner.nextLine().trim());
    writeToAddressInParameter(0, input, programMemory);
    return Optional.empty();
  }
}
