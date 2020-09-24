/**
 * 二叉排序树
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7,3,10,12,5,1,9,0};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            Node node = new Node(arr[i]);
            binarySortTree.add(node);
        }

        binarySortTree.infixOrder();
        binarySortTree.delete(7);
        binarySortTree.delete(3);
        binarySortTree.delete(12);
        binarySortTree.delete(5);
        binarySortTree.delete(9);
        binarySortTree.delete(0);
        binarySortTree.delete(1);
        binarySortTree.delete(10);


        //binarySortTree.delete(2);
        //binarySortTree.delete(5);

        System.out.println("删除后");
        binarySortTree.infixOrder();
    }
}

class BinarySortTree{
    private Node root;

    public Node search(int val){
        if (root == null) {
            return null;
        } else {
            return root.search(val);
        }
    }

    public Node searchParent(int val){
        if (root == null) {
            return null;
        } else {
            return root.searchParent(val);
        }
    }

    public int delRight(Node node){
        Node target = node;
        while (target.getLeft() != null) {
            target = target.getLeft();
        }
        delete(target.getVal());
        return target.getVal();
    }

    public void delete(int val){
        if (root == null) {
            return;
        } else {
            Node search = search(val);
            if (search == null) {
                return;
            }
            if (root.getLeft() == null && root.getRight() == null) {
                root = null;
                return;
            }
            Node node = searchParent(val);
            if (search.getLeft() == null && search.getRight() == null) {//删除叶子结点
                if (node.getLeft() != null && node.getLeft().getVal() == search.getVal()) {
                    node.setLeft(null);
                    return;
                } else if (node.getRight() != null && node.getRight().getVal() == search.getVal()) {
                    node.setRight(null);
                    return;
                }
            } else if (search.getLeft() != null && search.getRight() != null) {//删除有两颗子树的节点
                int i = delRight(search.getRight());
                search.setVal(i);
            } else {//删除只有一颗子树的节点
                if (search.getLeft() != null) {
                    if (node != null) {
                        if (node.getLeft().getVal() == search.getVal()) {
                            node.setLeft(search.getLeft());
                        } else {
                            node.setRight(search.getLeft());
                        }
                    } else {
                        root = search.getLeft();
                    }
                } else {
                    if (node != null) {
                        if (node.getLeft().getVal() == search.getVal()) {
                            node.setLeft(search.getRight());
                        } else {
                            node.setRight(search.getRight());
                        }
                    } else {
                        root = search.getRight();
                    }
                }
            }
        }
    }

    public void add(Node node){
        if (root == null) {
            this.root = node;
        } else {
            root.add(node);
        }
    }
    public void infixOrder(){
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空");
        }
    }
}

class Node{
    private int val;
    private Node left;
    private Node right;

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                '}';
    }

    public void add(Node node){
        if (node == null) {
            return;
        }
        if (node.val < this.val) {
            if (this.getLeft() == null) {
                this.setLeft(node);
            } else {
                this.getLeft().add(node);
            }
        } else {
            if (this.getRight() == null) {
                this.setRight(node);
            } else {
                this.getRight().add(node);
            }
        }
    }

    public void infixOrder(){
        if (this.getLeft() != null) {
            this.getLeft().infixOrder();
        }
        System.out.println(this);
        if (this.getRight() != null) {
            this.getRight().infixOrder();
        }
    }

    public Node(int val) {
        this.val = val;
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

    //查找节点
    public Node search(int val){
        if (val == this.getVal()) {
            return this;
        } else if (val < this.getVal()) {
            if (this.getLeft() == null) {
                return null;
            }
            return this.getLeft().search(val);
        } else {
            if (this.getRight() == null) {
                return null;
            }
            return this.getRight().search(val);
        }
    }

    //查找父节点
    public Node searchParent(int val){
        if ((this.getLeft() != null && this.getLeft().getVal() == val) || (this.getRight() != null && this.getRight().getVal() == val)) {
            return this;
        } else {
            if (this.getLeft() != null && this.getVal() > val) {
                return this.getLeft().searchParent(val);
            } else if (this.getRight() != null && this.getVal() < val) {
                return this.getRight().searchParent(val);
            } else {
                return null;
            }
        }
    }
}
