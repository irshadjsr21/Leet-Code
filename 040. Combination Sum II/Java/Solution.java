import java.util.*;

class Solution {
  /**
   * Using Backtracking.
   *
   * Similar to the previous Combination Sum problem.
   */
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> finalList = new ArrayList<>();

    Arrays.sort(candidates);
    backtrack(candidates, 0, target, new ArrayList<Integer>(), finalList);

    return finalList;
  }

  public void backtrack(int nums[], int start, int target, List<Integer> list, List<List<Integer>> result) {
    if (target < 0)
      return;

    if (target == 0) {
      result.add(new ArrayList(list));
      return;
    }

    for (int i = start; i < nums.length; i++) {
      // To skip duplicates
      if (i != start && nums[i] == nums[i - 1])
        continue;

      list.add(nums[i]);
      backtrack(nums, i + 1, target - nums[i], list, result);
      list.remove(list.size() - 1);
    }
  }
}
