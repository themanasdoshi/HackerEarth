import java.util.*;

public class SplitTheBill {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        long[] balance = new long[n];

        for (int i = 0; i < m; i++) {
            String s = sc.next();
            int np = sc.nextInt(), ns = sc.nextInt();

            for (int j = 0; j < np; j++) {
                int id = sc.nextInt() - 1;
                long amt = sc.nextLong();
                balance[id] -= amt;
            }

            for (int j = 0; j < ns; j++) {
                int id = sc.nextInt() - 1;
                long amt = sc.nextLong();
                balance[id] += amt;
            }
        }

        List<String> ans = new ArrayList<>();
        int j = 0;

        for (int i = 0; i < n; i++) {
            if (balance[i] > 0) {
                long curr = balance[i];

                while (curr > 0 && j < n) {
                    if (balance[j] >= 0) {
                        j++;
                        continue;
                    }

                    long mini = Math.min(curr, -balance[j]);
                    curr -= mini;
                    balance[j] += mini;
                    ans.add((i + 1) + " " + (j + 1) + " " + mini);
                }
            }
        }

        for (String s : ans) {
            System.out.println(s);
        }
    }
}
