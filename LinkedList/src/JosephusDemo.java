public class JosephusDemo {
    public static void main(String[] args) {
        JosephusCircle circle = new JosephusCircle();
        circle.add(5);
        circle.list();
        circle.deleteBySum(5,2,2);
    }
}
class JosephusCircle{
    private Boy first = null;
    public void add(int num){
        if (num < 1) {
            System.out.println("数字不正确");
            return;
        }
        Boy cur = null;
        for (int i = 1; i <= num; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.setNext(first);
                cur = first;
            } else {
                cur.setNext(boy);
                boy.setNext(first);
                cur = boy;
            }
        }
    }
    public void list(){
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        Boy cur = first;
        while (true){
            System.out.println("编号是："+cur.getNum());
            if (cur.getNext() == first) {
                break;
            }
            cur = cur.getNext();
        }
    }
    public void deleteBySum(int sum, int count, int start){
        if (first == null || start < 1 || start > sum) {
            System.out.println("数据有误");
            return;
        }
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        for (int i = 0; i < start -1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        while (true) {
            if (helper == first) {
                break;
            }
            for (int i = 0; i < count - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.println("出圈的编号:"+first.getNum());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.println("最后的编号:"+first.getNum());
    }
}
class Boy{
    private int num;
    private Boy next;
    public Boy(int number){
        this.num = number;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
