package de.mgrimpo.adventofcode.year2019.days;

import de.mgrimpo.adventofcode.year2019.intcodemachine.IntCodeProgram;

public class Day7 implements Day {

  public static class AmplificationCircuit {

    private final String program;

    public AmplificationCircuit(String program) {
      this.program = program;
    }

    public Integer thrusterSignal(int[] phaseSetting) {
      var amplifierInput = 0;
      for (int i = 0; i < 5; i++) {
        amplifierInput = runAmplifier(phaseSetting[i], amplifierInput);
      }
      return amplifierInput;
    }

    private int runAmplifier(int phaseSetting, int amplifierInput) {
      var amplifier = IntCodeProgram.fromString(program);
      var programInput = String.format("%s\n%s\n", phaseSetting, amplifierInput);
      var output = amplifier.executeWithPresetInput(programInput);
      return Integer.parseInt(output.trim());
    }
  }
}
