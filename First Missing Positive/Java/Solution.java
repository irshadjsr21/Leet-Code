import java.util.*;

class Solution {
  /**
   * Using sorting.
   *
   * Here, first we sort the array and then find the index of first positive
   * elemenet.
   *
   * If the first positive element is not `1` or it is out of bound, then we know
   * that the firstMissingPositive is `1`.
   *
   * Then we look for the first element in the array starting from `firstPositive`
   * where the next element not equal to the current element and it is also not
   * equal to the current element + 1. Hence, we know that the missing positive is
   * current element + 1.
   *
   * Complexity: O(n log(n))
   */
  public int firstMissingPositiveSORT(int nums[]) {
    Arrays.sort(nums);
    int i;

    int firstPositive = firstPositive(nums);

    if (firstPositive >= nums.length || nums[firstPositive] != 1)
      return 1;

    for (i = firstPositive; i < nums.length - 1; i++) {
      if (nums[i] > 0 && (nums[i + 1] != nums[i] && nums[i + 1] != nums[i] + 1)) {
        return nums[i] + 1;
      }
    }

    return nums[i] + 1;
  }

  public int firstPositive(int nums[]) {
    int low = 0;
    int high = nums.length - 1;

    while (low <= high) {
      int mid = (low + high) / 2;

      if (nums[mid] < 1) {
        low = mid + 1;
      } else if (nums[mid] > 1) {
        high = mid - 1;
      } else {
        return mid;
      }
    }

    return low;
  }

  /**
   * Linear.
   *
   * Here we utilize the fact that the array of size `n` can have the maximum
   * missing element as `n + 1`.
   *
   * So, we don't need to care about the negative numbers and the numbers greater
   * than `n`. Therefore we swap them with `1`.
   *
   * While doing this, we also check if `1` was already there in the array, if
   * not, then `1` is the first missing positive.
   *
   * After that, the array will only contain elements in the range `[1 - n]`.
   *
   * We loop through the array and calculate a position (say `pos`) as
   * `abs(nums[i]) - 1`. So, if `nums[i]` is 5, the `pos` will be `4`. This
   * indicates the position of the current element if the array was sorted without
   * duplicates. Then we make the sign of the element at `pos` as negative,
   * indicating that the element `pos + 1`, i.e., `5` already exists in the array.
   *
   * After this, we just have to find the index of an element which is not
   * negative. This indicates that we have not found the element `index + 1`, so
   * we return it.
   *
   * If we find no such element, then the missing position is `n + 1`.
   *
   * Complexity: O(n)
   */
  public int firstMissingPositive(int nums[]) {
    int n = nums.length;
    boolean hasOne = false;

    for (int i = 0; i < n; i++) {
      if (nums[i] <= 0 || nums[i] > n) {
        nums[i] = 1;
      } else if (nums[i] == 1) {
        hasOne = true;
      }
    }

    if (!hasOne)
      return 1;

    for (int i = 0; i < n; i++) {
      int pos = Math.abs(nums[i]) - 1;

      if (nums[pos] > 0) {
        nums[pos] *= -1;
      }
    }

    for (int i = 0; i < n; i++) {
      if (nums[i] > 0) {
        return i + 1;
      }
    }

    return n + 1;
  }
}
