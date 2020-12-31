import java.util.*;

class Solution {
  /**
   * Uses 2 pointer approach.
   *
   * Similar to `3 Sum` problem.
   *
   * The only difference is that we are now keeping track of the closest sum to
   * the target by introducing two variables (`maxDiff`, `closest`).
   *
   * `maxDiff` will store the difference of the closest sum to the target.
   *
   * `closest` will store the closest sum.
   *
   * Complexity: O(n^2)
   */
  public int threeSumClosest(int[] nums, int target) {
    Arrays.sort(nums);

    int n = nums.length;
    int maxDiff = Integer.MAX_VALUE;
    int closest = 0;

    for (int i = 0; i < n; i++) {
      if (i > 0 && nums[i] == nums[i - 1])
        continue;

      int toFind = target - nums[i];
      int low = i + 1;
      int high = n - 1;

      while (low < high) {
        int sum = nums[low] + nums[high];
        int dif = Math.abs(toFind - sum);

        if (dif < maxDiff) {
          maxDiff = dif;
          closest = sum + nums[i];
        }

        if (sum < toFind) {
          low++;
        } else if (sum > toFind) {
          high--;
        } else {
          return target;
        }
      }
    }

    return closest;
  }
}
