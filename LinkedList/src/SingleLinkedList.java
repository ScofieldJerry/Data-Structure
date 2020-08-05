

public class SingleLinkedList {
    public static void main(String[] args) {
        HeroNode heroNode1 = new HeroNode(1,"1","1");
        HeroNode heroNode2 = new HeroNode(2,"2","2");
        HeroNode heroNode3 = new HeroNode(3,"3","3");
        HeroNode heroNode4 = new HeroNode(4,"4","4");
        SingleLinkedListHero singleLinkedListHero = new SingleLinkedListHero();
        singleLinkedListHero.addByOrder(heroNode1);
        singleLinkedListHero.addByOrder(heroNode4);
        singleLinkedListHero.addByOrder(heroNode2);
        singleLinkedListHero.addByOrder(heroNode3);
        singleLinkedListHero.addByOrder(heroNode3);
        singleLinkedListHero.list();
        System.out.println("___________________________");
        HeroNode heroNode5 = new HeroNode(5,"123123","1231313");
        singleLinkedListHero.update(heroNode5);
        singleLinkedListHero.list();
        singleLinkedListHero.delete(1);
        singleLinkedListHero.delete(5);
        System.out.println("___________________________");
        singleLinkedListHero.list();
    }
}
class SingleLinkedListHero{
    //初始化一个头节点，头节点不能动，不存放具体的数据
    private HeroNode head = new HeroNode(0,null,null);
    //添加节点
    //思路：找到当前链表的最后一个节点，将最后节点的next指向新的节点
    public void add(HeroNode heroNode){
        HeroNode temp = head;
        while (true){
            if (temp.getNext() == null){
                break;
            }
            temp = temp.getNext();
        }
        temp.setNext(heroNode);
    }
    public void addByOrder(HeroNode heroNode){
        HeroNode temp = head;
        boolean flag = false;
        while (true){
            if (temp.getNext() == null) {
                break;
            }
            if (temp.getNext().getNum() == heroNode.getNum()) {
                flag = true;
                break;
            }else if (temp.getNext().getNum() > heroNode.getNum()) {
                break;
            }
            temp = temp.getNext();
        }
        if (flag) {
            System.out.println("不能添加，编号重复，编号是："+heroNode.getNum());
        }else {
            heroNode.setNext(temp.getNext());
            temp.setNext(heroNode);
        }
    }
    public void update(HeroNode heroNode){
        HeroNode temp = head;
        boolean flag = false;
        while (true){
            if (temp.getNext() == null) {
                break;
            }
            if (temp.getNext().getNum() == heroNode.getNum()) {
                flag = true;
                break;
            }
            temp = temp.getNext();
        }
        if (flag) {
            temp.getNext().setName(heroNode.getName());
            temp.getNext().setNickName(heroNode.getNickName());
        }else {
            System.out.println("未找到编号为："+heroNode.getNum());
            return;
        }
    }
    public void delete(int num){
        HeroNode temp = head;
        boolean flag = false;
        while (true){
            if (temp.getNext() == null) {
                break;
            }
            if (temp.getNext().getNum() == num) {
                flag = true;
                break;
            }
            temp = temp.getNext();
        }
        if (flag) {
            temp.setNext(temp.getNext().getNext());
        }else {
            System.out.println("没有找到节点为："+ num);
        }

    }
    public void list(){
        if (head.getNext() == null) {
            System.out.println("列表为空");
            return;
        }
        HeroNode temp = head.getNext();
        while (true){
            if (temp == null) {
                break;
            }
            System.out.println(temp.toString());
            temp = temp.getNext();
        }
    }
}
class HeroNode{
    private int num;
    private String name;
    private String nickName;
    private HeroNode next;
    public HeroNode(int num,String name,String nickName){
        this.num = num;
        this.name = name;
        this.nickName = nickName;
    }

    public HeroNode getNext() {
        return next;
    }

    public void setNext(HeroNode next) {
        this.next = next;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'';
    }
}
