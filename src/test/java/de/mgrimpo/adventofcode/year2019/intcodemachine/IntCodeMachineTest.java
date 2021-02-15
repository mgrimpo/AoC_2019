package de.mgrimpo.adventofcode.year2019.intcodemachine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

// TODO: move other related tests into this class or new test in this package
public class IntCodeMachineTest {

  //  Opcode 5 is jump-if-true: if the first parameter is non-zero, it sets the instruction pointer to
  //  the value from the second parameter. Otherwise, it does nothing.
  @Test
  public void testJumpIfTrueInstruction() {
    var exampleProgramWithJump = new int[]{1105, 1, 6, -1, 0, 0, 99};
    var jumpIfTrueInstruction = new JumpIfTrueInstruction(exampleProgramWithJump, 0);
    assertEquals(6, jumpIfTrueInstruction.execute(exampleProgramWithJump).get());

    var exampleProgramWithOutJump = new int[]{1105, 0, 6, 1, 0, 0, 0, 99};
    jumpIfTrueInstruction = new JumpIfTrueInstruction(exampleProgramWithOutJump, 0);
    assertTrue(jumpIfTrueInstruction.execute(exampleProgramWithOutJump).isEmpty());

    // the positional second parameter refers to the value 5 at index 3
    var programWithPositionalParams = new int[]{105, 1, 3, 5, 99,};
    jumpIfTrueInstruction = new JumpIfTrueInstruction(programWithPositionalParams, 0);
    assertEquals(5, jumpIfTrueInstruction.execute(programWithPositionalParams).get());
  }

  //  Opcode 6 is jump-if-false: if the first parameter is zero, it sets the instruction pointer to the value from the second parameter. Otherwise, it does nothing.
  @Test
  void testJumpIfFalseInstruction() {
    var program = new int[]{1106, 0, 3, 99};
    var jumpIfFalseInstruction = new JumpIfFalseInstruction(program, 0);
    assertEquals(3, jumpIfFalseInstruction.execute(program).get());

    var programWithoutJump = new int[]{1106, 1, 3, 99};
    jumpIfFalseInstruction = new JumpIfFalseInstruction(programWithoutJump, 0);
    assertTrue(jumpIfFalseInstruction.execute(programWithoutJump).isEmpty());

    var positionalParameterProgram = new int[]{1006, 3, 4, 0, 99};
    jumpIfFalseInstruction = new JumpIfFalseInstruction(positionalParameterProgram, 0);
    assertEquals(4, jumpIfFalseInstruction.execute(positionalParameterProgram).get());
  }

  //  Opcode 7 is less than: if the first parameter is less than the second parameter, it stores 1
  //  in the position given by the third parameter. Otherwise, it stores 0.
  @Test
  public void testLessThanInstruction() {
    // Test 1 < 2 is true
    var program = new int[]{1107, 1, 2, 4, 0};
    new LessThanInstruction(program, 0)
        .execute(program);
    assertEquals(1, program[4]);

    // Test 2 < 1 is false
    program = new int[]{1107, 2, 1, 4, 0};
    new LessThanInstruction(program, 0)
        .execute(program);
    assertEquals(0, program[4]);

    // Test 2 < 2 (positional mode) is false
    program = new int[]{107, 2, 1, 4, 0};
    new LessThanInstruction(program, 0)
        .execute(program);
    assertEquals(0, program[4]);
  }

  //  Opcode 8 is equals: if the first parameter is equal to the second parameter, it stores 1 in
  //  the position given by the third parameter. Otherwise, it stores 0.
  @Test
  public void testEqualsInstructions() {
    // Test 1 == 1 is true
    var program = new int[]{1108, 1, 1, 4, 0};
    new EqualsInstruction(program, 0)
        .execute(program);
    assertEquals(1, program[4]);

    // Test 1 == 2 is false
    program = new int[]{1108, 1, 2, 4, 0};
    new EqualsInstruction(program, 0)
        .execute(program);
    assertEquals(0, program[4]);

    // Test 1 == 1 is true (positional parameter)
    program = new int[]{1008, 2, 1, 4, 0};
    new EqualsInstruction(program, 0)
        .execute(program);
    assertEquals(1, program[4]);
  }


}
