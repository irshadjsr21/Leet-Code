import java.util.*;

class Solution {
  enum BS_MOVEMENT {
    DEFAULT, LEFT, RIGHT,
  }

  /**
   * Using binary search.
   *
   * Here, first we the leftMost and right most index of an element using some
   * variation of Binary Search.
   *
   * When finding the Left Most element, we don't stop the search after we find
   * the element. But we store it's index and move left to find another instance
   * of that element untill `low` becomes greater than `high`.
   *
   * We follow a similar approach to find the right most element.
   *
   * Complexity: O(log(n))
   */
  public int[] searchRange(int nums[], int target) {
    int n = nums.length;

    int range[] = { -1, -1 };

    range[0] = binarySearch(nums, 0, n - 1, target, BS_MOVEMENT.LEFT);
    range[1] = binarySearch(nums, 0, n - 1, target, BS_MOVEMENT.RIGHT);

    return range;
  }

  public int binarySearch(int nums[], int low, int high, int target, BS_MOVEMENT movementType) {
    int found = -1;
    while (low <= high) {
      int mid = (low + high) / 2;

      if (nums[mid] < target) {
        low = mid + 1;
      } else if (nums[mid] > target) {
        high = mid - 1;
      } else {
        switch (movementType) {
          case DEFAULT:
            return mid;
          case LEFT:
            found = mid;
            high = mid - 1;
            break;
          case RIGHT:
            found = mid;
            low = mid + 1;
            break;
        }
      }
    }

    return found;
  }
}
