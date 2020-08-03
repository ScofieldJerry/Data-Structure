public class ArrayQueueDemo {
    public static void main(String[] args) {
    }
}
//使用数组模拟队列，编写一个ArrayQueue类  缺点：不能重复使用
class ArrayQueue{
    //表示数组最大容量
    private int maxsize;
    //队列头
    private int front;
    //队列尾
    private int rear;
    //用于存放数据
    private int[] arr;
    //初始化队列构造器
    public ArrayQueue(int queueMaxSize){
        this.maxsize = queueMaxSize;
        this.arr = new int[this.maxsize];
        this.front = -1;//指向队列头的前一个位置
        this.rear = -1;//指向队列尾
    }
    //是否已满
    public boolean isFull(){
        return this.rear == maxsize - 1;
    }
    //是否为空
    public boolean isEmpty(){
        return this.front == this.rear;
    }
    //加数据
    public void addQueue(int item){
        if (isFull()) {
            System.out.println("队列满，不能加入数据");
            return;
        }
        rear++;
        arr[rear] = item;
    }
    //获取数据
    public int getQueue(){
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据");
        }
        front++;
        return arr[front];
    }
    //展示队列
    public void showQueue(){
        if (isEmpty()) {
            System.out.println("队列空，没有数据");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
    //显示队列头数据
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，没有头数据");
        }
        return arr[front+1];
    }
}
