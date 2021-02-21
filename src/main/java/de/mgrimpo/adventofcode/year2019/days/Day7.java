package de.mgrimpo.adventofcode.year2019.days;

import de.mgrimpo.adventofcode.year2019.intcodemachine.IntCodeProgram;
import de.mgrimpo.adventofcode.year2019.permutations.PermutationUtils;
import de.mgrimpo.adventofcode.year2019.permutations.Permutator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.StreamSupport;

public class Day7 implements Day {

  private final String program;

  public Day7() throws IOException {
    program = Files.readString(Path.of("input/day7_input.txt"));
  }

  @Override
  public String puzzleOneSolution() {
    var circuit = new AmplificationCircuit(program);
    return String.format("The maximum thruster signal given the input program is %s",
        circuit.maxThrusterSignal());
  }

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

    public int maxThrusterSignal() {
      var permutator = new Permutator<Integer>(new Integer[]{0, 1, 2, 3, 4});
      return StreamSupport.stream(permutator.spliterator(), false)
          .map(PermutationUtils::boxedIntArrayToPrimitive)
          .mapToInt(setting -> thrusterSignal(setting))
          .max()
          .getAsInt();
    }


  }
}

