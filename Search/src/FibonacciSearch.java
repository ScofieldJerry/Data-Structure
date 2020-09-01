import java.util.Arrays;
//斐波那契查找算法（必须有序），实质还是微分查找，只不过使用斐波那契数列的值来定义中位值
public class FibonacciSearch {
    public static int maxSize = 10;
    public static void main(String[] args) {
        int[] arr = {1,5,99,666,3333,8888,99999};
        System.out.println(fiSearch(arr, 555555));
    }
    //生成一个斐波那契数列
    public static int[] fi(){
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < f.length; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }
    //斐波那契查找
    public static int fiSearch(int[] arr, int val){
        //定义左边界
        int low = 0;
        //定义右边界
        int high = arr.length - 1;
        //初始化k值，用来查找斐波那契数列
        int k = 0;
        //初始化中位下标
        int mid = 0;
        //初始化斐波那契数列
        int[] f = fi();
        //找到待查找数列长度大于且最近的斐波那契值
        while (high > f[k] - 1){
            k ++;
        }
        //为待查找数组扩容
        int[] temp = Arrays.copyOf(arr,f[k]);
        //用最大值替换扩容后的0值
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }
        //开始查找
        while (low <= high){
            //定义中位值
            mid = low + f[k - 1] - 1;
            //待查找值比中位值小，说明在中位值左边
            if (val < temp[mid]) {
                //修改右边界
                high = mid - 1;
                //修改k值，来修改下次查找的中位值
                k--;
            } else if (val > temp[mid]) {
                //待查找值在中位值右边，定义左边界
                low = mid + 1;
                //修改k值，来修改下次查找的中位值
                k -= 2;
            } else {
                //查找到，将下标返回，返回小的那个值
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }
}
