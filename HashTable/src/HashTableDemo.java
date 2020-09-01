public class HashTableDemo {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(7);
        Emp emp = new Emp(1,"李四");
        Emp emp1 = new Emp(2,"王五");
        Emp emp2 = new Emp(3,"赵钱");
        Emp emp3 = new Emp(8,"韩霄");
        hashTable.add(emp);
        hashTable.add(emp1);
        hashTable.list();
        hashTable.add(emp2);
        hashTable.add(emp3);
        hashTable.list();
        hashTable.findById(1);
        hashTable.findById(2);
        hashTable.findById(3);
        hashTable.findById(4);
        hashTable.findById(8);
        hashTable.delete(2);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
        hashTable.list();
    }
}
class HashTable{
    private EmpLinkedList[] linkedList;
    private int size;
    public HashTable(int size) {
        this.size = size;
        this.linkedList = new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            linkedList[i] = new EmpLinkedList();
        }
    }
    public void add(Emp emp){
        int num = hash(emp.getId());
        linkedList[num].add(emp);
    }
    public void list(){
        for (int i = 0; i < linkedList.length; i++) {
            linkedList[i].list();
        }
    }
    public int hash(int id){
        return id % size;
    }
    public void findById(int id){
        int num = hash(id);
        Emp byId = linkedList[num].findById(id);
        if (byId == null) {
            System.out.println("未查找到");
        } else {
            System.out.println(byId.toString());
        }
    }
    public void delete(int id){
        int num = hash(id);
        linkedList[num].delete(id);
    }
}
class Emp{
    private int id;
    private String name;
    private Emp next;

    public Emp(int id, String name) {
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

    public Emp getNext() {
        return next;
    }

    public void setNext(Emp next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
class EmpLinkedList{
    private Emp head = new Emp(0,"");
    public void add(Emp emp){
        if (head.getNext() == null) {
            head.setNext(emp);
            return;
        }
        Emp cur = head;
        while (true){
            if (cur.getNext() == null) {
                break;
            }
            cur = cur.getNext();
        }
        cur.setNext(emp);
    }
    public void list(){
        if (head.getNext() == null) {
            System.out.println("链表为空");
            return;
        }
        Emp cur = head;
        while (true){
            if (cur.getNext() == null) {
                break;
            }
            System.out.println(cur.getNext().toString());
            cur = cur.getNext();
        }
    }
    public Emp findById(int id){
        if (head.getNext() == null) {
            return null;
        }
        Emp cur = head.getNext();
        while (true){
            if (cur == null) {
                cur = null;
                break;
            } else if (cur.getId() == id) {
                break;
            }
            cur = cur.getNext();
        }
        return cur;
    }
    public void delete(int num){
        if (head.getNext() == null) {
            return;
        }
        Emp cur = head;
        while (true){
            if (cur.getNext() == null) {
                break;
            } else if (cur.getNext().getId() == num) {
                break;
            }
            cur = cur.getNext();
        }
        if (cur.getNext() != null) {
            if(cur.getNext().getNext() == null){
                cur.setNext(null);
            } else {
                Emp emp = cur.getNext().getNext();
                cur.setNext(emp);
            }
        }
    }
}
