import java.util.Stack;

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
        System.out.println(singleLinkedListHero.length());
        singleLinkedListHero.reverse(singleLinkedListHero.getHead());
        System.out.println("___________________________");
        singleLinkedListHero.list();
        System.out.println("___________________________");
        singleLinkedListHero.reversePrint(singleLinkedListHero.getHead());
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
    //百度面试题，将链表从后往前打印
    //可以将链表倒置，然后打印，这样会改变链表的结构，假如链表数据特别多，也会影响性能，不推荐使用
    //利用栈先进后出的机制，将单链表存到栈（stack）中去，然后将栈的每一个元素取出并打印，不改变原单链表的机制
    public void reversePrint(HeroNode item){
        if (item.getNext() == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = item.getNext();
        Stack<HeroNode> stack = new Stack<>();
        while (temp != null){
            stack.push(temp);
            temp = temp.getNext();
        }
        while (stack.size() > 0){
            System.out.println(stack.pop());
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
    //腾讯面试题，将一个单链表倒置
    //实现思路：新增一个单链表，将原链表遍历，把原链表每一个节点都置于新增节点的最后。
    public void reverse(HeroNode heroNode){
        if (heroNode.getNext() == null || heroNode.getNext().getNext() == null) {
            return;
        }
        HeroNode temp = head.getNext();
        HeroNode next = null;
        HeroNode re = new HeroNode(0,null,null);
        while (temp != null){
            next = temp.getNext();
            temp.setNext(re.getNext());
            re.setNext(temp);
            temp = next;
        }
        heroNode.setNext(re.getNext());
    }
    //统计单链表实际长度
    public int length(){
        HeroNode temp = head;
        if (temp.getNext() == null) {
            return 0;
        }
        int index = 0;
        while (true){
            if (temp.getNext() == null) {
                break;
            }
            index ++;
            temp = temp.getNext();
        }
        return index;
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

    public HeroNode getHead() {
        return head;
    }

    public void setHead(HeroNode head) {
        this.head = head;
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
