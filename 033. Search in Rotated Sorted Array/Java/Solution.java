import java.util.*;

class Solution {
  /**
   * Using binary search.
   *
   * Here, first we find the `pivot` value.
   *
   * We can find the `pivot` where element at `pivot - 1` greater than element at
   * `pivot`.
   *
   * The we just have to implement a binary search. The only modification will be
   * that, while accessing the array element, the actual element should consider
   * the `pivot` value.
   *
   * Complexity: O(n log(n))
   */
  public int search(int nums[], int target) {
    int pivot = 1;
    int n = nums.length;
    while (pivot < n && nums[pivot - 1] < nums[pivot]) {
      pivot++;
    }

    int low = 0;
    int high = n - 1;

    while (low <= high) {
      int mid = (low + high) / 2;
      int midIndex = (mid + pivot) % n;

      if (nums[midIndex] < target) {
        low = mid + 1;
      } else if (nums[midIndex] > target) {
        high = mid - 1;
      } else {
        return midIndex;
      }
    }

    return -1;
  }
}
