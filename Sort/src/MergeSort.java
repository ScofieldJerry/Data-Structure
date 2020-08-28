import java.util.Arrays;
//归并排序
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8, 5, 0, 1, 4, 7, 5, 2, 3, 6};
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println(Arrays.toString(arr));
    }
    public static void mergeSort(int[] arr, int left, int right, int[] temp){
        if (left < right) {
            //中间索引
            int mid = (left + right) / 2;
            //向作递归进行分解
            mergeSort(arr, left, mid, temp);
            //向右递归进行分解
            mergeSort(arr, mid + 1, right, temp);
            //合并排序
            merge(arr, left, mid, right ,temp);
        }
    }
    public static void merge(int[] arr, int left, int mid, int right, int[] temp){
        //初始化i，左边有序数组的初始下标
        int i = left;
        //初始化j，右边有序数组的初始下标
        int j = mid + 1;
        //初始化temp数组下标
        int t = 0;
        //把左右两遍有序数据按照规则填充到temp，直到左右两边的有序序列有一边处理完毕
        while (i <= mid && j <= right){
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else {
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }
        //左边有剩余，按次序添加到temp中
        while (i <= mid){
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        //右边有剩余，按次序添加到temp中
        while (j <= right){
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }
        //全部处理完毕，将temp数组数据拷贝到arr中
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right){
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }
    }
}
