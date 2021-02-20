package de.mgrimpo.adventofcode.year2019.days;

import de.mgrimpo.adventofcode.year2019.intcodemachine.IntCodeProgram;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
      return possibleSettings().stream()
          .mapToInt(setting -> thrusterSignal(setting))
          .max()
          .getAsInt();
    }

    private List<int[]> possibleSettings() {
      var unchosen = Set.of(0, 1, 2, 3, 4);
      var permutations = new ArrayList<Integer[]>();
      var permutation = new Integer[5];
      permutationsHelper(permutation, unchosen, permutations);
      // 'unbox' from Integer[] to int[]
      return permutations.stream()
          .map(a -> Arrays.stream(a)
              .mapToInt(x -> x)
              .toArray())
          .collect(Collectors.toList());
    }

    private <T> void permutationsHelper(T[] permutation, Set<T> unchosen,
        ArrayList<T[]> permutations) {
      if (unchosen.size() == 0) permutations.add(permutation.clone());
      for (var element : unchosen) {
        permutation[unchosen.size() - 1] = element;
        var updatedUnchosen = new HashSet<>(unchosen);
        updatedUnchosen.remove(element);
        permutationsHelper(permutation, updatedUnchosen, permutations);
      }
    }
  }
}
