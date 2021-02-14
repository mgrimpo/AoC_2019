package de.mgrimpo.adventofcode.year2019.days;

import de.mgrimpo.adventofcode.year2019.intcodemachine.IntCodeProgram;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Day5Test {

//  First, you'll need to add two new instructions:
//
//  Opcode 3 takes a single integer as input and saves it to the position given by its only parameter.
//  For example, the instruction 3,50 would take an input value and store it at address 50.

//  Opcode 4 outputs the value of its only parameter. For example, the instruction 4,50 would output
//  the value at address 50.

  @Test public void testExampleProgram(){
    var exampleProgramString = "1002,4,3,4,33";
    var intCodeProgram = IntCodeProgram.fromString(exampleProgramString);
    assertEquals(99, intCodeProgram.execute().valueAt(4));
  }


}
