import java.util.*;

class Solution {
  // Brute Force
  // public int lengthOfLongestSubstring(String s) {
  // int maxSubStrLen = 0;
  // int len = s.length();
  // for (int i = 0; i < len; i++) {
  // String subStr = "";
  // int j = i;
  // while (j < len && subStr.indexOf(s.charAt(j)) == -1) {
  // subStr += s.charAt(j);
  // j++;
  // }

  // if (subStr.length() > maxSubStrLen)
  // maxSubStrLen = subStr.length();
  // }
  // return maxSubStrLen;
  // }

  // Improved Brute force with HashMap & non repeating logic
  // public int lengthOfLongestSubstringBR(String s) {
  // int maxSubStrLen = 0;
  // int len = s.length();
  // int counter = 0;
  // for (int i = 0; i < len; i++) {
  // int j = i;
  // int subStrLen = 0;
  // HashMap<Character, Integer> hash = new HashMap<Character, Integer>();
  // while (j < len && hash.getOrDefault(s.charAt(j), -1) == -1) {
  // hash.put(s.charAt(j), j);
  // subStrLen++;
  // j++;
  // }

  // if (subStrLen > maxSubStrLen)
  // maxSubStrLen = subStrLen;

  // counter++;

  // if (j >= len)
  // continue;

  // int prevRepeatingChar = hash.getOrDefault(s.charAt(j), -1);

  // if (prevRepeatingChar != -1) {
  // if (prevRepeatingChar - j > maxSubStrLen) {
  // maxSubStrLen = prevRepeatingChar - j;
  // i = j - 1;
  // } else {
  // i = prevRepeatingChar;
  // }
  // }
  // }

  // System.out.println("Counter: " + counter);
  // return maxSubStrLen;
  // }

  /**
   * Sliding Window.
   *
   * - Uses 2 pointers, `i` and `j` to point to the start and end of the window
   * respectively.
   *
   * - Using a HashSet to contain the characters in the current window.
   *
   * If the current character is not in the HashSet, add it and increase the
   * window.
   *
   * If the current character is present in the HashSet, shrink the window by
   * decrementing `i` pointer and removing the `i`th character from HashSet.
   *
   * Complexity: O(2n)
   */
  public int lengthOfLongestSubstring(String s) {
    int len = s.length();
    int maxSubStr = 0;
    int i = 0, j = 0;
    HashSet<Character> set = new HashSet<Character>();
    while (i < len && j < len) {
      char ch = s.charAt(j);

      if (!set.contains(ch)) {
        j++;
        set.add(ch);
        maxSubStr = Math.max(maxSubStr, j - i);
      } else {
        set.remove(s.charAt(i));
        i++;
      }
    }

    return maxSubStr;
  }

  /**
   * Optimized sliding window.
   *
   * Instead of storing the characters in a set, here we store the character with
   * it's last index in a HashMap.
   *
   * If the current character is not in the HashMap, keep the `i` pointer
   * unchanged. If the current character is present in the HashMap, set `i` to the
   * maximum of `i` and (`index` + 1). Where `index` is the index of the current
   * character stored in the HashMap.
   *
   * Put the current character index in the HashMap and increment the window.
   *
   * Complexity: O(n)
   */
  public int lengthOfLongestSubstringOptimized(String s) {
    int len = s.length();
    int maxSubStr = 0;
    int i = 0, j = 0;
    HashMap<Character, Integer> set = new HashMap<Character, Integer>();

    while (j < len) {
      char ch = s.charAt(j);

      i = Math.max(i, set.getOrDefault(ch, -1) + 1);
      set.put(ch, j++);
      maxSubStr = Math.max(maxSubStr, j - i);
    }

    return maxSubStr;
  }
}
