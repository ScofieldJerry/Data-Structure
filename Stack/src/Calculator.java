public class Calculator {
    public static void main(String[] args) {
        String exp = "70+2*6-4";
        ArrayStack2 num = new ArrayStack2(10);
        ArrayStack2 ope = new ArrayStack2(10);
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';
        String keepNum = "";
        while (true){
            ch = exp.substring(index,index+1).charAt(0);
            if (ope.isOpe(ch)) {
                if (ope.isEmpty()) {
                    ope.push(ch);
                } else {
                    if (ope.priority(ch) <= ope.priority(ope.peek())) {
                        num1 = num.pop();
                        num2 = num.pop();
                        oper = ope.pop();
                        res = num.cal(num1,num2,oper);
                        num.push(res);
                        ope.push(ch);
                    } else {
                        ope.push(ch);
                    }
                }
            } else {
                keepNum += ch;
                if (index == exp.length()-1){
                    num.push(Integer.parseInt(keepNum));
                } else {
                    if (ope.isOpe(exp.substring(index + 1, index + 2).charAt(0))) {
                        num.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
                //num.push(ch - 48);
            }
            index++;
            if (index >= exp.length()) {
                break;
            }
        }
        while (true) {
            if (ope.isEmpty()) {
                break;
            }
            num1 = num.pop();
            num2 = num.pop();
            oper = ope.pop();
            res = num.cal(num1,num2,oper);
            num.push(res);
        }
        System.out.println(num.pop());
    }
}
class ArrayStack2{
    private int maxSize;
    private int[] stack;
    private int top = -1;
    public ArrayStack2(int maxSize){
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
    //返回运算符的优先级
    public int priority(int ope){
        if (ope == '*' || ope == '/') {
            return 1;
        } else if (ope == '+' || ope == '-'){
            return 0;
        } else {
            return -1;
        }
    }
    public boolean isOpe(char val){
        return val == '+' || val == '-' || val == '*' || val == '/';
    }
    public int cal(int num1, int num2, int ope){
        int res = 0;
        switch (ope){
            case '+':res = num1 + num2;
            break;
            case '-':res = num2 - num1;
                break;
            case '*':res = num2 * num1;
                break;
            case '/':res = num2 / num1;
                break;
        }
        return res;
    }
    public int peek(){
        return stack[top];
    }
}
