import java.util.Arrays;

public class HeapSort {
    private int[] arr;
    public static void main(String[] args) {
        int[] arr = {4,6,8,5,9};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void heapSort(int[] arr){
        int temp = 0;
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr,i,arr.length);
        }
        for (int j = arr.length -1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0,j);
        }
    }
    public static void adjustHeap(int[] arr, int i, int length){
        int temp = arr[i];
        for (int j = 2 * i + 1; j < length; j = j * 2 + 1) {
            if ( j + 1 < length && arr[j] < arr[j + 1]) {
                j ++;
            }
            if (arr[j] > temp) {
                arr[i] = arr[j];
                i = j;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }
}
