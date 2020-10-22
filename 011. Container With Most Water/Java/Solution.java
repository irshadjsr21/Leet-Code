class Solution {
  // Complexity: O(n^2)
  // public int maxArea(int[] height) {
  // int max = 0;
  // for (int i = 0; i < height.length; i++) {
  // for (int j = 0; j < height.length; j++) {
  // int maxHeight = height[i] > height[j] ? height[j] : height[i];
  // int area = maxHeight * (i - j);
  // if (area > max)
  // max = area;
  // }
  // }
  // return max;
  // }

  /**
   * Uses 2 pointer approach.
   *
   * Starts from the left and right at the same time and calculate the area. Move
   * the pointer whoose height is minimun, thus retaining the pointer to the
   * maximum height.
   *
   * Complexity: O(n)
   */
  public int maxArea(int[] height) {
    int i = 0;
    int j = height.length - 1;
    int max = -1;

    while (i < j) {
      max = Math.max(max, Math.min(height[i], height[j]) * (j - i));

      if (height[i] < height[j]) {
        i++;
      } else {
        j--;
      }
    }

    return max;
  }
}
