import java.util.ArrayList;
import java.util.Collections;
//树的带权路径之和最小称为哈弗曼树（wpl（weighted path length）最小）
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,6,1};
        Node huffmanTree = createHuffmanTree(arr);
        preOrder(huffmanTree);
    }
    public static Node createHuffmanTree(int[] arr){
        //遍历数组，将数组置于一个ArrayList集合中去
        ArrayList<Node> arrayList = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            Node node = new Node(arr[i]);
            arrayList.add(node);
        }
        while (arrayList.size() > 1) {
            Collections.sort(arrayList);
            Node leftNode = arrayList.get(0);
            Node rightNode = arrayList.get(1);
            Node parent = new Node(leftNode.getVal() + rightNode.getVal());
            parent.setLeft(leftNode);
            parent.setRight(rightNode);
            arrayList.remove(leftNode);
            arrayList.remove(rightNode);
            arrayList.add(parent);
        }
        return arrayList.get(0);
    }
    public static void preOrder(Node root){
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }
}
//实现comparable接口，重写compareTo方法，便于排序
class Node implements Comparable<Node>{
    private int val;
    private Node left;
    private Node right;

    public void preOrder(){
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public Node(int val){
        this.val = val;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                '}';
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public int compareTo(Node o) {
        return this.val - o.val;
    }
}
