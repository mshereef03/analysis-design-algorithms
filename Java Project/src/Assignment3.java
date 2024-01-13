import java.util.*;

public class Assignment3 {

    /*Given an undirected graph, explain how you can determine whether it is a tree or not. What would be the running time?

    For it to be a tree it should be connected and not contain any cycles. To check for both of these properties we could do a DFS search, if at any point
    a node has a connection to another node that has already been visited then it contains a cycle and is not a tree. If at the end no cycles were detected
    and all nodes were visited then it's a tree, if some nodes were unvisited then the graph is disconnected and is not a tree either.

    The time complexity for this is O(V+E) since DFS is used and that's the time complexity of DFS. This is because each vertex and edge will explored in
    the worst case.
    * */

    static boolean [] visited = new boolean[1000];
    static boolean isBipartite = true;
    static ArrayList<Integer> path = new ArrayList<>();

    public static void main(String[] args){
        HashMap<Integer, ArrayList<Integer>> graph = new  HashMap<Integer, ArrayList<Integer>>();

        ArrayList<Integer> adjacent1 = new ArrayList<Integer>();
        adjacent1.add(3);adjacent1.add(4);graph.put(1,adjacent1);

        ArrayList<Integer> adjacent2 = new ArrayList<Integer>();
        adjacent2.add(1);adjacent2.add(3);graph.put(2,adjacent2);

        ArrayList<Integer> adjacent3 = new ArrayList<Integer>();
        adjacent3.add(4);graph.put(3,adjacent3);

        ArrayList<Integer> adjacent4 = new ArrayList<Integer>();
        adjacent4.add(1);adjacent4.add(2);graph.put(4,adjacent4);

//         Iterating over the entry set of the map
//        for (Map.Entry<Integer, ArrayList<Integer>> entry : graph.entrySet()) {
//            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//        }

        DFS(graph);
        BFS(graph);
        printCycles(graph);



    }

    public static void DFS(HashMap<Integer, ArrayList<Integer>> graph){
        visited = new boolean[1000];
        System.out.print("DFS: ");
        DFSHelper(graph,1);
        System.out.println();

    }

    public static void DFSHelper(HashMap<Integer, ArrayList<Integer>> graph, int currentNode){
        if(visited[currentNode])return;
        System.out.print(currentNode+" ");
        visited[currentNode]=true;
        for(int x : graph.get(currentNode) ){
            DFSHelper(graph,x);
        }
    }

    public static void BFS(HashMap<Integer, ArrayList<Integer>> graph){
        visited = new boolean[1000];
        System.out.print("BFS: ");
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(1);
        visited[1]=true;
        while(!queue.isEmpty()){
            int currentNode = queue.removeFirst();
            System.out.print(currentNode+" ");
            ArrayList<Integer> adjacent = graph.get(currentNode);
            for(int x : adjacent) {
                if (!visited[x]){
                    queue.add(x);
                    visited[x]=true;
                }
            }
        }
        System.out.println();

    }

    public static void printCycles(HashMap<Integer, ArrayList<Integer>> graph) {
        visited = new boolean[1000];
        int[] colors = new int[1000];
        Arrays.fill(colors, -1);

        for (int node : graph.keySet()) {
            if (colors[node] == -1) { // If the node has not been colored
                if (!dfsCycle(graph, node, colors)) {
                    isBipartite = false;
                    break; // Stop the search if the graph is not bipartite
                }
            }
        }

        System.out.println("Graph is " + (isBipartite ? "bipartite" : "not bipartite"));
    }

    private static boolean dfsCycle(HashMap<Integer, ArrayList<Integer>> graph, int node, int[] colors) {
        if (colors[node] == -1) {
            colors[node] = 0; // Assign the initial color
        }

        visited[node] = true;
        path.add(node);

        boolean isCurrentPathBipartite = true;

        for (int adj : graph.getOrDefault(node, new ArrayList<>())) {
            if (colors[adj] == -1) {
                colors[adj] = 1 - colors[node]; // Alternate color for adjacent node
                isCurrentPathBipartite = dfsCycle(graph, adj, colors) && isCurrentPathBipartite;
            } else if (colors[adj] == colors[node]) {
                isBipartite = false; // Same color as current node, not bipartite
                isCurrentPathBipartite = false;
            }

            if (visited[adj]) {
                int startIndex = path.indexOf(adj);
                if (startIndex != -1 && startIndex < path.size() - 1) { // Check for valid cycle
                    // Cycle detected
                    List<Integer> cycle = new ArrayList<>(path.subList(startIndex, path.size()));
                    cycle.add(adj); // Add the starting node at the end to complete the cycle
                    System.out.println("Cycle: " + cycle);
                }
            }
        }

        path.remove((Integer) node); // Backtracking: Remove the current node before returning
        return isCurrentPathBipartite;
    }






}


