package de.mgrimpo.adventofcode.year2019;

import de.mgrimpo.adventofcode.year2019.days.Day;
import java.util.HashSet;
import java.util.Set;
import org.reflections.Reflections;
import org.reflections.ReflectionsException;
import org.slf4j.LoggerFactory;

public class DayExecutor {

  private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DayExecutor.class);

  public static void main(String[] args) {
    for (var dayClass : findDayClasses()) {
      try {
        Day day = dayClass.getDeclaredConstructor().newInstance();
        printSolutions(day);
      } catch (ReflectiveOperationException e) {
        logger.error("%s could not be instantiated, no arg constructor seems to be missing",
            dayClass.getName());
        e.printStackTrace();
      }
    }
  }

  private static void printSolutions(Day day) {
    var dayNumber = getDayNumber(day.getClass());
    System.out.println();
    System.out.println("#".repeat(12) + "  Advent of Code 2019  " + "#".repeat(12));
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
