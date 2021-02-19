package de.mgrimpo.adventofcode.year2019.days;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.mgrimpo.adventofcode.year2019.intcodemachine.IntCodeProgram;
import org.junit.jupiter.api.Test;

public class Day5Test {

  @Test public void testExampleProgram(){
    var exampleProgramString = "1002,4,3,4,33";
    var intCodeProgram = IntCodeProgram.fromString(exampleProgramString);
    intCodeProgram.execute();
    assertEquals(99, intCodeProgram.valueAt(4));
  }


}
