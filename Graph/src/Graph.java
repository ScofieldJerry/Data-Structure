import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
    private ArrayList<String> vertexList;//存储顶点集合
    private int[][] edges;//存储图的邻接矩阵
    private int numOfEdges;//有几条边
    private boolean[] isVisited;

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
        graph.dfs();
    }

    public Graph(int n) {
        this.edges = new int[n][n];
        this.vertexList = new ArrayList<>(n);
        this.isVisited = new boolean[n];
        this.numOfEdges = 0;
    }

    //得到第一个邻接结点的下标
    public int getFirstNeighbor(int index){
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    //根据前一个邻接节点的下标来获取下一个邻接节点
    public int getNextNeighbor(int v1, int v2){
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    //深度优先遍历算法
    private void dfs(boolean[] isVisited, int index){
        System.out.print(getValByIndex(index) + "->");
        isVisited[index] = true;
        int w = getFirstNeighbor(index);
        while (w != -1){
            if (isVisited[w] == false) {
                dfs(isVisited, w);
            }
            w = getNextNeighbor(index, w);
        }
    }

    //对dfs进行重载，遍历所有的点，并进行dfs
    public void dfs(){
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
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
    public String getValByIndex(int index){
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
