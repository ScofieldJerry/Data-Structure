import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        int[] arr = new int[]{5,4,3,2,1};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    //选择排序（从arr[0]到arr[n]找到最小值，然后将最小值与arr[0]交换位置,
    // 然后从arr[1]到arr[n]再找到最小值，将arr[1]和最小值交换位置，依次类推）
    public static void selectSort(int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            int min = arr[i];
            int index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < min ) {
                    min = arr[j];
                    index = j;
                }
            }
            if (index != i) {
                arr[index] = arr[i];
                arr[i] = min;
            }
        }
    }
}
