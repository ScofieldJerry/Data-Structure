public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(10);
        arrayStack.list();
        arrayStack.push(1);
        arrayStack.list();
        System.out.println("__________________________");
        System.out.println(arrayStack.pop());
        System.out.println("__________________________");
        arrayStack.list();
    }
}
class ArrayStack{
    private int maxSize;
    private int[] stack;
    private int top = -1;
    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }
    public boolean isFull(){
        return top == maxSize - 1;
    }
    public boolean isEmpty(){
        return top == -1;
    }
    public void push(int val){
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = val;
    }
    public int pop(){
        if (isEmpty()) {
            throw new RuntimeException("栈空,没有数据");
        }
        int val = stack[top];
        top--;
        return val;
    }
    public void list(){
        if (isEmpty()) {
            System.out.println("没有数据");
            return;
        }
        for (int i = top; i >= 0 ; i--) {
            System.out.println("第"+i+"个，值是："+stack[i]);
        }
    }
}
