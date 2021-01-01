import java.io.*;

class SolutionExecutor {
  public static void main(String args[]) throws IOException {
    FileReader file = new FileReader("input.txt");
    BufferedReader br = new BufferedReader(file);

    String s = br.readLine();
    br.close();

    Solution sol = new Solution();
    System.out.println(sol.largeGroupPositions(s));
  }
}
