package _03经典面试题目.未分类;

/**
 * 给定一个路径统计数组paths，表示一张图。
 * paths[i] == j代表城市i连向城市j，如果paths[i] == i，则表示i城市是首都，
 * 一张图里只会有一个首都且图中除首都指向自己之外不会有环。
 * 例如，paths=[9,1,4,9,0,4,8,9,0,1]，代表的图如图9-14所示。
 * 由数组表示的图可以知道，城市1是首都，所以距离为0，
 * 离首都距离为1的城市只有城市9，离首都距离为2的城市有城市0、3和7，
 * 离首都距离为3的城市有城市4和8，离首都距离为4的城市有城市2、5和6。
 * 所以距离为0的城市有1座，
 * 距离为1的城市有1座，
 * 距离为2的城市有3座，
 * 距离为3的城市有2座，
 * 距离为4的城市有3座。
 * 那么统计数组为nums=[1,1,3,2,3,0,0,0,0,0]
 *
 * 时间复杂度为O ( n ) O(n)O(n)，额外空间复杂度为O ( 1 ) O(1)O(1)
 * Created by huangjunyi on 2022/10/15.
 */
public class _14PathsToNums {

    public static void pathsToNums(int[] paths) {
        if (paths == null || paths.length == 0) return;
        pathsToDistans(paths);
        distanceToNums(paths);
    }

    /**
     * 从 下标为城市，值为离首都的距离 转变为 下标为离首都的距离，值为城市的个数
     * @param paths
     */
    private static void distanceToNums(int[] paths) {
        /*
        此时的数组，记录的时每座城市与首都距离的负数
        遍历数组
        没到一个位置，检查是否为负数
        是则代表没有处理，则进行处理
        通过下标循环怼的方式进行转换
        因为下标表示城市，值表示离首都的距离
        所以值作为下标，检查对应数组上值是否为正数，是则++，退出循环
        不是则先记录该位置对应的值，然后修改为1，再拿着这个值做相同的处理
         */
        for (int i = 0; i < paths.length; i++) {
            int index = paths[i];
            if (index < 0) {
                paths[i] = 0;
                while (true) {
                    index = -index;
                    if (paths[index] >= 0) {
                        paths[index]++;
                        break;
                    } else {
                        int nextIndex = paths[index];
                        paths[index] = 1;
                        index = nextIndex;
                    }
                }
            }
        }
        paths[0] = 1;
    }

    /**
     * 从 路径数组 转变为 下标为城市，值为离首都的距离
     * @param paths
     */
    private static void pathsToDistans(int[] paths) {
        /*
        采用下标循环怼的方式
        from、to两个指针
        每遍历一个位置，如果该位置不是首都（值等于下标），或者不是负数（已经处理过），就进行处理
        from记录来自哪座城市，to记录去往哪座城市
        每到一座城市，把对应的值修改为from，方便往回走
        直到走到首都，或者已经处理过的城市，开始往回走
        往回走时，记录初始值value，如果从首都往回走，则value为0，否则value就是开始往回走的那座城市的值
        每往回走一步，value先--，然后再赋值给当前数组下标对应的值
        处理完成后，每座城市都是与首都距离的负数
         */
        int cap = 0;
        for (int start = 0; start < paths.length; start++) {
            if (paths[start] == start) {
                cap = start;
            } else {
                int from = start;
                int to = paths[start];
                paths[start] = -1;
                while (paths[to] != to) {
                    if (paths[to] > -1) {
                        int next = paths[to];
                        paths[to] = from;
                        from = to;
                        to = next;
                    } else {
                        break;
                    }
                }
                int value = paths[to] == to ? 0 : paths[to];
                while (paths[from] != -1) {
                    int last = paths[from];
                    paths[from] = --value;
                    from = last;
                }
                paths[from] = --value;
            }
        }
        paths[cap] = 0;
    }

}
