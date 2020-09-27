import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
    private ArrayList<String> vertexList;//存储顶点集合
    private int[][] edges;//存储图的邻接矩阵
    private int numOfEdges;//有几条边

    public static void main(String[] args) {
        String[] vertex = {"A","B","C","D","E"};
        Graph graph = new Graph(5);
        for (int i = 0; i < 5; i++) {
            graph.insertVertexList(vertex[i]);
        }
        graph.insertEdges(0,1,1);
        graph.insertEdges(0,2,1);
        graph.insertEdges(1,2,1);
        graph.insertEdges(1,3,1);
        graph.insertEdges(1,4,1);
        graph.show();
    }

    public Graph(int n) {
        this.edges = new int[n][n];
        this.vertexList = new ArrayList<>(n);
        this.numOfEdges = 0;
    }
    public void insertVertexList(String ver){
        this.vertexList.add(ver);
    }
    public void insertEdges(int v1, int v2, int weight){
        this.edges[v1][v2] = weight;
        this.edges[v2][v1] = weight;
        this.numOfEdges ++;
    }
    public int getNumOfVertex(){
        return vertexList.size();
    }
    public int getNumOfEdges(){
        return this.numOfEdges;
    }
    public String geValByIndex(int index){
        return vertexList.get(index);
    }
    public int getWeight(int v1, int v2){
        return edges[v1][v2];
    }
    public void show(){
        for (int[] link:edges) {
            System.out.println(Arrays.toString(link));
        }
    }
}
