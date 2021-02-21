package de.mgrimpo.adventofcode.year2019.permutations;

import java.util.Arrays;
import java.util.Iterator;


public class Permutator<T> implements Iterable<T[]> {

  private final T[] permutation;

  public Permutator(T[] permutation) {
    this.permutation = permutation;
  }

  @Override
  public Iterator<T[]> iterator() {
    return new PermutationIterator<>(permutation.clone());
  }

  private class PermutationIterator<T> implements Iterator<T[]> {

    private final T[] currentPermutation;
    private final int[] stackLoopVariable;
    private int k;
    private boolean firstCall;

    protected PermutationIterator(T[] initialPermutation) {
      this.currentPermutation = initialPermutation;
      this.stackLoopVariable = new int[initialPermutation.length];
      this.k = 0;
      this.firstCall = true;
      Arrays.fill(stackLoopVariable, 0);
    }

    private void swap(T[] array, int indexA, int indexB) {
      var temp = array[indexA];
      array[indexA] = array[indexB];
      array[indexB] = temp;
    }

    @Override
    public boolean hasNext() {
      return k < currentPermutation.length;
    }

    @Override
    public T[] next() {
      if (firstCall) {
        firstCall = false;
        return currentPermutation.clone();
      }
      while (hasNext()) {
        if (stackLoopVariable[k] < k) {
          if (k % 2 == 0) {
            swap(currentPermutation, k, 0);
          } else {
            swap(currentPermutation, k, stackLoopVariable[k]);
          }
          stackLoopVariable[k] += 1;
          this.k = 1;
          break;
        } else {
          stackLoopVariable[k] = 0;
          k += 1;
        }
      }
      return currentPermutation.clone();
    }
  }

}

