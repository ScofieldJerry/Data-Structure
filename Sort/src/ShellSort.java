import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort2(arr);
        System.out.println(Arrays.toString(arr));
    }
    //希尔排序也是一种插入排序，是简单插入排序修改之后一个更高效的版本，也成为缩小增量排序
    //采用交换法进行希尔排序
    public static void shellSort(int[] arr){
        int temp = 0;
        for (int i = arr.length / 2; i > 0 ; i /= 2) {
            for (int j = i; j < arr.length; j++) {
                //遍历各组中所有元素（共i组），步长i
                for (int k = j - i; k >= 0 ; k -= i) {
                    //如果当前元素大于加上步长后的，元素交换位置
                    if (arr[k] > arr[k+i]) {
                        temp = arr[k];
                        arr[k] = arr[k+i];
                        arr[k+i] = temp;
                    }
                }
            }
        }
    }
    //采用移位法进行希尔排序
    public static void shellSort2(int[] arr){
        for (int i = arr.length / 2; i > 0; i /= 2) {
            for (int j = i; j < arr.length; j++) {
                int k = j;
                int temp = arr[k];
                if (arr[k] < arr[k - i]) {
                    while (k - i >= 0 && temp < arr[k - i]){
                        arr[k] = arr[k - i];
                        k -= i;
                    }
                    arr[k] = temp;
                }
            }
        }
    }
}
