package de.mgrimpo.adventofcode.year2019.days;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import de.mgrimpo.adventofcode.year2019.days.Day6.OrbitMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Day6Test {

  private final String exampleMapString = "COM)B\n"
      + "B)C\n"
      + "C)D\n"
      + "D)E\n"
      + "E)F\n"
      + "B)G\n"
      + "G)H\n"
      + "D)I\n"
      + "E)J\n"
      + "J)K\n"
      + "K)L";

  private Day6.OrbitMap biggerExampleMap;

  @BeforeEach
  public void setUp(){
    biggerExampleMap = new OrbitMap(exampleMapString);
  }

  @Test
  public void testReadOrbitMap() {
    var mapString = "A)B\nB)C";
    var map = new Day6.OrbitMap(mapString);
    assertEquals("A",map.orbitee("B") );
    assertNotEquals("A", map.orbitee("C"));
    assertEquals("B", map.orbitee("C"));
  }


  @Test
  public void testIndirectOrbitCount() {
    assertEquals(3, biggerExampleMap.indirectOrbitCount("D"));
    assertEquals(7, biggerExampleMap.indirectOrbitCount("L"));
  }

  @Test
  public void testOrbitCountChecksum(){
    assertEquals(42, biggerExampleMap.checksum());
  }

}
