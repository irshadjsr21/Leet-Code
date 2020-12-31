import java.util.*;

class Solution {
  /**
   * Using binary search.
   *
   * We find the element using binary search and if not found, we just return
   * `low`, since it indicates the position where the element should have been
   * found.
   *
   * Complexity: O(log(n))
   */
  public int searchInsert(int nums[], int target) {
    int n = nums.length;

    int low = 0;
    int high = n - 1;
    while (low <= high) {
      int mid = (low + high) / 2;

      if (nums[mid] < target) {
        low = mid + 1;
      } else if (nums[mid] > target) {
        high = mid - 1;
      } else {
        return mid;
      }
    }

    return low;
  }
}
