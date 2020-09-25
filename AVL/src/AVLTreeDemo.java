public class AVLTreeDemo {
    public static void main(String[] args) {
        //int[] arr = {4,3,6,5,7,8};
        int[] arr = {2,1,6,5,7,3};
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        avlTree.infixOrder();
        System.out.println("树的高度"+avlTree.getRoot().height());
        System.out.println("左子树的高度"+avlTree.getRoot().getLeft().height());
        System.out.println("右子树的高度"+avlTree.getRoot().getRight().height());
        System.out.println(avlTree.getRoot().getVal());
    }
}

class AVLTree{
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

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
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

    private void leftRotate(){
        //创建新的结点
        Node node = new Node(val);
        //把新的结点的左子树设置成当前结点的左子树
        node.setLeft(left);
        //把新的结点的右子树设置成当前结点的右子树的左子树
        node.setRight(right.getLeft());
        //把当前结点的值替换成右子结点的值
        val = right.getVal();
        //把当前结点的右子树设置成当前结点的右子树的右子树
        right = right.getRight();
        //把当前结点的左子树设置成新的结点
        left = node;
    }
    private void rightRotate(){
        Node node = new Node(val);
        node.setRight(right);
        node.setLeft(left.getRight());
        val = left.getVal();
        left = left.getLeft();
        right = node;
    }
    //获取该结点下左子树的高度
    public int leftHeight(){
        if (left == null) {
            return 0;
        } else {
            return left.height();
        }
    }
    //获取该结点下右子树的高度
    public int rightHeight(){
        if (right == null) {
            return 0;
        } else {
            return right.height();
        }
    }

    //返回当前结点的高度，以该结点为根结点的树的高度
    public int height(){
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
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
        //当添加完一个结点后，如果右子树的高度-左子树的高度 > 1 ，左旋转
        if (rightHeight() - leftHeight() > 1) {
            if (right != null && right.leftHeight() > right.rightHeight()) {
                right.rightRotate();
                leftRotate();
            } else {
                leftRotate();
            }
            return;
        }
        if (leftHeight()  - rightHeight() > 1) {
            if (left != null && left.rightHeight() > left.leftHeight()) {
                left.leftRotate();
                rightRotate();
            } else {
                rightRotate();
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