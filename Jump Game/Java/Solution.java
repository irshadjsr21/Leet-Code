import java.util.*;

class Solution {
  /**
   * Dynamic Programming.
   *
   * We divide this problem into optimal sumproblems.
   *
   * We label the all the indexes `Good` or `Bad`. `Good` index means, we can
   * reach the last index from that index. `Bad` index means, we can't reach the
   * last index from that index.
   *
   * To calculate if we can jump to the last index of the array from index `i`, we
   * need to go to all the immediate possible indexes that we can jump to from the
   * index `i` and check if they are `Good` indexes. If any one of them is `Good`,
   * then we can say that the `i`th index is `Good` otherwiser it is `Bad`.
   *
   * To execute this idea, we maintain a boolean array (say `canJumpFrom`) such
   * that `canJumpFrom[i]` will be `true` if we can reach the last index of the
   * array from index `i`.
   *
   * We use Bottom-Up approah here. So we move from the last index to the first
   * index and compute if the last index is reachable or not.
   *
   * Time Complexity: O(n^2)
   *
   * Space Complexity: O(n)
   */
  public boolean canJumpDP(int[] nums) {
    boolean canJumpFrom[] = new boolean[nums.length];
    canJumpFrom[nums.length - 1] = true;

    for (int from = nums.length - 2; from >= 0; from--) {
      canJumpFrom[from] = false;
      for (int next = from + 1; next <= (from + nums[from]) && next < nums.length; next++) {
        if (canJumpFrom[next]) {
          canJumpFrom[from] = true;
          break;
        }
      }
    }

    return canJumpFrom[0];
  }

  /**
   * Greedy Algorithm.
   *
   * This is an improvement to the above idea. If we see the above idea, to
   * compute if we can reach the last index from the index `i`, we only need to
   * find the next reachable `Good` index from `i`. We can skip all the other
   * `Good` or `Bad` indexes and just check if we can reach the leftmost `Good`
   * index from the index `i`. (This is valid because if the leftmost `Good` index
   * is not reachable, then we cannot reach any other `Good` index.)
   *
   * To execute this idea, we only maintain the index of the leftmost `Good` index
   * (say `goodIndex`). Initially this will be the last index itself. Now when
   * moving from right to left in the array, we only check if we can reach the
   * `goodIndex` from `i` or not. If we can reach the `goodIndex`, then `i` is a
   * `Good` index otherwise it is a `Bad` index. If `i` is a `Bad` index, we'll
   * just skip it. But if it is a `Good` index, we need to modify `goodIndex` to
   * `i`, since now `i` is the leftmost `Good` index.
   *
   * At last, we just check if the leftmost `Good` index is `0` or not.
   *
   * Complexity: O(n)
   */
  public boolean canJump(int[] nums) {
    int goodIndex = nums.length - 1;

    for (int i = nums.length - 2; i >= 0; i--) {
      if (nums[i] >= goodIndex - i) {
        goodIndex = i;
      }
    }

    return goodIndex == 0;
  }
}
