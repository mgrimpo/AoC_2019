package de.mgrimpo.adventofcode.year2019.intcodemachine;

import java.util.Optional;

public abstract class IntCodeInstruction {

  protected final int opCode;
  protected final ParameterMode firstParameterMode;
  protected final ParameterMode secondParameterMode;
  protected final ParameterMode thirdParameterMode;

  IntCodeInstruction(int[] programMemory, int instructionPointer) {
    int instruction = programMemory[instructionPointer];
    this.opCode = instruction % 100;
    firstParameterMode = ParameterMode.fromInt(findNthDigit(instruction, 2));
    secondParameterMode = ParameterMode.fromInt(findNthDigit(instruction, 3));
    thirdParameterMode = ParameterMode.fromInt(findNthDigit(instruction, 4));

  }
  protected  int getParameterValue(ParameterMode mode, int parameter, int[] programMemory){
    if (mode == ParameterMode.IMMEDIATE_MODE){
      return parameter;
    }
    else if (mode == ParameterMode.POSITION_MODE){
      return programMemory[parameter];
    }
    throw new RuntimeException("Unknown Parameter mode: " + mode.toString());
  }
  // digit position counted from right to left
  private static int findNthDigit(int number, int digitPosition) {
    return (int) (
        (number % Math.pow(10d, digitPosition + 1) - number % Math.pow(10, digitPosition))
            / Math.pow(10, digitPosition));
  }

  abstract public Optional<Integer> execute(int[] programMemory);
}
