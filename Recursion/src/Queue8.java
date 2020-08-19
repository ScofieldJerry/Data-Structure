public class Queue8 {
    int max = 8;
    int[] arr = new int[max];
    static int count = 0;
    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println(count);
    }
    public void check(int n){
        if (n == max) {
            print();
            return;
        }
        for (int i = 0; i < 8; i++) {
            arr[n] = i;
            if (judge(n)) {
                check(n + 1);
            }
        }
    }
    public void print(){
        count++;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    public boolean judge(int n){
        for (int i = 0; i < n; i++) {
            //arr[i] == arr[n] 表示第n个皇后和之前的每一个皇后在同一列
            //Math.abs(n-i) == Math.abs(arr[n] - arr[i]) 表示第n个皇后和之前的每一个皇后在同一斜线
            if (arr[i] == arr[n] || Math.abs(n-i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }
        return true;
    }
}
