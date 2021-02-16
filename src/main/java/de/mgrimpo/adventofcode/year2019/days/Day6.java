package de.mgrimpo.adventofcode.year2019.days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Day6 implements Day {

  private final String mapDescription;

  public Day6() throws IOException {
    mapDescription = Files.readString(Path.of("input/day6_input.txt"));
  }

  @Override
  public String puzzleOneSolution() {
    return String.format("The checksum is %s", new OrbitMap(mapDescription).checksum());
  }


  public static class OrbitMap {

    private final Map<String, String> orbitAdjacencyMap;

    public OrbitMap(String mapString) {
      orbitAdjacencyMap = new HashMap<String, String>();
      var entries = mapString.split("\n");
      for (var entry : entries) {
        addEntryToMap(entry);
      }
    }

    private void addEntryToMap(String entry) {
      var bracePosition = entry.indexOf(")");
      var orbitee = entry.substring(0, bracePosition);
      var orbiter = entry.substring(bracePosition + 1);
      orbitAdjacencyMap.put(orbiter, orbitee);
    }

    public String orbitee(String orbiter) {
      return orbitAdjacencyMap
          .getOrDefault(orbiter, "");
    }

    public int indirectOrbitCount(String orbiter) {
      var count = 0;
      for (var currentOrbiter = orbiter; !currentOrbiter.equals("COM");
          currentOrbiter = orbitAdjacencyMap.get(currentOrbiter)) {
        count++;
      }
      return count;
    }

    public int checksum() {
      return orbitAdjacencyMap.keySet().stream()
          .mapToInt(orbiter -> indirectOrbitCount(orbiter))
          .sum();
    }
  }

}
