import java.io.*;

class SolutionExecutor {
  public static void main(String args[]) throws IOException {
    FileReader file = new FileReader("input.txt");
    BufferedReader br = new BufferedReader(file);

    int cap1 = Integer.parseInt(br.readLine());
    int arr1[] = new int[cap1];
    for (int i = 0; i < cap1; i++)
      arr1[i] = Integer.parseInt(br.readLine());

    int cap2 = Integer.parseInt(br.readLine());
    int arr2[] = new int[cap2];
    for (int i = 0; i < cap2; i++)
      arr2[i] = Integer.parseInt(br.readLine());

    br.close();

    Solution sol = new Solution();
    System.out.println(sol.findMedianSortedArrays(arr1, arr2));
  }
}
