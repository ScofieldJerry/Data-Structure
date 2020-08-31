public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        System.out.println(insertValSearch(arr, 78, 0, arr.length - 1));
    }
    //关键点在于int mid = left + (right - left) * (val - arr[left]) / (arr[right] - arr[left])
    public static int insertValSearch(int[] arr, int val, int left, int right){
        System.out.println("!!!!!!!!!!");
        //val < arr[0]和val > arr[arr.length - 1]这两个条件是必须的，不然mid的值有可能会很大，或者很小，导致发生数组越界异常
        if (left > right || val < arr[0] || val > arr[arr.length - 1]) {
            return -1;
        }
        int mid = left + (right - left) * (val - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (midVal > val) {
            return insertValSearch(arr,val,left,mid - 1);
        } else if (midVal < val) {
            return insertValSearch(arr,val,mid + 1,right);
        } else {
            return mid;
        }
    }
}
