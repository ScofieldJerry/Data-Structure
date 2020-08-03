public class SparseArray {
    public static void main(String[] args) {
        //创建一个二维数组11*11  0表示没有棋子  1表示黑子  2表示蓝子
        int chessArray[][] = new int[11][11];
        chessArray[1][2] = 1;
        chessArray[2][4] = 2;
        //输出一下二维数组
        for (int [] a: chessArray) {
            for (int b: a) {
                System.out.printf(b+" ");
            }
            System.out.println();
        }
        int sparse[][] = arrayChangeSparseArray(chessArray);
        for (int array[]:sparse) {
            for (int item:array) {
                System.out.printf(item+" ");
            }
            System.out.println();
        }
        int[][] chess = sparseChangeChessArray(sparse);
        for (int array[]:chess) {
            for (int item:array) {
                System.out.printf(item+" ");
            }
            System.out.println();
        }
    }
    //将二维数组转成稀疏数组
    public static int[][] arrayChangeSparseArray(int [][] chessArray){
        if (chessArray == null) {
            return null;
        }
        int sum = 0;
        for (int [] a: chessArray) {
            for (int b: a) {
                if (b != 0) {
                    sum ++;
                }
            }
        }
        //创建对应的稀疏数组
        int sparseArray[][] = new int[sum+1][3];
        sparseArray[0][0] = chessArray[0].length;
        sparseArray[0][1] = chessArray.length;
        sparseArray[0][2] = sum;
        //初始化一个计数器
        int count = 0;
        //将有效值记录到稀疏数组中
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray[i].length; j++) {
                if (chessArray[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArray[i][j];
                }
            }
        }
        return sparseArray;
    }
    //将稀疏数组转化成二维数组
    public static int[][] sparseChangeChessArray(int [][] sparse){
        if (sparse == null) {
            return null;
        }
        //初始化二维数组
        int[][] chessArray = new int[sparse[0][0]][sparse[0][1]];
        //将稀疏数组中有效值还原到二维数组
        for (int i = 1; i < sparse.length; i++) {
            chessArray[sparse[i][0]][sparse[i][1]] = sparse[i][2];
        }
        return chessArray;
    }
}
