import java.util.*;

class Solution {
  /**
   * Approach.
   *
   * In this problem, we keep track of the starting index of the current group as
   * `startIndex` and the character of the current group as `startChar`.
   *
   * We go through all the characters of the string and if we find that the char
   * is different from `startChar`, then we know that the previous group has ended
   * at `i - 1`. Then we just add the `startIndex` and `i - 1` to the list if
   * their difference is atleast 3.
   *
   * Complexity: O(n)
   */
  public List<List<Integer>> largeGroupPositions(String s) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();

    int n = s.length();
    if (n == 0)
      return res;

    int startIndex = 1;
    char startChar = s.charAt(0);
    int i;

    for (i = 1; i < n; i++) {
      char c = s.charAt(i);
      if (c != startChar) {
        if (i - startIndex >= 3) {
          List<Integer> list = new ArrayList<Integer>();
          list.add(startIndex);
          list.add(i - 1);
          res.add(list);
        }
        startIndex = i;
        startChar = c;
      }
    }

    if (i - startIndex >= 3) {
      List<Integer> list = new ArrayList<Integer>();
      list.add(startIndex);
      list.add(i - 1);
      res.add(list);
    }

    return res;
  }
}
