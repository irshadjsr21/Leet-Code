class Solution {
  /**
   * Brute Force.
   *
   * Complexity: O(n^3)
   */
  public String longestPalindromeBF(String s) {
    int len = s.length();
    String longestPal = "";

    for (int i = 0; i < len; i++) {

      String subStr = "";
      for (int j = i; j < len; j++) {
        subStr += s.charAt(j);

        if (subStr.length() > longestPal.length() && isPalindrome(subStr))
          longestPal = subStr;
      }
    }

    return longestPal;
  }

  public boolean isPalindrome(String str) {
    int i = 0, len = str.length();

    while (i <= len / 2) {
      if (str.charAt(i) != str.charAt(len - 1 - i)) {
        return false;
      }
      i++;
    }

    return true;
  }

  /**
   * Dynamic Programming.
   *
   * Uses a boolean dpTable to store if the substring from `i` to `j` is a
   * palindrome or not. Simultaniously store the maximum substring which is a
   * palindrome.
   *
   * Note: We only need to store elements where i >= j
   *
   * - Starts off by filling the diagonal elements (i==j). Here all the substring
   * will be a palindrome since `i == j` so the substring will have length 1.
   *
   * - Then fill the upper diagonal such that j = i + 1, here the element will be
   * a substring only if the element at `i` == element at `j`, since the length of
   * the substring here will be 2.
   *
   * - Then we will fill the rest of the diagonals by moving upwards. The
   * substring will be a palindrome only of it's first and last elements are equal
   * and the middle substring is a palindrome.
   *
   * Time Complexity: O(n^2)
   *
   * Space Complexity: O(n^2)
   */
  public String longestPalindromeDP(String s) {
    int len = s.length();
    int maxLen = 0;
    int startIndex = -1;
    boolean dpTable[][] = new boolean[len][len];

    for (int i = 0; i < len; i++) {
      dpTable[i][i] = true;
      maxLen = 1;
      startIndex = i;
    }

    for (int i = 0; i < len - 1; i++) {
      dpTable[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
      if (dpTable[i][i + 1] == true && maxLen < 2) {
        maxLen = 2;
        startIndex = i;
      }
    }

    for (int k = 2; k < len; k++) {
      int j = k;
      int i = 0;

      while (j < len && i < (len - k)) {
        if (s.charAt(i) == s.charAt(j) && dpTable[i + 1][j - 1] == true) {
          dpTable[i][j] = true;
          if ((j - i + 1) > maxLen) {
            maxLen = j - i + 1;
            startIndex = i;
          }
        } else
          dpTable[i][j] = false;

        i++;
        j++;
      }
    }

    return s.substring(startIndex, startIndex + maxLen);
  }

  /**
   * Expand from middle.
   *
   * Here we start from the middle element (say 'i') and check if the equidistant
   * elements from the starting elements are equal or not. Hence we find the
   * substring with maximum length which has the middle element as `i`.
   *
   * Note: There can be one or two middle elements, since the palindrome can be
   * even or odd.
   *
   * Here we loop through the string with variable `i` and we call the apply the
   * above expandFromMiddle algorithm to find the maximum palindrome substring
   * which have the middle element as `i` and `i, i+1`.
   *
   * Hence we find the longest palindromic substring.
   * 
   * Time complexity: O(n^2)
   *
   * Space complexity: O(1)
   */
  public String longestPalindromeEX(String s) {
    int len = s.length();
    int maxLen = 0;
    int startIndex = -1;

    for (int i = 0; i < len; i++) {
      int max1 = expandFromMiddle(s, i, i);
      int max2 = expandFromMiddle(s, i, i + 1);

      int max = Math.max(max1, max2);
      if (max > maxLen) {
        maxLen = max;
        startIndex = i - ((maxLen - 1) / 2);
      }
    }

    return s.substring(startIndex, startIndex + maxLen);
  }

  public int expandFromMiddle(String s, int left, int right) {
    if (left > right)
      return 0;

    int len = s.length();
    while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
      left--;
      right++;
    }

    return right - left - 1;
  }

  /**
   * Manacher's Algorithm.
   *
   * This algorithm is an improvement of the above expandFromMiddle algorithm.
   * Here, instead of applying the expandFromMiddle to all the elements in the
   * string, we resuse some of the previously computes result from
   * expandFromMiddle of other elements.
   *
   * First we add a unique symbol (say "#") which is not present in the string to
   * the string to make the algorithm work for both odd and even palindromes.
   *
   * Example: If string is "abccta", then after adding the "#" the new string
   * becomes "#a#bc#c#t#a#"
   *
   * We also store the maximum palindromic sequence starting from the middle
   * element `i` to `p[i]`.
   *
   * We also keep track of the boundries of the current palindrome. The left
   * boundary is stored in `l` and right boundary is stored in `r`.
   *
   * If the current `i` lies inside the right boundary of the latest palindrome
   * found, then we can say that longest palindromic substring which has `i` as
   * it's middle element is greater than or equal to the p[left mirror element of
   * `i` in the range `l,r`]. This is true because the substring in the range `l,r` 
   * is a palindrome.
   *
   * Example: 
   *
   * Var:    l                    i        r                  
   * Index:  0  1  2  3  4  5  6  7  8  9  10 11 12
   * String: #  a  #  a  #  b  #  a  #  a  #  k  #
   * p[]:    0  1  2  1  0  5  0  
   *
   * Here, we have `i` at `7` and it lies within `l,r` range and it's mirror in 
   * the range `l,r` will be `3`, so we can assign p[i] = p[3]. And after that 
   * we can try to expand more if it is possible. By doing this we skip some 
   * iterations.
   *
   * Time Complexity: O(n)
   *
   * Space Complexity: O(n)
   */
  public String longestPalindrome(String s) {
    String newStr = "";
    for (int i = 0; i < s.length(); i++) {
      newStr += "#" + s.charAt(i);
    }

    newStr += "#";

    int l = 0;
    int r = -1;
    int max = -1;
    int midIndex = -1;

    int len = newStr.length();
    int p[] = new int[len];

    for (int i = 0; i < len; i++) {
      int k;

      if (i < r) {
        k = Math.min(p[l + r - i], r - i);
      } else {
        k = 0;
      }

      while (i + k + 1 < len && i - k - 1 >= 0 && newStr.charAt(i - k - 1) == newStr.charAt(i + k + 1))
        k++;

      p[i] = k;

      if (k > max) {
        max = k;
        midIndex = i;
      }

      if (i + k > r) {
        r = i + k;
        l = i - k;
      }
    }

    int index = (midIndex - max) / 2;
    return s.substring(index, index + max);
  }
}
