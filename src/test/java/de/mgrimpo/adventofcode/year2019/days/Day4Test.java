package de.mgrimpo.adventofcode.year2019.days;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day4Test {

  @Test
  public void testSolveDay1ReturnsPositiveValue() throws InterruptedException {
    assertTrue(Day4.solveDay4(Day4.puzzleInput, Day4::containsNeighboringPair) >= 0);
  }

  @Test
  public void testContainsIdenticalNeighbors() {
    assertTrue(Day4.containsNeighboringPair(112345));
    assertFalse(Day4.containsNeighboringPair(012345));
  }

  @Test
  public void testFindGreaterPredecessorDigit() {
    assertEquals(0, Day4.findGreaterPredecessorDigit(213456));
    assertEquals(-1, Day4.findGreaterPredecessorDigit(123456));
  }

  @Test
  public void testNextPossibleCandidate() {
    assertEquals(222222, Day4.nextPossibleCandidate(213456, 0));
    assertEquals(222555, Day4.nextPossibleCandidate(222546, 3));
  }

  @Test
  public void containsIsolatedPair() {
    // 12233 meets these criteria because the digits never decrease and all repeated digits are exactly two digits long.
    assertTrue(Day4.containsIsolatedPair(12233));
    // 123444 no longer meets the criteria (the repeated 44 is part of a larger group of 444).
    assertFalse(Day4.containsIsolatedPair(123444));
    // 111122 meets the criteria (even though 1 is repeated more than twice, it still contains a double 22).
    assertTrue(Day4.containsIsolatedPair(111122));
  }
}
