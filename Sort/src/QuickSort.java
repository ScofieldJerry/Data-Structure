import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-1, 3, 1, -10, 33, 99, 2, 0};//-10,3,1,-1,33,99,2,0
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
    //快速排序，对冒泡排序的一种改进，使用递归，用空间换时间
    public static void quickSort(int[] arr, int left, int right){
        //左下标
        int l = left;
        //右下标
        int r = right;
        //中轴值
        int pivot = arr[(l + r) / 2];
        //临时变量，用作交换使用
        int temp = 0;
        //将比pivot值大的放到右边，比pivot小的放到左边
        while (l < r){
            //从左遍历数组，直到找到比pivot小的
            while (arr[l] < pivot){
                l ++;
            }
            //从右遍历数组，直到找到比pivot大的
            while (arr[r] > pivot){
                r --;
            }
            //如果满足条件，则说明pivot左边全是比pivot小于等于的，右边全是比pivot大于等于的
            if (l >= r) {
                break;
            }
            //找到位置，进行交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            if (arr[l] == pivot) {
                r --;
            }
            if (arr[r] == pivot) {
                l ++;
            }
        }
        if (l == r) {
            l += 1;
            r -= 1;
        }
        //左排序
        if (left < r) {
            quickSort(arr,left,r);
        }
        //右排序
        if (right > l) {
            quickSort(arr,l,right);
        }
    }
}
