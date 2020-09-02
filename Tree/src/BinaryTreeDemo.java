public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode heroNode = new HeroNode(1,"aa");
        HeroNode heroNode1 = new HeroNode(2,"bb");
        HeroNode heroNode2 = new HeroNode(3,"cc");
        HeroNode heroNode3 = new HeroNode(4,"dd");
        heroNode.setLeft(heroNode1);
        heroNode.setRight(heroNode2);
        heroNode2.setRight(heroNode3);
        binaryTree.setRoot(heroNode);
        binaryTree.preOrder();
//        binaryTree.infixOrder();
//        binaryTree.postOrder();
//        System.out.println(binaryTree.postSearch(6));
        binaryTree.delete(1);
        System.out.println("删除后");
        binaryTree.preOrder();
    }
}
class BinaryTree{
    private HeroNode root;

    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
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
    public HeroNode preSearch(int num){
        if (this.root != null) {
            return this.root.preSearch(num);
        } else {
            return null;
        }
    }
    public HeroNode infixSearch(int num){
        if (this.root != null) {
            return this.root.infixSearch(num);
        } else {
            return null;
        }
    }
    public HeroNode postSearch(int num){
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
class HeroNode{
    private int id;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int id, String name) {
        this.id = id;
        this.name = name;
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

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
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
    public HeroNode preSearch(int num){
        if (this.id == num) {
            return this;
        }
        HeroNode heroNode = null;
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
    public HeroNode infixSearch(int num){
        HeroNode heroNode = null;
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
    public HeroNode postSearch(int num){
        HeroNode heroNode = null;
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
