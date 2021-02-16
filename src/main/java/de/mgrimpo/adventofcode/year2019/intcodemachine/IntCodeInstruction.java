package de.mgrimpo.adventofcode.year2019.intcodemachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class IntCodeInstruction {

  protected final int opCode;
  protected List<Parameter> parameters;

  IntCodeInstruction(int[] programMemory, int instructionPointer) {
    int instruction = programMemory[instructionPointer];
    this.opCode = instruction % 100;
    parameters = readParameters(numberOfParameters(), instructionPointer, programMemory);
  }

  protected abstract int numberOfParameters();

  public int length() {
    return numberOfParameters() + 1;
  }

  private static List<Parameter> readParameters(int numberOfParameters, int instructionPointer,
      int[] programMemory) {
    var parameters = new ArrayList<Parameter>();
    for (int i = 1; i <= numberOfParameters; i++) {
      parameters.add(
          new Parameter(programMemory[instructionPointer + i],
              ParameterMode.fromInstruction(programMemory[instructionPointer], i))
      );
    }
    return parameters;
  }

  /**
   * Execute the instruction possibly altering the {@code programMemory} in the process
   *
   * @param programMemory the memory the instruction operates on
   * @return the new value for the instruction counter if the instruction modifies the instruction
   * counter, else Optional.empty()
   */
  abstract public Optional<Integer> execute(int[] programMemory);

  protected void writeToAddressInParameter(int parameterIndex, int valueToWrite,
      int[] programMemory) {
    programMemory[parameters.get(parameterIndex).uninterpretedValue()] = valueToWrite;
  }
}
