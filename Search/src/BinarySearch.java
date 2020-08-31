import java.util.ArrayList;
import java.util.List;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1,1,2,3,4,5,6,7};
        List<Integer> i = binarySearch2(arr,1,0,arr.length - 1);
        System.out.println(i);
    }
    //利用递归实现二分查找
    public static int binarySearch(int[] arr,int val, int left,int right){
        if (left > right) {
            return -1;
        }
        int mid = ( left + right ) / 2;
        int midVal = arr[mid];
        if (midVal > val) {
            return binarySearch(arr,val,left,mid -1);
        } else if (midVal < val) {
            return binarySearch(arr,val,mid + 1,right);
        } else {
            return mid;
        }
    }
    //修改二分查找，使其能返回多个值的下标
    public static List<Integer> binarySearch2(int[] arr, int val, int left, int right){
        if (left > right) {
            return new ArrayList<>();
        }
        int mid = ( left + right ) / 2;
        int midVal = arr[mid];
        if (midVal > val) {
            return binarySearch2(arr,val,left,mid -1);
        } else if (midVal < val) {
            return binarySearch2(arr,val,mid + 1,right);
        } else {
            List<Integer> list = new ArrayList<>();
            int temp = mid - 1;
            while (true){
                if (temp < 0 || arr[temp] != val){
                    break;
                }
                list.add(temp);
                temp --;
            }
            list.add(mid);
            temp = mid + 1;
            while (true){
                if (temp > arr.length - 1 || arr[temp] != val){
                    break;
                }
                list.add(temp);
                temp ++;
            }
            return list;
        }
    }
}
