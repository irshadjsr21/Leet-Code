import java.net.InetAddress;
import java.util.*;

class Solution {
  /**
   * Uses 2 pointer approach.
   *
   * First we sort the array. Then we fix the 1st element of the triplet (say
   * `i`th element) and then proceed to find the next two. We can easily calculate
   * what should be the sum of the other two elements.
   *
   * To find the other two elements, keep 1st pointer `low` at `i+1` and the 2nd
   * pointer `high` at the last element. Then we find the sum of those two
   * elements and if the sum is less than what is to be found, then we increase
   * the `low` pointer (since we know that the next element of `low` will be
   * greater than or equal to the current `low` because the array is sorted).
   *
   * Similarly, if the sum is greater than what is to be found, then we decrement
   * the `high` pointer.
   *
   * If the sum is equal to what is to be found, then we got our triplet.
   *
   *
   * **** Approache to skip duplicates. ****
   *
   * While iterating through `i`, we skip the elements where element at `i` ==
   * element at `i - 1`. Basically all the duplicate `i` elements.
   *
   * We also need to do the same thing for the `low` and `high` pointer when have
   * found a triplet.
   *
   *
   * NOTE: We could have also used a `Set` to remove the duplicates, but that
   * would add an overhead of converting the set to list.
   *
   * Complexity: O(n^2)
   */
  public List<List<Integer>> threeSum(int[] nums) {
    int len = nums.length;

    Arrays.sort(nums);

    List<List<Integer>> finalList = new ArrayList<>();

    for (int i = 0; i < len; i++) {
      if (i > 0 && nums[i] == nums[i - 1])
        continue;

      int low = i + 1;
      int high = len - 1;
      int toFind = nums[i] * -1;

      while (low < high) {
        int sum = nums[low] + nums[high];

        if (sum > toFind)
          high--;
        else if (sum < toFind)
          low++;
        else {
          List<Integer> list = new ArrayList<>();

          list.add(nums[i]);
          list.add(nums[low]);
          list.add(nums[high]);

          finalList.add(list);

          while (low + 1 < len && nums[low] == nums[low + 1])
            low++;
          while (high > 0 && nums[high] == nums[high - 1])
            high--;

          high--;
          low++;
        }
      }
    }

    return finalList;
  }
}
