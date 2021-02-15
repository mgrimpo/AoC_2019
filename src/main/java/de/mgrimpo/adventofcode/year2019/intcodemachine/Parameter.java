package de.mgrimpo.adventofcode.year2019.intcodemachine;

public class Parameter {

  private final int parameterValue;
  private final ParameterMode parameterMode;

  public Parameter(int parameterValue,
      ParameterMode parameterMode) {
    this.parameterValue = parameterValue;
    this.parameterMode = parameterMode;
  }
  public int getUninterpretedParameterValue(){
    return parameterValue;
  }
  public  int interpretParameterValue(int[] programMemory){
    if (parameterMode == ParameterMode.IMMEDIATE_MODE){
      return parameterValue;
    }
    else if (parameterMode == ParameterMode.POSITION_MODE){
      return programMemory[parameterValue];
    }
    throw new RuntimeException("Unknown Parameter mode: " + parameterMode.toString());
  }
}
