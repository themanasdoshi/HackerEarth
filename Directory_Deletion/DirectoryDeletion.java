import java.util.*;

public class DirectoryDeletion {
    static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] parent = new int[N];
        int root = -1;

        for (int i = 0; i < N; i++) {
            parent[i] = sc.nextInt();
            if (parent[i] == -1) {
                root = i;
            } else {
                parent[i] = parent[i] - 1;
            }
        }

        int K = sc.nextInt();
        Set<Integer> deleteSet = new HashSet<>();

        for (int i = 0; i < K; i++) {
            deleteSet.add(sc.nextInt() - 1);
        }

        List<Integer>[] children = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            children[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            if (parent[i] != -1) {
                children[parent[i]].add(i);
            }
        }

        dfs(root, false, deleteSet, children);

        System.out.println(count);
    }

    private static void dfs(int node, boolean hasAncestorDeletion, Set<Integer> deleteSet, List<Integer>[] children) {
        boolean currentDeletion = deleteSet.contains(node);

        if (currentDeletion && !hasAncestorDeletion) {
            count++;
            hasAncestorDeletion = true;
        }

        // Continue DFS for each child.
        for (int child : children[node]) {
            dfs(child, hasAncestorDeletion, deleteSet, children);
        }
    }
}
