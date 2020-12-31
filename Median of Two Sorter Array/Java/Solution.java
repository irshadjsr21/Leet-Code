class Solution {
  // public int getValue(int index, int[] num1, int[] num2) {
  // int i = 0;
  // int j = 0;
  // int count = -1;
  // int value = 0;
  // while (i < num1.length && j < num2.length && count < index) {
  // if (num1[i] < num2[j]) { value = num1[i]; i++;
  // } else {
  // value = num2[j];
  // j++;
  // }
  // count++;
  // }
  // while (i < num1.length && count < index) {
  // value = num1[i];
  // i++;
  // count++;
  // }
  // while (j < num2.length && count < index) {
  // value = num2[j];
  // count++;
  // j++;
  // }

  // return value;
  // }

  // Complexity: O((n+m)/2)
  // public double findMedianSortedArrays(int[] nums1, int[] nums2) {
  // int totalSize = nums1.length + nums2.length;
  // if (totalSize % 2 == 0) {
  // int index2 = totalSize / 2;
  // int index1 = index2 - 1;
  // return (getValue(index1, nums1, nums2) + getValue(index2, nums1, nums2)) /
  // 2.0;
  // } else {
  // return getValue(totalSize / 2, nums1, nums2);
  // }
  // }
  //

  /**
   * Uses binary search technique.
   *
   * It divides the two arrays into left and right parts such that all the
   * elements in the left part (of both the arrays) is less than the all the
   * elements in the right part (of both the arrays).
   *
   * If it has even no of elements, It also ensures that the left part and the
   * right part have equal elements. If it has odd no of elements, It also ensures
   * that the left part is just 1 elemenet bigger than the right part.
   *
   * Complexity: O(log(min(n,m)))
   */
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    if (nums1.length > nums2.length)
      return findMedianSortedArrays(nums2, nums1);

    int totalSize = nums1.length + nums2.length;
    int leftPartitionSize = (totalSize + 1) / 2;
    int low = 0;
    int high = nums1.length;

    while (low <= high) {
      int partition1 = (low + high) / 2;
      int partition2 = leftPartitionSize - partition1;

      int leftMax1 = partition1 <= 0 ? Integer.MIN_VALUE : nums1[partition1 - 1];
      int leftMax2 = partition2 <= 0 ? Integer.MIN_VALUE : nums2[partition2 - 1];

      int rightMin1 = partition1 >= nums1.length ? Integer.MAX_VALUE : nums1[partition1];
      int rightMin2 = partition2 >= nums2.length ? Integer.MAX_VALUE : nums2[partition2];

      if (leftMax1 > rightMin2) {
        high = partition1 - 1;
      } else if (leftMax2 > rightMin1) {
        low = partition1 + 1;
      } else {
        if (totalSize % 2 == 0) {
          int num1 = Math.max(leftMax1, leftMax2);
          int num2 = Math.min(rightMin1, rightMin2);

          return (num1 + num2) / 2.0;
        }

        return Math.max(leftMax1, leftMax2);
      }
    }

    return 0;
  }
}
