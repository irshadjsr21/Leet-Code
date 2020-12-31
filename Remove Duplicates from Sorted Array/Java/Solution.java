import java.util.*;

class Solution {
  /**
   * Brute Force
   *
   * O(n^2)
   */
  public int removeDuplicatesBF(int[] nums) {
    int n = nums.length;
    for (int i = 0; i < n; i++) {
      if (i > 0 && nums[i] == nums[i - 1]) {
        int j = i;
        while (j < n - 1) {
          nums[j] = nums[j + 1];
          j++;
        }
        n--;
        i--;
      }
    }
    return n;
  }

  /**
   * Optimized.
   *
   * Here we are replacing all duplicates with a NULL_VALUE in the first
   * iteration.
   *
   * In the second iteration, if we encounter a NULL_VALUE, we look for the next
   * value which is not NULL_VALUE. We swap those two elements and save index
   * where we found the non NULL_VALUE so that we can start from there in the next
   * iteration.
   *
   * Complexity: O(n)
   */
  public int removeDuplicatesOP(int[] nums) {
    int n = nums.length;
    int NULL_VALUE = Integer.MIN_VALUE;
    int latestDup = NULL_VALUE;

    if (n > 0)
      latestDup = nums[0];

    int totalDup = 0;

    for (int i = 1; i < n; i++) {
      if (nums[i - 1] != NULL_VALUE)
        latestDup = nums[i - 1];

      if (nums[i] == latestDup) {
        nums[i] = NULL_VALUE;
        totalDup++;
      }
    }

    int lastJ = -1;

    for (int i = 1; i < n; i++) {
      if (nums[i] == NULL_VALUE) {
        int j = lastJ == -1 ? i + 1 : lastJ;

        while (j < n && nums[j] == NULL_VALUE)
          j++;

        if (j >= n)
          break;

        nums[i] = nums[j];
        nums[j] = NULL_VALUE;

        lastJ = j + 1;
      }
    }

    return n - totalDup;
  }

  /**
   * Two pointer approach.
   *
   * Here we have 2 pointers `i` and `j`. `i` keeps track of the next duplicate
   * element and `j` keeps track of the next unique element.
   * 
   * `i` is the slow pinter and `j` is the fast pointer.
   *
   * When element at `i` != element at `j`, which means that we have found the
   * next unique element, then we incriment i and put the unique element in it.
   *
   * Complexity: O(n)
   */
  public int removeDuplicates(int[] nums) {
    if (nums.length == 0)
      return 0;
    int i = 0;
    for (int j = 1; j < nums.length; j++) {
      if (nums[j] != nums[i]) {
        i++;
        nums[i] = nums[j];
      }
    }
    return i + 1;
  }
}
