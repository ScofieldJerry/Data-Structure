import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int[] arr = { 2,17,79,22,11,66,1,0};//0,1,2,22,66,17,79
        radixSort(arr);
    }
    public static void radixSort(int[] arr){
        //找到最大值
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //找到最大值的位数
        int maxLength = (max + "").length();
        //定义一个二维数组，每个数组对应0-9的数位桶
        int[][] bucket = new int[10][arr.length];
        //定义一个一维数组用来记录每个数位同种放了多少数据
        // 例如element[1] = 2,就是位数位2的数为同中放了2个数据
        int[] elementCount = new int[10];
        //
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                int digit = arr[j] / n % 10;
                bucket[digit][elementCount[digit]] = arr[j];
                elementCount[digit] ++;
            }
            int index = 0;
            for (int j = 0; j < elementCount.length; j++) {
                if (elementCount[j] != 0) {
                    for (int k = 0; k < elementCount[j]; k++) {
                        arr[index++] = bucket[j][k];
                    }
                }
                elementCount[j] = 0;
            }
            System.out.println(i +"                 "+ Arrays.toString(arr));
        }
    }
}
