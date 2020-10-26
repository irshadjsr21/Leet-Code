import java.util.*;

class Solution {
  /**
   * Using Backtracking.
   *
   * Here we use backtracking method. The backtrack function takes a `list` which
   * contains the current list of numbers we are working with.
   *
   * In backtracking function, if the target is less than 0, there we cannot find
   * any combinations thus we return. If the target is `0` then the `list` already
   * contains the combination for achieving the target.
   *
   * To find the target, we loop through the `start` and add the element at
   * `start` to the list and call the backtrack method again with the new list.
   *
   * After that, we remove the latest element that we added and increment `start`.
   *
   * This will generate all the combinations whoose sum is `target`.
   */
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> finalList = new ArrayList<>();

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

    while (start < nums.length) {
      list.add(nums[start]);
      backtrack(nums, start, target - nums[start], list, result);
      list.remove(list.size() - 1);
      start++;
    }
  }
}
