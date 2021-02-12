package de.mgrimpo.adventofcode.year2019;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day4Test {

  @Test public void testSolveDay1ReturnsPositiveValue() throws InterruptedException {
    assertTrue(Day4.solveDay1(Day4.puzzleInputDay1) >= 0);
  }

  @Test public void testContainsIdenticalNeighbors() {
    assertTrue(Day4.containsIdenticalNeighbors(112345));
    assertFalse(Day4.containsIdenticalNeighbors(012345));
  }

  @Test public void testFindGreaterPredecessorDigit(){
    assertEquals(0, Day4.findGreaterPredecessorDigit(213456));
    assertEquals(-1, Day4.findGreaterPredecessorDigit(123456));
  }

  @Test public void testNextPossibleCandidate(){
    assertEquals(222222, Day4.nextPossibleCandidate(213456, 0));
    assertEquals(222555, Day4.nextPossibleCandidate(222546, 3));
  }


}