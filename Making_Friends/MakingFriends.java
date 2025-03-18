import java.util.Scanner;

public class MakingFriends {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        for (int i = 0; i < num; i++) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            if (n % 2 == 0 && m <= n / 2) {
                System.out.println("Yes");
            } else if (m == 0) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }
}
