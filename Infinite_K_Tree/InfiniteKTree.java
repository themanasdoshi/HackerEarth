import java.util.*;

public class InfiniteKTree {
    static long k;

    static long parent(long x) {
        return x / k;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        k = sc.nextLong();
        long q = sc.nextLong();

        Map<Long, Long> inw = new HashMap<>();

        while (q-- > 0) {
            long op = sc.nextLong();

            if (op == 1) {
                long a = sc.nextLong(), b = sc.nextLong();

                if (a == b) {
                    System.out.println(0);
                    continue;
                }

                long ans = 0;

                while (a > 0 || b > 0) {
                    if (a > b) {
                        ans += (1 + inw.getOrDefault(a, 0L));
                        a = parent(a);
                    } else {
                        ans += (1 + inw.getOrDefault(b, 0L));
                        b = parent(b);
                    }

                    if (a == b) break;
                }

                System.out.println(ans);
            } else {
                long a = sc.nextLong(), b = sc.nextLong(), w = sc.nextLong();

                if (a == b) {
                    continue;
                }

                while (a > 0 || b > 0) {
                    if (a > b) {
                        inw.put(a, inw.getOrDefault(a, 0L) + w);
                        a = parent(a);
                    } else {
                        inw.put(b, inw.getOrDefault(b, 0L) + w);
                        b = parent(b);
                    }

                    if (a == b) break;
                }
            }
        }
    }
}
