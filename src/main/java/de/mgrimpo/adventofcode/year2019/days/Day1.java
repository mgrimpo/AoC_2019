package de.mgrimpo.adventofcode.year2019.days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Day1 {

  public static void main(String[] args) throws IOException {
    Path inputFile = Paths.get("input/day1_input.txt");
    List<Long> moduleMasses = readModuleMasses(inputFile);
    long puzzleOneResult = netFuelRequiredForModules(moduleMasses);
    System.out.printf("The net fuel requirements for all modules are: %s\n",  puzzleOneResult);
    long puzzleTwoResult = totalFuelRequiredForModules(moduleMasses);
    System.out.printf("The overall fuel requirements, including fuel for fuel mass, are: %s\n",  puzzleTwoResult);
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
}
