package _03经典面试题目.前缀树;

/**
 * 一个数组的异或和是指数组中所有的数异或在一起的结果，给定一个数组arr，求最大子数组异或和
 *
 * Created by huangjunyi on 2022/10/1.
 */
public class _02MaxSubArrayEorSum {

    /**
     * 第一种解法 预处理数组
     * @param arr
     * @return
     */
    public  int getMaxEorSum1(int[] arr){
        if (arr == null || arr.length == 0) return 0;

        /*
        求一个浅醉预处理数组
        每个位置代表数组从0到i的异或和
         */
        int[] eor = new int[arr.length];
        eor[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            eor[i] = eor[i - 1] ^ arr[i];
        }

        /*
        遍历，以i结尾，从j到i的异或和
        j到i的异或和，相当于0到i的异或和异或上0到j-1的异或和
         */
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j <= i; j++) {
                max = Math.max(max, j == 0 ? eor[i] : eor[i] ^ eor[j - 1]);
            }
        }

        return max;
    }

    private static class Node {
        private Node[] nexts = new Node[2]; //前缀树节点，有0和1两条路径
    }

    /**
     * 前缀树，每个节点有0和1两条路径，表示一个数的一个二进制位
     */
    private static class Tree {

        private Node head = new Node();

        /**
         * 往前缀树添加一个数
         * @param num
         */
        public void add(int num) {
            Node cur = head;
            /*
            遍历num的每个二进制位，在前缀树中保存二进制位的节点
            节点有则复用，没有新建
             */
            for (int i = 31; i >= 0; i--) {
                int path = ((num >> i) & 1);
                cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
                cur = cur.nexts[path];
            }
        }

        /**
         * 从前缀树中找出一个数与num异或得到最大的数
         * @param num
         * @return
         */
        public int maxXor(int num) {
            Node cur = head;
            int res = 0;
            /*
            遍历num的每个二进制位，去寻找前缀树中的最佳路径
            如果是高位第32位，则最佳路径是当前二进制位上原来的数，因为负数符号位1异或1等于0 -> 变成正数，正数符号位0异或0还是0 -> 还是正数
            非32位，则取当前位的数的相反，异或的结果就是1
            如果当前节点不存在最佳路径的子节点，则只能走另外一条路径
            最佳路径代表的数bset与num当前二进制位的数path异或得到结果左移然后与结果进行或等，把该位填到结果中
             */
            for (int i = 31; i >=0; i--) {
                int path = ((num >> i) & 1);
                int best = i == 31 ? path : (path ^ 1);
                best = cur.nexts[best] != null ? best : (best ^ 1);
                res |= ((best ^ path) << i);
                cur = cur.nexts[best];
            }
            return res;
        }

    }

    /**
     * 第二种解法 前缀树
     * @param arr
     * @return
     */
    public  int getMaxEorSum2(int[] arr){
        if (arr == null || arr.length == 0) return 0;
        //初始化前缀树
        Tree tree = new Tree();
        tree.add(0);
        int max = Integer.MIN_VALUE;
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i]; //0到i的异或累加和
            max = Math.max(max, tree.maxXor(eor)); //从前缀树中选取一个数使得与eor异或得出最大的值
            tree.add(eor); //把0到i的异或累加和添加到前缀树中
        }
        return max;
    }
}
