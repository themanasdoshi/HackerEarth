import java.util.*;

public class NewTravelingSalesman {
    static int n, m;
    static long[] cst, vsc;
    static List<List<Integer>> g;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        cst = new long[m + 1];
        vsc = new long[m + 1];
        g = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            g.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int cost = sc.nextInt();
            int vis = sc.nextInt();

            cst[i + 1] = cost;
            vsc[i + 1] = vis;
            g.get(a).add(i + 1);
            g.get(b).add(i + 1);
        }

        double mn = 1e18;
        int id = 1, idd = 1;

        for (int i = 1; i <= n; i++) {
            for (int x = 0; x < g.get(i).size(); x++) {
                int a = g.get(i).get(x);
                double xx = (n - 2) * cst[a] + (n - 3) * Math.sqrt(2) * vsc[a];

                for (int y = 0; y < g.get(i).size(); y++) {
                    if (x == y) continue;
                    int b = g.get(i).get(y);
                    double yy = xx + cst[b] + Math.sqrt(vsc[a] * vsc[a] + vsc[b] * vsc[b]);

                    if (yy < mn) {
                        mn = yy;
                        id = a;
                        idd = b;
                    }
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        ans.add(idd);
        for (int i = 1; i < n - 1; i++) {
            ans.add(id);
        }

        System.out.println(ans.size());
        for (int num : ans) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
