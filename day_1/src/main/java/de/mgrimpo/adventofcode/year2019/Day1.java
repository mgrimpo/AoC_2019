package de.mgrimpo.adventofcode.year2019;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Day1 {

  public static void main(String[] args) throws IOException {
    Path inputFile = Paths.get("input.txt");
    List<Long> moduleMasses = readModuleMasses(inputFile);
    long result = calculateModuleFuelRequirements(moduleMasses);
    System.out.println(result);
  }


  private static long calculateFuelForFuelMass(long fuelMass){
    long fuelRequirements = fuelRequiredForMass(fuelMass);
    if (fuelRequirements <= 0) {
      return 0;
    } else {
      return  fuelRequirements + calculateFuelForFuelMass(fuelRequirements);
    }
  }

  private static Long calculateModuleFuelRequirements(List<Long> moduleMasses) {
    return moduleMasses
        .stream()
        .map(Day1::fuelRequiredForMass)
        .map(fuelMass -> fuelMass + calculateFuelForFuelMass(fuelMass))
        .reduce(Long::sum)
        .get();
  }

  private static long fuelRequiredForMass(long mass) {
    return (mass /3 ) - 2;
  }

  private static List<Long> readModuleMasses(Path statsPath) throws IOException {
    return Files.lines(statsPath)
        .map(Long::parseLong)
        .collect(Collectors.toList());
  }
}
