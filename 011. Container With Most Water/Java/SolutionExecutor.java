import java.io.*;

class SolutionExecutor {
  public static void main(String args[]) throws IOException {
    FileReader file = new FileReader("input.txt");
    BufferedReader br = new BufferedReader(file);

    int cap = Integer.parseInt(br.readLine());
    int arr[] = new int[cap];
    for (int i = 0; i < cap; i++)
      arr[i] = Integer.parseInt(br.readLine());

    br.close();

    Solution sol = new Solution();
    System.out.println(sol.maxArea(arr));
  }
}
