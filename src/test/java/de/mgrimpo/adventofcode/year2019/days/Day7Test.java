package de.mgrimpo.adventofcode.year2019.days;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.mgrimpo.adventofcode.year2019.days.Day7.AmplificationCircuit;
import org.junit.jupiter.api.Test;

public class Day7Test {
  @Test
  public void testThrusterPhaseInput(){
    //   Max thruster signal 43210 (from phase setting sequence 4,3,2,1,0):
    //    3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0
    var phaseSetting = new int[]{ 4,3,2,1,0 };
    var program = "3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0";
    var ampCircuit = new AmplificationCircuit( program);
    assertEquals(43210, ampCircuit.thrusterSignal(phaseSetting));

    //   Max thruster signal 54321 (from phase setting sequence 0,1,2,3,4):
    //  3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0
    phaseSetting = new int[] {0,1,2,3,4};
    program = "3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0";
    ampCircuit = new AmplificationCircuit(program);
    assertEquals(54321, ampCircuit.thrusterSignal(phaseSetting));

    //    Max thruster signal 65210 (from phase setting sequence 1,0,4,3,2):
    //    3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0
    phaseSetting = new int[] {1,0,4,3,2};
    program = "3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0";
    ampCircuit = new AmplificationCircuit(program);
    assertEquals(65210, ampCircuit.thrusterSignal(phaseSetting));

  }


}
