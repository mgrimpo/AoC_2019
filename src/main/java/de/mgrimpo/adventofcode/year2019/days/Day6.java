package de.mgrimpo.adventofcode.year2019.days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class Day6 implements Day {

  private final String orbiterOrbiteeMap;

  public Day6() throws IOException {
    orbiterOrbiteeMap = Files.readString(Path.of("input/day6_input.txt"));
  }

  @Override
  public String puzzleOneSolution() {
    return String.format("The checksum is %s", new OrbitMap(orbiterOrbiteeMap).checksum());
  }

  @Override
  public String puzzleTwoSolution() {
    return String.format("The minimum of orbital transfers is %s",
        new OrbitMap(orbiterOrbiteeMap).orbitalTransfers("YOU", "SAN"));
  }


  public static class OrbitMap {

    private final Map<String, String> orbitAdjacencyMap;
    private final Map<String, Set<String>> reverseAdjacencyMap;

    public OrbitMap(String mapString) {
      orbitAdjacencyMap = new HashMap<String, String>();
      reverseAdjacencyMap = new HashMap<String, Set<String>>();
      var entries = mapString.split("\n");
      for (var entry : entries) {
        addEntryToMap(entry);
        addEntryToReverseMap(entry);
      }
    }

    private void addEntryToReverseMap(String entry) {
      var bracePosition = entry.indexOf(")");
      var orbitee = entry.substring(0, bracePosition);
      var orbiter = entry.substring(bracePosition + 1);
      reverseAdjacencyMap.putIfAbsent(orbitee, new TreeSet<>());
      reverseAdjacencyMap.get(orbitee).add(orbiter);
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

    public Set<String> adjacentObjects(String object) {
      var adjacent = new HashSet<String>(reverseAdjacencyMap.getOrDefault(object, new HashSet<>()));
      if (!orbitee(object).equals("")) {
        adjacent.add(orbitee(object));
      }
      return adjacent;
    }

    /**
     * Computes the minimum number of orbital transfers between the orbit that {@code originObject}
     * is in and the orbit that {@code destinationObject} is in.
     * <p>
     * Here is an example where {@code originObject = "YOU"} and {@code destinationObject="SAN"}:
     *
     * <pre>
     *                           YOU
     *                          /
     *         G - H       J - K - L
     *        /           /
     * COM - B - C - D - E - F
     *                \
     *                 I - SAN
     * </pre>
     * <p>
     * In this example, YOU are in orbit around K, and SAN is in orbit around I. To move from K to
     * I, a minimum of 4 orbital transfers are required:
     * <pre>
     *     K to J
     *     J to E
     *     E to D
     *     D to I
     * </pre>
     */
    public int orbitalTransfers(String originObject, String destinationObject) {
      Queue<String> queue = new LinkedList<String>();
      var distanceFromOrigin = new HashMap<String, Integer>();
      var destination = orbitee(destinationObject);
      distanceFromOrigin.put(orbitee(originObject), 0);
      queue.add(orbitee(originObject));
      while (!queue.isEmpty()) {
        String currentObject = queue.poll();
        if (destination.equals(currentObject)) return distanceFromOrigin.get(currentObject);
        for (var neighbor : adjacentObjects(currentObject)) {
          if (distanceFromOrigin.containsKey(neighbor)) continue;
          distanceFromOrigin.put(neighbor, distanceFromOrigin.get(currentObject) + 1);
          queue.add(neighbor);
        }
      }
      throw new RuntimeException(String.format(
          "The orbit object %s is in is not reachable from the orbit that the object %s is in.",
          destinationObject, originObject));
    }
  }

}
