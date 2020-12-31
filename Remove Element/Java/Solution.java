import java.util.*;

class Solution {
  /**
   * Brute Force
   *
   * O(n^2)
   */
  public int removeElementBF(int[] nums, int val) {
    int n = nums.length;
    for (int i = 0; i < n; i++) {
      if (nums[i] == val) {
        int j = i;
        while (j < n - 1) {
          nums[j] = nums[j + 1];
          j++;
        }

        i--;
        n--;
      }
    }

    return n;
  }

  /**
   * Optimized using 2 pointer.
   *
   * Here, if we find the element which we want to remove (say `val`) at `i`, then
   * we go ahead and find it's replacement in the `j` loop whoose value is not
   * equal the `val`. If found, we swap those two elements and save the `j`
   * pointer for future `j` loops.
   *
   * Complexity: O(n)
   */
  public int removeElementOP(int[] nums, int val) {
    int n = nums.length;
    int lastElem = -1;
    int i;

    for (i = 0; i < n; i++) {
      if (nums[i] == val) {
        int j = lastElem == -1 ? i + 1 : lastElem;

        while (j < n && nums[j] == val)
          j++;

        if (j >= n)
          break;

        nums[i] = nums[j];
        nums[j] = val;
      }
    }

    return i;
  }

  /**
   * Here we again find the element we want to remove (say `val`) at `i`. Then we
   * swap that element with the last element in the array and reduce it's size.
   *
   * Complexity: O(n)
   */
  public int removeElement(int[] nums, int val) {
    int n = nums.length;

    for (int i = 0; i < n; i++) {
      if (nums[i] == val) {
        nums[i] = nums[n - 1];
        n--;
        i--;
      }
    }

    return n;
  }
}
