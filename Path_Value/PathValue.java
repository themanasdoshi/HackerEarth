import java.io.*;
import java.util.*;

public class PathValue {
    static class Node {
        int id, maxDiff;

        Node(int id, int maxDiff) {
            this.id = id;
            this.maxDiff = maxDiff;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken()) - 1;
        int E = Integer.parseInt(st.nextToken()) - 1;

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < N; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        st = new StringTokenizer(br.readLine());
        int[] values = new int[N];
        for (int i = 0; i < N; i++) values[i] = Integer.parseInt(st.nextToken());

        System.out.println(findMinPathValue(N, graph, values, S, E));
    }

    private static int findMinPathValue(int N, List<List<Integer>> graph, int[] values, int S, int E) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.maxDiff));
        int[] minDiff = new int[N];
        Arrays.fill(minDiff, Integer.MAX_VALUE);
        minDiff[S] = 0;
        pq.add(new Node(S, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.id, currentDiff = current.maxDiff;

            if (u == E) return currentDiff;

            for (int v : graph.get(u)) {
                int diff = Math.abs(values[u] - values[v]);
                int maxDiff = Math.max(currentDiff, diff);

                if (maxDiff < minDiff[v]) {
                    minDiff[v] = maxDiff;
                    pq.add(new Node(v, maxDiff));
                }
            }
        }

        return -1; // Should not reach here
    }
}
