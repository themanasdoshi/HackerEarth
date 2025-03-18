import java.util.*;

class PopulationOutburst {
    static class Node {
        int id, capacity, used, level;
        Node(int id, int capacity, int level) {
            this.id = id;
            this.capacity = capacity;
            this.level = level;
            this.used = 0;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int RC0 = sc.nextInt();

        Node root = new Node(0, RC0, 0);

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (N-- > 0) {
            int newId = sc.nextInt();
            int newCap = sc.nextInt();

            while (!queue.isEmpty() && queue.peek().used == queue.peek().capacity) {
                queue.poll();
            }

            Node parent = queue.peek();

            int childLevel = parent.level + 1;
            parent.used++;
            int rank = parent.used;

            System.out.println(parent.id + " " + childLevel + " " + rank);

            if (parent.used == parent.capacity) {
                queue.poll();
            }

            Node child = new Node(newId, newCap, childLevel);

            if (newCap > 0) {
                queue.add(child);
            }
        }
    }
}
