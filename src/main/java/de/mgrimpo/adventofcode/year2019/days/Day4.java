package de.mgrimpo.adventofcode.year2019.days;

import java.util.function.Predicate;

public class Day4 {

  static int[] puzzleInput = new int[]{153517, 630395};

  public static void main(String[] args) {
    int solutionPuzzle1 = solveDay4(puzzleInput, Day4::containsNeighboringPair);
    System.out.printf(
        "Puzzle 1: There are %d possible passwords for the range of %d-%d\n",
        solutionPuzzle1, puzzleInput[0], puzzleInput[1]);
    int solutionPuzzle2 = solveDay4(puzzleInput, Day4::containsIsolatedPair);
    System.out.printf(
        "Puzzle 2: There are %d possible passwords for the range of %d-%d\n",
        solutionPuzzle2, puzzleInput[0], puzzleInput[1]);
  }

  static int solveDay4(int[] puzzleInputDay1, Predicate<Integer> neighborRule) {
    int numberOfPossiblePasswords = 0;
    int currentCandidate = puzzleInputDay1[0];
    while (currentCandidate <= puzzleInputDay1[1]) {
      int greaterPredecessorDigitPosition = findGreaterPredecessorDigit(currentCandidate);
      if (greaterPredecessorDigitPosition > -1) {
        currentCandidate = nextPossibleCandidate(currentCandidate, greaterPredecessorDigitPosition);
        continue;
      }
      if (neighborRule.test(currentCandidate)) {
        numberOfPossiblePasswords++;
      }
      currentCandidate++;
    }
    return numberOfPossiblePasswords;
  }

  static int nextPossibleCandidate(int currentCandidate, int greaterPredecessorPosition) {
    String candidateString = Integer.toString(currentCandidate);
    StringBuilder newCandidate =
        new StringBuilder(candidateString.substring(0, greaterPredecessorPosition + 1));
    for (int i = 0; i < 5 - greaterPredecessorPosition; i++) {
      newCandidate.append(candidateString.charAt(greaterPredecessorPosition));
    }
    return Integer.parseInt(newCandidate.toString());
  }

  /**
   * Finds the position of a digit with a lower numerical value than its successor digit and returns
   * that position. If no such digit is found returns -1
   *
   * @param currentCandidate the current candidate for a password, represented as a String of 6
   *                         digits
   * @return the position of the digit that is greater than its successor digit
   */
  static int findGreaterPredecessorDigit(int currentCandidate) {
    String candidateString = Integer.toString(currentCandidate);
    for (int i = 0; i < 5; i++) {
      if (candidateString.charAt(i) > candidateString.charAt(i + 1)) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Checks whether {@code passwordCandidate} contains two adjacent matching digits.
   */
  static boolean containsNeighboringPair(Integer passwordCandidate) {
    String passwordCandidateString = Integer.toString(passwordCandidate);
    for (int i = 0; i < passwordCandidateString.length() - 1; i++) {
      if (passwordCandidateString.charAt(i) == passwordCandidateString.charAt(i + 1)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks whether {@code passwordCandidate} has two adjacent matching digits, where the two adjacent
   * matching digits are not part of a larger group of matching digits.
   *
   * @param passwordCandidate
   */
  static boolean containsIsolatedPair(int passwordCandidate) {
    String candidateString = Integer.toString(passwordCandidate);
    return isolatedPairHelper(candidateString);
  }

  private static boolean isolatedPairHelper(String passwordCandidate) {
    if (passwordCandidate.isEmpty()) return false;

    var sameCharacterStreakLength =
        passwordCandidate.chars()
            .takeWhile(c -> c == passwordCandidate.charAt(0))
            .count();
    if (sameCharacterStreakLength == 2) {
      return true;
    }
    return isolatedPairHelper(passwordCandidate.substring((int) sameCharacterStreakLength));
  }
}
