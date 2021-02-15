package de.mgrimpo.adventofcode.year2019.intcodemachine;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import org.junit.jupiter.api.Test;

public class IntCodeMachineTest {

  @Test
  public void testRead8CompareWith8ReturnsTrue() throws IOException {
    var exampleString1 = "3,9,8,9,10,9,4,9,99,-1,8";
    var program = IntCodeProgram.fromString(exampleString1);
    var programOutput = program.executeWithPresetInput("8");
    assertTrue(programOutput.endsWith("1\n"));
  }

  @Test
  public void testRead7CompareWith8ReturnsFalse() {
    var exampleString1 = "3,9,8,9,10,9,4,9,99,-1,8";
    var program = IntCodeProgram.fromString(exampleString1);
    var programOutput = program.executeWithPresetInput("7");
    assertTrue(programOutput.endsWith("0\n"));
  }

  //  3,9,7,9,10,9,4,9,99,-1,8 - Using position mode, consider whether the input is less than 8; output 1 (if it is) or 0 (if it is not).
  @Test
  public void testReadInputLess8Positional() {
    var program = IntCodeProgram.fromString("3,9,7,9,10,9,4,9,99,-1,8");
    assertTrue(program.executeWithPresetInput("7").endsWith("1\n"));
  }

  //  3,3,1108,-1,8,3,4,3,99 - Using immediate mode, consider whether the input is equal to 8; output 1 (if it is) or 0 (if it is not).
  @Test
  public void testReadInputEqual8Immediate() {
    var program = IntCodeProgram.fromString("3,3,1108,-1,8,3,4,3,99");
    assertTrue(program.executeWithPresetInput("8").endsWith("1\n"));
  }

  //  3,3,1107,-1,8,3,4,3,99 - Using immediate mode, consider whether the input is less than 8; output 1 (if it is) or 0 (if it is not).
  @Test
  public void testReadInputLess8Immediate() {
    var program = IntCodeProgram.fromString("3,3,1107,-1,8,3,4,3,99");
    assertTrue(program.executeWithPresetInput("9").endsWith("0\n"));
    assertTrue(program.executeWithPresetInput("7").endsWith("1\n"));
  }

  //  Here are some jump tests that take an input, then output 0 if the input was zero or 1 if the input was non-zero:
  //      3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9 (using position mode)
  @Test
  public void testJumpPositional() {
    var program = IntCodeProgram.fromString("3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9");
    assertTrue(program.executeWithPresetInput("0").endsWith("0\n"));
    assertTrue(program.executeWithPresetInput("3").endsWith("1\n"));
  }
  //  Here are some jump tests that take an input, then output 0 if the input was zero or 1 if the input was non-zero:
  //      3,3,1105,-1,9,1101,0,0,12,4,12,99,1 (using immediate mode)

}
