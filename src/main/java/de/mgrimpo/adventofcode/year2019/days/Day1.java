package de.mgrimpo.adventofcode.year2019.days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Day1 implements Day{

  private final List<Long> moduleMasses;

  public Day1() throws IOException {
    Path inputFile = Paths.get("input/day1_input.txt");
     moduleMasses = readModuleMasses(inputFile);
  }
  public static void main(String[] args) throws IOException {
  }

  private static long fuelForFuelMass(long fuelMass) {
    long fuelRequirements = netFuelForMass(fuelMass);
    if (fuelRequirements <= 0) {
      return 0;
    } else {
      return fuelRequirements + fuelForFuelMass(fuelRequirements);
    }
  }

  private static Long netFuelRequiredForModules(List<Long> moduleMasses) {
    return moduleMasses.stream()
        .mapToLong(Day1::netFuelForMass)
        .sum();
  }

  private static Long totalFuelRequiredForModules(List<Long> moduleMasses) {
    return moduleMasses.stream()
        .mapToLong(Day1::totalFuelForMass)
        .sum();
  }

  private static long netFuelForMass(long mass) {
    return (mass / 3) - 2;
  }

  private static long totalFuelForMass(long mass) {
    return netFuelForMass(mass) + fuelForFuelMass(netFuelForMass(mass));
  }

  private static List<Long> readModuleMasses(Path statsPath) throws IOException {
    return Files.lines(statsPath).map(Long::parseLong).collect(Collectors.toList());
  }

  @Override
  public String puzzleOneSolution() {
    long puzzleOneResult = netFuelRequiredForModules(moduleMasses);
    return String.format("The net fuel requirements for all modules are: %s",  puzzleOneResult);
  }

  @Override
  public String puzzleTwoSolution() {
    long puzzleTwoResult = totalFuelRequiredForModules(moduleMasses);
    return String.format("The overall fuel requirements, including fuel for fuel mass, are: %s",  puzzleTwoResult);
  }
}
