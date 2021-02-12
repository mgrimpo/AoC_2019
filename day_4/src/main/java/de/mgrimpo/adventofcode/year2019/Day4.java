package de.mgrimpo.adventofcode.year2019;

public class Day4 {

  static int[] puzzleInputDay1 = new int[] {153517, 630395};

  public static void main(String[] args) {
    int solutionDay1 = solveDay1(puzzleInputDay1);
    System.out.printf(
        "There are %d possible passwords for the range of %d-%d\n",
        solutionDay1, puzzleInputDay1[0], puzzleInputDay1[1]);
  }

  static int solveDay1(int[] puzzleInputDay1) {
    int numberOfPossiblePasswords = 0;
    int currentCandidate = puzzleInputDay1[0];
    while (currentCandidate <= puzzleInputDay1[1]) {
      int greaterPredecessorDigitPosition = findGreaterPredecessorDigit(currentCandidate);
      if (greaterPredecessorDigitPosition > -1) {
        currentCandidate = nextPossibleCandidate(currentCandidate, greaterPredecessorDigitPosition);
        continue;
      }
      if (containsIdenticalNeighbors(currentCandidate)) {
        numberOfPossiblePasswords++;
      }
      currentCandidate++;
    }
    return numberOfPossiblePasswords;
  }

   static int nextPossibleCandidate(int currentCandidate, int greaterPredecessorPosition) {
    String candidateString = Integer.toString(currentCandidate);
    StringBuilder newCandidate = new StringBuilder(candidateString.substring(0, greaterPredecessorPosition + 1));
    for (int i=0; i < 5 - greaterPredecessorPosition; i++) {
      newCandidate.append(candidateString.charAt(greaterPredecessorPosition));
    }
    return Integer.parseInt(newCandidate.toString());
  }

  /**
   * Finds the position of a digit with a lower numerical value than its successor digit and returns
   * that position. If no such digit is found returns -1
   *
   * @param currentCandidate the current candidate for a password, represented as a String of 6
   *     digits
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

  public static boolean containsIdenticalNeighbors(Integer passwordCandidate) {
    String passwordCandidateString = Integer.toString(passwordCandidate);
    for (int i = 0; i < passwordCandidateString.length() - 1; i++) {
      if (passwordCandidateString.charAt(i) == passwordCandidateString.charAt(i + 1)) {
        return true;
      }
    }
    return false;
  }
}
