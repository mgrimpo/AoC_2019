package de.mgrimpo.adventofcode.year2019.intcodemachine;

public enum ParameterMode {
  POSITION_MODE(0),
  IMMEDIATE_MODE(1);

  private final int modeCode;

  ParameterMode(int modeCode) {
    this.modeCode = modeCode;
  }

  public int getModeCode() {
    return modeCode;
  }

  public static ParameterMode fromInt(int modeCode) {
    if(modeCode == POSITION_MODE.getModeCode()){
      return POSITION_MODE;
    }
    else if (modeCode == IMMEDIATE_MODE.getModeCode()){
      return IMMEDIATE_MODE;
    }
    else {
      throw new RuntimeException("Unknown mode code");
    }
  }
}
