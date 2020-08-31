public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {3,2,6,9,4};
        int i = seqSearch(arr, 3);
        System.out.println(i);
    }
    public static int seqSearch(int[] arr,int num){
        for (int i = 0; i < arr.length; i++) {
            if (num == arr[i]) {
                return i;
            }
        }
        return -1;
    }
}
