import java.io.*;

class SolutionExecutor {
  public static void main(String args[]) throws IOException {
    FileReader file = new FileReader("input.txt");
    BufferedReader br = new BufferedReader(file);

    int cap = Integer.parseInt(br.readLine());
    int arr[] = new int[cap];
    for (int i = 0; i < cap; i++) {
      arr[i] = Integer.parseInt(br.readLine());
    }

    int cap2 = Integer.parseInt(br.readLine());
    int pieces[][] = new int[cap2][];

    for (int i = 0; i < cap2; i++) {
      int len = Integer.parseInt(br.readLine());
      pieces[i] = new int[len];

      for (int j = 0; j < len; j++)
        pieces[i][j] = Integer.parseInt(br.readLine());
    }

    br.close();

    Solution sol = new Solution();
    System.out.println(sol.canFormArray(arr, pieces));
  }
}
