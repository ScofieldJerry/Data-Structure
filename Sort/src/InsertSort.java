import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = new int[]{5,4,3,2,1};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    //插入排序（插入排序是指在待排序的元素中，假设前面n-1(其中n>=2)个数已经是排好顺序的，
    // 现将第n个数插到前面已经排好的序列中，然后找到合适自己的位置，使得插入第n个数的这个序列也是排好顺序的。
    // 按照此法对所有元素进行插入，直到整个序列排为有序的过程，称为插入排序）
    public static void insertSort(int[] arr){
        int indexVal = 0;
        int index = 0;
        for (int i = 1; i < arr.length; i++) {
            indexVal = arr[i];
            index = i - 1;
            while (index >= 0 && arr[index] > indexVal){
                arr[index + 1] = arr[index];
                index--;
            }
            if (index + 1 != i) {
                arr[index+1] = indexVal;
            }
        }
    }
}
