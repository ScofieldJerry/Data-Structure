public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = new int[]{3,9,-1,10,-2};
        int temp = 0;
        boolean flag = false;
        //冒泡排序，时间复杂度O（n^2）
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j+1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (flag == false) {
                break;
            } else {
                flag = false;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
