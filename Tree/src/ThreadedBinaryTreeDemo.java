public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode2 heroNode1 = new HeroNode2(1,"tom");
        HeroNode2 heroNode2 = new HeroNode2(3,"jack");
        HeroNode2 heroNode3 = new HeroNode2(6,"jerry");
        HeroNode2 heroNode4 = new HeroNode2(8,"mary");
        HeroNode2 heroNode5 = new HeroNode2(10,"king");
        HeroNode2 heroNode6 = new HeroNode2(14,"lion");
        heroNode1.setLeft(heroNode2);
        heroNode1.setRight(heroNode3);
        heroNode2.setLeft(heroNode4);
        heroNode2.setRight(heroNode5);
        heroNode3.setLeft(heroNode6);
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(heroNode1);
        threadedBinaryTree.threadedNodes();
        //System.out.println(heroNode5.getLeft());
        //System.out.println(heroNode5.getRight());
        threadedBinaryTree.threadedList();
    }
}
class ThreadedBinaryTree{
    private HeroNode2 root;
    //定义前驱节点的引用
    private HeroNode2 pre = null;
    public HeroNode2 getRoot() {
        return root;
    }

    public void setRoot(HeroNode2 root) {
        this.root = root;
    }
    public void threadedNodes(){
        this.threadedNodes(root);
    }
    //遍历中序线索化二叉树
    public void threadedList(){
        HeroNode2 heroNode2 = root;
        while (heroNode2 != null){
            while (heroNode2.getLeftType() == 0){
                heroNode2 = heroNode2.getLeft();
            }
            System.out.println(heroNode2);
            while (heroNode2.getRightType() == 1){
                heroNode2 = heroNode2.getRight();
                System.out.println(heroNode2);
            }
            heroNode2 = heroNode2.getRight();
        }
    }
    //编写对二叉树进行中序线索化
    public void threadedNodes(HeroNode2 heroNode2){
        if (heroNode2 == null) {
            return;
        }
        threadedNodes(heroNode2.getLeft());
        if (heroNode2.getLeft() == null) {
            heroNode2.setLeft(pre);
            heroNode2.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(heroNode2);
            pre.setRightType(1);
        }
        pre = heroNode2;
        threadedNodes(heroNode2.getRight());
    }
    public void preOrder(){
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }
    public void infixOrder(){
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }
    public void postOrder(){
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }
    public HeroNode2 preSearch(int num){
        if (this.root != null) {
            return this.root.preSearch(num);
        } else {
            return null;
        }
    }
    public HeroNode2 infixSearch(int num){
        if (this.root != null) {
            return this.root.infixSearch(num);
        } else {
            return null;
        }
    }
    public HeroNode2 postSearch(int num){
        if (this.root != null) {
            return this.root.postSearch(num);
        } else {
            return null;
        }
    }
    public void delete(int num){
        if (this.root != null) {
            if (this.root.getId() == num) {
                this.root = null;
            } else {
                this.root.delete(num);
            }
        } else {
            System.out.println("树为空");
        }
    }
}
class HeroNode2{
    private int id;
    private String name;
    private HeroNode2 left;
    private HeroNode2 right;
    //如果leftType为0表示指向左子树，为1表示指向前驱节点
    private int leftType;
    //如果rightType为0表示指向右子树，为1表示指向后继节点
    private int rightType;


    public HeroNode2(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode2 getLeft() {
        return left;
    }

    public void setLeft(HeroNode2 left) {
        this.left = left;
    }

    public HeroNode2 getRight() {
        return right;
    }

    public void setRight(HeroNode2 right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
    public void preOrder(){
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
    public void infixOrder(){
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
    public void postOrder(){
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }
    public HeroNode2 preSearch(int num){
        if (this.id == num) {
            return this;
        }
        HeroNode2 heroNode = null;
        if (this.left != null) {
            heroNode = this.left.preSearch(num);
        }
        if (heroNode != null) {
            return heroNode;
        }
        if (this.right != null) {
            heroNode = this.right.preSearch(num);
        }
        return heroNode;
    }
    public HeroNode2 infixSearch(int num){
        HeroNode2 heroNode = null;
        if (this.left != null) {
            heroNode = this.left.infixSearch(num);
        }
        if (heroNode != null) {
            return heroNode;
        }
        if (this.id == num) {
            return this;
        }
        if (this.right != null) {
            heroNode = this.right.infixSearch(num);
        }
        return heroNode;
    }
    public HeroNode2 postSearch(int num){
        HeroNode2 heroNode = null;
        if (this.left != null) {
            heroNode = this.left.postSearch(num);
        }
        if (heroNode != null) {
            return heroNode;
        }
        if (this.right != null) {
            heroNode = this.right.postSearch(num);
        }
        if (heroNode != null) {
            return heroNode;
        }
        if (this.id == num) {
            return this;
        }
        return heroNode;
    }
    public void delete(int num){
        if (this.left != null && this.left.id == num) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.id == num) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.delete(num);
        }
        if (this.right != null) {
            this.right.delete(num);
        }
    }
}
