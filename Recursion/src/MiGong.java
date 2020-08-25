public class MiGong {
    public static void main(String[] args) {
        int[][] map = new int[8][7];
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        findWay(map,1,1);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
    //使用递归给小球找路

    /**
     *
     * @param map 地图
     * @param i  初始横坐标
     * @param j  初始纵坐标
     * @return  找到道路返回true，未找到返回false
     */
    /*
    终点默认在[6][5]
    小球没有走过的道路为0，1为墙，2为表示道路已经走过，3表示该点走过但是走不通
    找路策略：下-》右-》上-》左，如果该点走不通再回溯
     */
    public static boolean findWay(int[][] map, int i, int j){
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2;
                if (findWay(map, i+1, j)) {
                    return true;
                } else if (findWay(map, i, j+1)) {
                    return true;
                } else if (findWay(map, i-1, j)) {
                    return true;
                } else if (findWay(map, i, j-1)) {
                    return true;
                } else {
                    map[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }
}
