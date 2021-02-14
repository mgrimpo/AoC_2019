package de.mgrimpo.adventofcode.year2019;

import de.mgrimpo.adventofcode.year2019.days.Day;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.reflections.Reflections;
import org.reflections.ReflectionsException;
import org.slf4j.LoggerFactory;

public class DayExecutor {

  private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DayExecutor.class);

  public static void main(String[] args) {
    final int[] classesToLoad = new int[]{2, 5};
    executeAndPrintSolutions(classesToLoad);
  }

  private static void executeAndPrintSolutions(int[] classesToLoad) {
    printAdventOfCode2019Banner();
    for (var dayClass : loadDayClasses(classesToLoad)) {
      try {
        Day day = dayClass.getDeclaredConstructor().newInstance();
        printSolutions(day);
      } catch (ReflectiveOperationException e) {
        logger.error("%s could not be instantiated",
            dayClass.getName());
        e.printStackTrace();
      }
    }
  }

  private static List<Class<? extends Day>> loadDayClasses(int[] classesToLoad) {
    Predicate<Integer> isInClassesToLoad = i -> Arrays.stream(classesToLoad).anyMatch(i::equals);
    return findDayClasses().stream()
        .filter(clazz ->  isInClassesToLoad.test(getDayNumber(clazz)))
        .sorted(Comparator.comparingInt(DayExecutor::getDayNumber))
        .collect(Collectors.toList());
  }

  private static void printAdventOfCode2019Banner() {
    System.out.println();
    System.out.println("#".repeat(12) + "  Advent of Code 2019  " + "#".repeat(12));
  }

  private static void printSolutions(Day day) {
    var dayNumber = getDayNumber(day.getClass());
    System.out.printf("Day %s, Puzzle 1:\n%s\n", dayNumber, day.puzzleOneSolution());
    System.out.printf("Day %s, Puzzle 2:\n%s\n", dayNumber, day.puzzleTwoSolution());
    System.out.println();
  }

  private static Set<Class<? extends Day>> findDayClasses() {
    try {
      Reflections reflections = new Reflections("de.mgrimpo.adventofcode.year2019.days");
      return reflections.getSubTypesOf(Day.class);
    } catch (ReflectionsException e) {
      logger.error("The org.reflections SubTypeScanner encountered a problem.");
      logger.error(
          "This can happen, when no matching subtypes are found, cf. https://github.com/ronmamo/reflections/issues/273");
      e.printStackTrace();
      return new HashSet<>();
    }
  }

  private static int getDayNumber(Class<? extends Day> dayClass) {
    if (!dayClass.getSimpleName().startsWith("Day")) {
      throw new IllegalArgumentException(
          "The passed class is not named in the format 'Day[number]'");
    }
    return Integer.parseInt(
        dayClass.getSimpleName().substring(3)
    );
  }

}
