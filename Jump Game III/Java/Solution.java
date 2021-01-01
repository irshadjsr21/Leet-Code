import java.util.*;

class Solution {
  /**
   * Breadth First Search.
   *
   * From the question, we can determine that the array can be thought of as a
   * graph which is connected to a maximum of 2 vertices (i.e., `i - arr[i]` and
   * `i + arr[i]`).
   *
   * After this, the problem becomes a graph find algorithm. We just need to
   * traverse the graph and find `0`.
   *
   * In this approach we use BFS.
   *
   * Time Complexity: O(n)
   *
   * Space Complexity: O(n)
   */
  public boolean canReachBF(int arr[], int start) {
    Queue<Integer> q = new ArrayDeque<Integer>();
    boolean isVisited[] = new boolean[arr.length];

    q.add(start);
    isVisited[start] = true;

    while (!q.isEmpty()) {
      int elem = q.poll();
      int rightIndex = elem + arr[elem];
      int leftIndex = elem - arr[elem];
      if (arr[elem] == 0)
        return true;

      if (checkBoundary(rightIndex, arr.length) && !isVisited[rightIndex]) {
        q.add(rightIndex);
        isVisited[rightIndex] = true;
      }

      if (checkBoundary(leftIndex, arr.length) && !isVisited[leftIndex]) {
        q.add(leftIndex);
        isVisited[leftIndex] = true;
      }
    }

    return false;
  }

  public boolean checkBoundary(int index, int len) {
    return index >= 0 && index < len;
  }

  /**
   * Depth First Search.
   *
   * From the above solution, we can replace the graph traversal algorithm from
   * BFS to DFS and we get another solution.
   *
   * Time Complexity: O(n)
   *
   * Space Complexity: O(n)
   */
  public boolean canReachDF(int arr[], int start) {
    boolean isVisited[] = new boolean[arr.length];
    return dfsVisit(arr, start, isVisited);
  }

  public boolean dfsVisit(int arr[], int start, boolean isVisited[]) {
    isVisited[start] = true;

    if (arr[start] == 0)
      return true;

    int rightIndex = start + arr[start];
    int leftIndex = start - arr[start];

    if (checkBoundary(rightIndex, arr.length) && !isVisited[rightIndex]) {
      if (dfsVisit(arr, rightIndex, isVisited))
        return true;
    }

    if (checkBoundary(leftIndex, arr.length) && !isVisited[leftIndex]) {
      if (dfsVisit(arr, leftIndex, isVisited))
        return true;
    }

    return false;
  }

  /**
   * Depth First Search. (Less Space)
   *
   * We can improve the above algorithm to use less space.
   *
   * Instead of using an array `isVisited` to track of the visited nodes, we can
   * make the `arr[i]` negative to indicate that the `i` th node is visited. For
   * all the other operations, we can use `Maths.abs(arr[i])`. (This works because
   * we know that the `arr` conatains only positive values or `0`).
   *
   * Complexity: O(n)
   */
  public boolean canReach(int arr[], int start) {
    return dfsVisit2(arr, start);
  }

  public boolean dfsVisit2(int arr[], int start) {
    if (!(start <= 0 && start > arr.length) || arr[start] < 0)
      return false;

    arr[start] *= -1;

    if (arr[start] == 0)
      return true;

    int rightIndex = start + Math.abs(arr[start]);
    int leftIndex = start - Math.abs(arr[start]);

    if (dfsVisit2(arr, rightIndex))
      return true;

    if (dfsVisit2(arr, leftIndex))
      return true;

    return false;
  }
}
