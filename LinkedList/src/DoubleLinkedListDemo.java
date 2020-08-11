public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode2 heroNode1 = new HeroNode2(1,"1","1");
        HeroNode2 heroNode2 = new HeroNode2(2,"2","2");
        HeroNode2 heroNode3 = new HeroNode2(3,"3","3");
        HeroNode2 heroNode4 = new HeroNode2(4,"4","4");
        HeroNode2 heroNode5 = new HeroNode2(5,"666","6666");
        DoubleLinkedList linkedList = new DoubleLinkedList();
        linkedList.addByOrder(heroNode2);
        linkedList.addByOrder(heroNode3);
        linkedList.addByOrder(heroNode1);
        linkedList.addByOrder(heroNode4);
        linkedList.list();
        System.out.println("------------------------");
        linkedList.delete(4);
        //linkedList.update(heroNode5);
        linkedList.list();
    }
}
class DoubleLinkedList{
    //初始化一个头节点，头节点不能动，不存放具体的数据
    private HeroNode2 head = new HeroNode2(0,null,null);
    public HeroNode2 getHead(){
        return head;
    }
    //遍历链表
    public void list(){
        if (head.getNext() == null) {
            System.out.println("列表为空");
            return;
        }
        HeroNode2 temp = head.getNext();
        while (true){
            if (temp == null) {
                break;
            }
            System.out.println(temp.toString());
            temp = temp.getNext();
        }
    }
    //在指定位置插入
    public void addByOrder(HeroNode2 heroNode){
        HeroNode2 temp = head;
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
            if (temp.getNext() == null) {
                temp.setNext(heroNode);
                heroNode.setPre(temp);
            }else {
                heroNode.setNext(temp.getNext());
                heroNode.setPre(temp);
                temp.getNext().setPre(heroNode);
                temp.setNext(heroNode);
            }

        }
    }
    public void addLast(HeroNode2 heroNode2){
        HeroNode2 temp = head;
        while (true){
            if (temp.getNext() == null){
                break;
            }
            temp = temp.getNext();
        }
        temp.setNext(heroNode2);
        heroNode2.setPre(temp);
    }
    public void update(HeroNode2 heroNode){
        HeroNode2 temp = head;
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
        if (head.getNext() == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.getNext();
        boolean flag = false;
        while (true){
            if (temp == null) {
                break;
            }
            if (temp.getNum() == num) {
                flag = true;
                System.out.println("123");
                break;
            }
            temp = temp.getNext();
        }
        if (flag) {
            temp.getPre().setNext(temp.getNext());
            if (temp.getNext() != null) {
                temp.getNext().setPre(temp.getPre());
            }
        }else {
            System.out.println("没有找到节点为："+ num);
        }
    }
}
class HeroNode2{
    private int num;
    private String name;
    private String nickName;
    private HeroNode2 next;
    private HeroNode2 pre;
    public HeroNode2(int num,String name,String nickName){
        this.num = num;
        this.name = name;
        this.nickName = nickName;
    }

    public HeroNode2 getPre() {
        return pre;
    }

    public void setPre(HeroNode2 pre) {
        this.pre = pre;
    }

    public HeroNode2 getNext() {
        return next;
    }

    public void setNext(HeroNode2 next) {
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