import java.io.*;
import java.util.*;
public class PowerGame implements Runnable{
    private static final FastReader fr = new FastReader();
    private static final long MOD = 1_000_000_007L;
    public static void main(String[] args) throws IOException {
        new Thread(null, new PowerGame(), "atcoder", 1<<26).start();
    }
    @Override
    public void run() {
        PrintWriter out = new PrintWriter(System.out);
        int n = fr.nextInt();
        int m = fr.nextInt();
        long[] A = new long[m];
        List<List<Edge>> graph = new ArrayList<>();
        for(int i=0;i<m;i++) A[i] = fr.nextLong();
        for(int i=0;i<n;i++) graph.add(new ArrayList<>());
        for(int i=0;i<m;i++){
            int u = fr.nextInt()-1;
            int v = fr.nextInt()-1;
            graph.get(u).add(new Edge(u,v,i));
            graph.get(v).add(new Edge(v,u,i));
        }
        MyBridge b = new MyBridge(graph);
        List<Edge> bridges = b.getAllBridges();
        List<Long> list = new ArrayList<>();
        for(Edge edge: bridges){
            list.add(A[edge.edge_number]);
        }
        list.sort(Collections.reverseOrder());
        long sum1=0,sum2=0;
        for(int i=0;i<list.size();i+=2){
            sum1 += list.get(i);
            if(i+1 < list.size()) sum2 += list.get(i+1);
        }
        out.print(sum1 + " " + sum2);
        out.flush();
    }

}


class Edge{
    int u;
    int v;
    int edge_number;

    public Edge(int u, int v, int edge_number) {
        this.u = u;
        this.v = v;
        this.edge_number = edge_number;
    }
}
class MyBridge{
    int n;
    List<List<Edge>> graph;
    List<Edge> bridges;
    int[] dp;
    int[] lvl;
    public MyBridge(List<List<Edge>> graph){
        this.graph = graph;
        this.n = graph.size();
        this.bridges = new ArrayList<>();
        this.dp = new int[n];
        this.lvl = new int[n];
    }
    private void bridge_dfs(int node, int parent,int edge_number){
        for(Edge child_edge: graph.get(node)){
            int child = child_edge.v;
            if(lvl[child] == 0){
                lvl[child] = lvl[node]+1;
                bridge_dfs(child,node,child_edge.edge_number);
                dp[node] += dp[child];
            }
            else if(lvl[child] > lvl[node]) dp[node]--;
            else if(lvl[child] < lvl[node]) dp[node]++;
        }
        dp[node]--;
        if(lvl[node]>1 && dp[node] == 0) bridges.add(new Edge(parent,node,edge_number));
    }
    public List<Edge> getAllBridges(){
        lvl[0]++;
        bridge_dfs(0,-1,-1);
        return bridges;
    }
}
class FastReader {
    BufferedReader br;
    StringTokenizer st;

    public FastReader()
    {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt() { return Integer.parseInt(next()); }

    long nextLong() { return Long.parseLong(next()); }

    double nextDouble()
    {
        return Double.parseDouble(next());
    }

    String nextLine()
    {
        String str = "";
        try {
            if(st.hasMoreTokens()){
                str = st.nextToken("\n");
            }
            else{
                str = br.readLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}