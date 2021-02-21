package de.mgrimpo.adventofcode.year2019.permutations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PermutationUtils {



  public static void heapsAlgorithm(List<int[]> permutations, int[] permutation, int k) {
    if (k == 1) {
      permutations.add(permutation.clone());
      return;
    }
    heapsAlgorithm(permutations, permutation, k - 1);
    for (int i = 0; i < k - 1; i++) {
      if (k % 2 == 0) {
        swap(permutation, k - 1, i);
      } else {
        swap(permutation, k - 1, 0);
      }
      heapsAlgorithm(permutations, permutation, k - 1);
    }
  }

  public static List<int[]> heapsAlgorithmIterative(int[] permutation) {
    var stackLoopVariable = new int[permutation.length];
    var result = new ArrayList<int[]>();
    Arrays.fill(stackLoopVariable, 0);

    result.add(permutation.clone());

    var k = 0;
    while (k < permutation.length) {
      if (stackLoopVariable[k] < k) {
        if (k % 2 == 0) {
          swap(permutation, k, 0);
        } else {
          swap(permutation, k, stackLoopVariable[k]);
        }

        result.add(permutation.clone());
        stackLoopVariable[k] += 1;
        k = 1;
      } else {
        stackLoopVariable[k] = 0;
        k += 1;
      }
    }
    return result;
  }

  public static List<int[]> naiveSetBasedPermutationGenerator(Set permutationElements) {
    List<Integer[]> permutations = new ArrayList<>();
    var permutation = new Integer[5];
    setBasedPermutationHelper(permutation, permutationElements, permutations);
    return boxedIntegerArrayListToPrimitiveIntArrayList(permutations);
  }

  //       'unbox' List elements from Integer[] to int[]
  static List<int[]> boxedIntegerArrayListToPrimitiveIntArrayList(
      List<Integer[]> permutations) {
    return permutations.stream()
        .map(PermutationUtils::boxedIntArrayToPrimitive)
        .collect(Collectors.toList());
  }

  public static int[] boxedIntArrayToPrimitive(Integer[] boxedArray) {
    return Arrays.stream(boxedArray).mapToInt(x -> x).toArray();
  }

  private static <T> void setBasedPermutationHelper(T[] permutation, Set<T> unchosen,
      List<T[]> permutations) {
    if (unchosen.size() == 0) permutations.add(permutation.clone());
    for (var element : unchosen) {
      permutation[unchosen.size() - 1] = element;
      var updatedUnchosen = new HashSet<>(unchosen);
      updatedUnchosen.remove(element);
      setBasedPermutationHelper(permutation, updatedUnchosen, permutations);
    }
  }

  static void swap(int[] array, int indexA, int indexB) {
    var temp = array[indexA];
    array[indexA] = array[indexB];
    array[indexB] = temp;
  }
}
