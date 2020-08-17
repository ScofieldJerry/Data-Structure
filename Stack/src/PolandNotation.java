import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //定义一个逆波兰表达式（数字和符号用空格隔开）
        /*String suffixExp = "30 4 + 5 * 6 -";
        System.out.println(getResult(getListString(suffixExp)));*/
        String exp = "1+((2+3)*4)-5";
        System.out.println(parseSufExp(toList(exp)));
        System.out.println(getResult(parseSufExp(toList(exp))));

    }
    public static List<String> getListString(String exp){
        String[] s = exp.split(" ");
        List<String> list = new ArrayList<>();
        for (String a: s) {
            list.add(a);
        }
        return list;
    }
    public static int getResult(List<String> list){
        Stack<String> stack = new Stack<>();
        for (String a:list) {
            if (a.matches("\\d+")) {//匹配的是多位数
                stack.push(a);
            } else {
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int res = 0;
                if ("+".equals(a)) {
                    res = num1 + num2;
                } else if ("*".equals(a)) {
                    res = num1 * num2;
                } else if ("-".equals(a)) {
                    res = num2 - num1;
                } else if ("/".equals(a)) {
                    res = num2 / num1;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push(res + "");
            }
        }
        return Integer.parseInt(stack.pop());
    }
    public static List<String> toList(String exp){
        List<String> list = new ArrayList<>();
        int i = 0;
        String str;
        char c;
        do {
            if ((c = exp.charAt(i)) < 48 || (c = exp.charAt(i)) > 57) {
                list.add(c + "");
                i++;
            } else {
                str = "";
                while (i < exp.length() && (c = exp.charAt(i)) >= 48 && (c = exp.charAt(i)) <= 57){
                    str += c;
                    i++;
                }
                list.add(str);
            }
        }while (i < exp.length());
        return list;
    }
    public static List<String> parseSufExp(List<String> list){
        //符号栈
        Stack<String> stack1 = new Stack<>();
        //因为s2这个栈，在整个转换过程中，没有pop操作，最后又要逆序输出，所以不用栈，用ArrayList来代替
        List<String> list1 = new ArrayList<>();
        for (String item : list) {
            if (item.matches("\\d+")) {
                list1.add(item);
            } else if (item.equals("(")) {
                stack1.push(item);
            } else if (item.equals(")")) {
                //如果是右括号“）”，则依次弹出stack1栈顶的运算符，并加入list1，知道遇到左括号为止
                while (!stack1.peek().equals("(")){
                    list1.add(stack1.pop());
                }
                //将（弹出stack1，消除小括号
                stack1.pop();
            } else {
                //当item的优先级小于等于stack1栈顶的运算符，将stack1栈顶的运算符弹出并加入list1中，再次与stack1中新的栈顶运算符比较
                while (stack1.size() != 0 && Operation.getVal(stack1.peek()) >= Operation.getVal(item)){
                    list1.add(stack1.pop());
                }
                //最后将运算符压入栈
                stack1.push(item);
            }
        }
        while (stack1.size() != 0){
            list1.add(stack1.pop());
        }
        return list1;
    }
}
class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;
    public static int getVal(String operation){
        int res = 0;
        switch (operation){
            case "+":
                res = ADD;
                break;
            case "-":
                res = SUB;
                break;
            case "*":
                res = MUL;
                break;
            case "/":
                res = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return res;
    }
}
