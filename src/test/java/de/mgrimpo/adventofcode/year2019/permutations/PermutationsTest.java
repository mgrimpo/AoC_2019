package de.mgrimpo.adventofcode.year2019.permutations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.junit.jupiter.api.Test;

public class PermutationsTest {

  @Test
  public void testPermutator() {
    var permutator = new Permutator<Integer>(new Integer[]{0, 1, 2, 3, 4});
    var permutationsBoxed = StreamSupport.stream(permutator.spliterator(), false)
        .collect(Collectors.toList());
    var permutations = permutationsBoxed.stream()
        .map(perm -> Arrays.stream(perm).mapToInt(x -> x).toArray())
        .collect(Collectors.toList());
    int uniquePermCount = uniqueArrayCount(permutations);
    assertEquals(120, uniquePermCount);
    assertTrue(
        permutations.stream().anyMatch(perm -> Arrays.equals(perm, new int[]{0, 1, 2, 3, 4})));
  }

  @Test
  public void testHeapsAlgorithm() {
    List<int[]> permutations = new ArrayList<int[]>();
    PermutationUtils.heapsAlgorithm(permutations, new int[]{0, 1, 2, 3, 4}, 5);
    int uniquePermCount = uniqueArrayCount(permutations);
    assertEquals(120, uniquePermCount);
    assertTrue(
        permutations.stream().anyMatch(perm -> Arrays.equals(perm, new int[]{0, 1, 2, 3, 4})));
  }

  @Test
  public void testHeapIterative() {
    var permutations = PermutationUtils.heapsAlgorithmIterative(new int[]{0, 1, 2, 3, 4});
    int uniquePermCount = uniqueArrayCount(permutations);
    assertEquals(120, uniquePermCount);
    assertTrue(
        permutations.stream().anyMatch(perm -> Arrays.equals(perm, new int[]{0, 1, 2, 3, 4})));
  }

  private int uniqueArrayCount(java.util.List<int[]> permutations) {
    var uniquePerms = permutations.stream()
        .collect(HashSet::new,
            (HashSet<int[]> set, int[] current) -> {
              if (set.stream().noneMatch(perm -> Arrays.equals(current, perm))) {
                set.add(current);
              }
            },
            (setA, setB) -> setA.addAll(setB));
    var uniquePermCount = uniquePerms.size();
    return uniquePermCount;
  }

  @Test
  public void testNaivePermutationGenerator() {
    var permutations = PermutationUtils.naiveSetBasedPermutationGenerator(
        Set.of(0, 1, 2, 3, 4));
    int uniquePermCount = uniqueArrayCount(permutations);
    assertEquals(120, uniquePermCount);
    assertTrue(
        permutations.stream().anyMatch(perm -> Arrays.equals(perm, new int[]{0, 1, 2, 3, 4})));
  }

}
