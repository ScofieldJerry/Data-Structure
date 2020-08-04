public class CircleArrayQueue {
    //表示数组最大容量
    private int maxsize;
    //指向队列的第一个元素，初始值为0
    private int front;
    //指向队列最后一个元素的后一个位置，初始值为0
    private int rear;
    //用于存放数据
    private int[] arr;
    //初始化队列构造器
    public CircleArrayQueue(int queueMaxSize){
        this.maxsize = queueMaxSize;
        this.arr = new int[this.maxsize];
        this.front = 0;
        this.rear = 0;
    }
    //是否已满
    public boolean isFull(){
        return (this.rear + 1) % this.maxsize == front;
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
        arr[rear] = item;
        //rear后移，rear不能无限制增加，考虑取模
        rear = (rear + 1) % maxsize;
    }
    //获取数据
    public int getQueue(){
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据");
        }
        int val = arr[front];
        //将front后移，front不能无限制增加，考虑取模
        front = (front + 1) % maxsize;
        return val;
    }
    //展示队列
    public void showQueue(){
        if (isEmpty()) {
            System.out.println("队列空，没有数据");
            return;
        }
        for (int i = front; i < front + queueSize(); i++) {
            System.out.println(arr[i % maxsize]);
        }
    }
    public int queueSize(){
        return (rear + maxsize - front) % maxsize;
    }
    //显示队列头数据
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，没有头数据");
        }
        return arr[front];
    }
}
