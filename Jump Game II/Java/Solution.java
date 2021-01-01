import java.util.*;

class Solution {
  /**
   * Dynamic Programming.
   *
   * We divide this problem into optimal sumproblems. (Similar to Jump Game)
   *
   * We store the minimum jumps required to reach the last index from index `i` in
   * an array (say `minJumpFrom`). So, `minJumpFrom[i]` stores the minimum jumps
   * required to reach the last index from index `i`, if we cannot reach the last
   * index, then it stores `-1`.
   *
   * We calculate all the values of `minJumpFrom` by using Bottom Up approach.
   *
   * To calculate the minimum no of jumps to reach the last index of the array
   * from index `i`, we need to go to all the immediate possible indexes (say `j`)
   * that we can jump to from the index `i` and select the one which requires the
   * minimum jumps to reach the last index (This value will be stored in
   * `minJumpFrom[j]`). Thus we can set `minJumpFrom[i]` to be `minJumpFrom[j] +
   * 1`.
   *
   * At last the answer will be stored in `minJumpFrom[0]`.
   *
   * Time Complexity: O(n^2)
   *
   * Space Complexity: O(n)
   */
  public int jumpDP(int[] nums) {
    int minJumpFrom[] = new int[nums.length];
    minJumpFrom[nums.length - 1] = 0;

    for (int from = nums.length - 2; from >= 0; from--) {
      minJumpFrom[from] = -1;
      int min = Integer.MAX_VALUE;

      for (int next = from + 1; next <= (from + nums[from]) && next < nums.length; next++) {
        if (minJumpFrom[next] != -1) {
          min = Math.min(min, minJumpFrom[next] + 1);
        }
      }

      if (min != Integer.MAX_VALUE)
        minJumpFrom[from] = min;
    }

    return minJumpFrom[0];
  }

  /**
   * Greedy Algorithm.
   *
   * We can also solve this problem with a greedy approach.
   *
   * Here we store 3 values,
   *
   * `jumps`: To store the number of jumps required.
   * 
   * `maxReach`: The maximum index we can reach from the current index (The
   * current index means the loop index `i`).
   *
   * `currentReach`: The maximum index we can reach from the last jumped index
   * (The last jumped index means the index where the last jump was made).
   *
   * Here we start from the initial values of `maxReach` and `currentReach` as
   * `nums[0]`.
   *
   * Then we loop through all the array elements (say `i`), and calculate the
   * maximum index we can reach from the index `i`. If this index is greater than
   * our `maxReach`, then we update `maxReach`.
   *
   * If we reach the `currentReach`, i.e., `i` == `currentReach`, then we know
   * that we have to make the next jump. Thus, we increment `jumps` and set the
   * `currentReach` to `maxReach`.
   *
   * ** Explanation **
   *
   * When we set the `currentReach` to `nums[0]`, we are saying that from `0`, we
   * can reach the maximum index of `nums[0]` by making one jump.
   *
   * It can also be that the maximum index is not the optimum choice, so we go to
   * the next index `1`, and check the if this choice is better that our current
   * choice. (To check this, we use `maxReach` var and store the maximum value.)
   *
   * Once `i` reaches the `currentReach`, this means that we have reached the end
   * of our jump. So we increment the `jumps` and set the `currentReach` to the
   * `maxReach`.
   * 
   * Complexity: O(n)
   */
  public int jump(int[] nums) {
    if (nums.length == 0)
      return 0;

    int maxReach = nums[0];
    int currentReach = nums[0];

    int jumps = 0;

    for (int i = 1; i < nums.length; i++) {
      if (i == nums.length - 1)
        return jumps + 1;

      maxReach = Math.max(maxReach, nums[i] + i);

      if (i == currentReach) {
        jumps++;
        currentReach = maxReach;
      }
    }

    return jumps;
  }
}
