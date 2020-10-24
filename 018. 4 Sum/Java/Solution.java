import java.util.*;

class Solution {
  /**
   * Using 2 pointer approach.
   *
   * Same as the previous `3 Sum` solution.
   *
   * Here we have an additional outer loop to make it 4 sum.
   *
   * Complexity: O(n^3)
   */
  public List<List<Integer>> fourSum(int[] nums, int target) {
    Arrays.sort(nums);

    int n = nums.length;

    List<List<Integer>> finalList = new ArrayList<>();

    for (int i = 0; i < n - 1; i++) {
      if (i > 0 && nums[i] == nums[i - 1])
        continue;

      for (int j = i + 1; j < n; j++) {
        if (j > i + 1 && nums[j] == nums[j - 1])
          continue;

        int toFind = target - nums[i] - nums[j];

        int low = j + 1;
        int high = n - 1;

        while (low < high) {
          int sum = nums[low] + nums[high];

          if (sum < toFind) {
            low++;
          } else if (sum > toFind) {
            high--;
          } else {
            List<Integer> list = new ArrayList<>();

            list.add(nums[i]);
            list.add(nums[j]);
            list.add(nums[low]);
            list.add(nums[high]);

            finalList.add(list);

            while (low + 1 < n && nums[low] == nums[low + 1])
              low++;
            while (high > 0 && nums[high] == nums[high - 1])
              high--;

            low++;
            high--;
          }
        }
      }
    }

    return finalList;
  }
}
