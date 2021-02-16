package de.mgrimpo.adventofcode.year2019.intcodemachine;

public class Parameter {

  private final int value;
  private final ParameterMode mode;

  public Parameter(int value,
      ParameterMode mode) {
    this.value = value;
    this.mode = mode;
  }

  public int uninterpretedValue() {
    return value;
  }

  public int interpret(int[] programMemory) {
    if (mode == ParameterMode.IMMEDIATE_MODE) {
      return value;
    } else if (mode == ParameterMode.POSITION_MODE) {
      return programMemory[value];
    }
    throw new RuntimeException("Unknown Parameter mode: " + mode.toString());
  }
}
