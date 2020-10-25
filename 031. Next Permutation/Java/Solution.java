import java.util.*;

class Solution {
  /**
   * Next Permutation.
   *
   * Here we first find an element `i` such that `i`th element is less than
   * `i+1`th element. This `i` is the element which needs to be swapped to get the
   * next greater permutation. This `i` needs to be replaced by the next smallest
   * element (say `j`) which is also greater than the `i`th element. This will
   * ensure that the next permutation we get is greater than the previous one and
   * is truly the next greater permutation.
   *
   * Then we find an element, after `i` (say `j`), such that `j`th element is the
   * smallest element which is greater than `i`th element. And then we swap those
   * two.
   *
   * After doing this, we now need to reverse the order of the elements after `i`
   * to make them in ascending order.
   *
   * Complexity: O(n)
   */
  public void nextPermutation(int[] nums) {
    int n = nums.length;

    int i = n - 2, j;
    while (i >= 0 && nums[i] >= nums[i + 1]) {
      i--;
    }

    if (i >= 0) {
      j = n - 1;

      while (j >= 0 && nums[j] <= nums[i]) {
        j--;
      }

      int t = nums[j];
      nums[j] = nums[i];
      nums[i] = t;
    }

    j = n - 1;
    i = i + 1;

    while (i < j) {
      int t = nums[j];
      nums[j] = nums[i];
      nums[i] = t;
      j--;
      i++;
    }
  }
}
