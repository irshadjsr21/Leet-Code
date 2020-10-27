import java.util.*;

class Solution {
  /**
   * Brute force.
   *
   * Here we calculate the water which will be stored starting from the index `i`.
   * If the height[i] == 0 OR height[i] is less than the next height, then no
   * water can be stored starting from `i`.
   *
   * Then we find the immidiate greater height from `i + 1` (say `j`). If found,
   * we know that the water will be stored between `i` and `j`.
   *
   * If not found, then we decrement the height of the element at `i` and check
   * again.
   * 
   * Complexity: O(n^2)
   */
  public int trapBF(int height[]) {
    int n = height.length;
    int water = 0;

    int i = 0;
    while (i < n - 1) {
      int currentWater = 0;
      if (height[i] == 0 || height[i] < height[i + 1]) {
        i++;
        continue;
      }

      int j = i + 1;
      while (j < n && height[i] > height[j]) {
        currentWater += height[i] - height[j++];
      }

      if (j == n) {
        height[i] = height[i] - 1;
      } else {
        water += currentWater;
        i = j;
      }
    }

    return water;
  }

  /**
   * Dynamic Programming.
   *
   * Here we calculate the amount of water which will be stored at an element `i`.
   * We do this by calculating minimum of the left maximum and right maximum
   * heights from the element at `i`. This will give us the height at which the
   * water will be from the base. Then we subtract it by `height[i]` to get the
   * amount of water at element `i`.
   *
   * This will take `O(n^2)` time if implemented as given, but we can improve it
   * by precomputing the `leftMax` and `rightMax`.
   * 
   * Time Complexity: O(n)
   *
   * Space Complexity: O(n)
   */
  public int trapDP(int height[]) {
    int n = height.length;
    int leftMax[] = new int[n];
    int rightMax[] = new int[n];

    int max = -1;
    for (int i = 0; i < n; i++) {
      if (height[i] > max)
        max = height[i];

      leftMax[i] = max;
    }

    max = -1;
    for (int i = n - 1; i >= 0; i--) {
      if (height[i] > max)
        max = height[i];

      rightMax[i] = max;
    }

    int water = 0;
    for (int i = 0; i < n; i++) {
      int currentWater = Math.min(leftMax[i], rightMax[i]) - height[i];
      water += currentWater;
    }

    return water;
  }

  /**
   * Two pointer.
   *
   * This method is similar to the above solution but has some optimizations to
   * have constant Space Complexity.
   *
   * Here we have 2 pointers (say `low`, `high`) starting from `0` and `n-1`
   * respectively.
   *
   * We calculate two values `leftMax` and `rightMax` which contains the maximum
   * height from `0 to low` and `high to (n-1)` respectively.
   *
   * We calculate the `leftMax` as the maximum of previous `leftMax` and
   * `height[low]`. `rightMax` is calculated similarly.
   *
   * The main idea behind this method is that, the `leftMax` will increase or
   * remain the same in the range `0 - (n-1)` and the `rightMax` will decrease or
   * remain the same in the range `0 - (n-1)`.
   *
   * Since we need to calculate the minimum of the leftMax and rightMax in order
   * to calculate the height of the water at a given index, we can do it as
   * follows:
   *
   * If the `leftMax` (at low) is less than `rightMax` (at high) then we know than
   * minimum of `leftMax` and `rightMax` at `low` will be `leftMax` itself (since
   * `rightMax` when at `low` will be greater than or equal to the current
   * `rightMax`).
   *
   * Similarly, if the `rightMax` (at high) is less than `leftMax` (at low) then
   * we know than minimum of `leftMax` and `rightMax` at `high` will be `rightMax`
   * itself (since `leftMax` when at `high` will be greater than or equal to the
   * current `leftMax`).
   *
   * Complexity: O(n)
   */
  public int trap(int height[]) {
    int water = 0;
    int n = height.length;

    if (n < 3)
      return water;

    int low = 0;
    int high = n - 1;
    int leftMax = height[low], rightMax = height[high];
    while (low <= high) {
      leftMax = Math.max(leftMax, height[low]);
      rightMax = Math.max(rightMax, height[high]);

      if (leftMax > rightMax) {
        int currentWater = rightMax - height[high];
        water += currentWater;
        high--;
      } else {
        int currentWater = leftMax - height[low];
        water += currentWater;
        low++;
      }
    }

    return water;
  }
}
