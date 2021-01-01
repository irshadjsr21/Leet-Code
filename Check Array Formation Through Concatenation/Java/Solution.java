import java.util.*;

class Solution {
  /**
   * Approach.
   *
   * From the given constrains, we know that each piece (i.e., `pieces[i]`) should
   * only have the elements which are present in the array and in the same order
   * as present in the array.
   *
   * So to solve this, we create a hashMap of all the elements in the array to
   * it's index.
   *
   * Then we look into all the pieces, and see if all of it's elements are in the
   * array and in the same order or not. If not, then we cannot create the array.
   *
   * Time Complexity: O(n)
   *
   * Space Complexity: O(n)
   */
  public boolean canFormArray(int arr[], int pieces[][]) {
    HashMap<Integer, Integer> map = new HashMap();

    for (int i = 0; i < arr.length; i++) {
      map.put(arr[i], i);
    }

    for (int i = 0; i < pieces.length; i++) {
      if (pieces[i].length != 0) {
        int startIndex = map.getOrDefault(pieces[i][0], -1);
        if (startIndex == -1)
          return false;

        for (int j = 1; j < pieces[i].length; j++) {
          int index = map.getOrDefault(pieces[i][j], -1);
          if (index == -1)
            return false;

          if (index != startIndex + 1)
            return false;

          startIndex = index;
        }
      }
    }

    return true;
  }
}
