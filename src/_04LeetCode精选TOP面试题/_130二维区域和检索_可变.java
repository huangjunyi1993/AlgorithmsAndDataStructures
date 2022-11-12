package _04LeetCode精选TOP面试题;

/**
 * 实现二维矩阵更新和区间范围查询都是O(logN)的数据结构
 * Created by huangjunyi on 2022/11/9.
 */
public class _130二维区域和检索_可变 {

    class RangeSumQuery2DMutable {
        int[][] tree;
        int[][] nums;
        int N;
        int M;

        public RangeSumQuery2DMutable(int[][] matrix) {
            this.N = matrix.length;
            this.M = matrix[0].length;
            tree = new int[this.N + 1][this.M + 1];
            nums = new int[this.N][this.M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    update(i, j, matrix[i][j]);
                }
            }
        }

        /**
         * 二维矩阵前缀累加和
         * @param row
         * @param col
         * @return
         */
        private int sum(int row, int col) {
            int res = 0;
            for (int i = row + 1; i > 0; i -= i & -i) {
                for (int j = col + 1; j > 0; j -= j & -j) {
                    res += tree[i][j];
                }
            }
            return res;
        }

        /**
         * 二维矩阵更新
         * @param row
         * @param col
         * @param val
         */
        public void update(int row, int col, int val) {
            int add = val - nums[row][col];
            nums[row][col] = val;
            for (int i = row + 1; i < N; i += i & -i) {
                for (int j = col + 1; j < M; j += j & -j) {
                    tree[i][j] += add;
                }
            }
        }

        /**
         * 二维矩阵区间范围查询
         * @param row1 左上方行数
         * @param col1 左上方列数
         * @param row2 右下方行数
         * @param col2 右下方列数
         * @return
         */
        public int sumRegion(int row1, int col1, int row2, int col2) {
            return sum(row2, col2) + sum(row1 - 1, col1 - 1) - sum(row1 - 1, col2) - sum(row2, col1 - 1);
        }

    }

    /**
     * 线段树，一位数组更新和区间范围查询都是O(logN)的数据结构
     *
     * 原理：
     * 0位置不用，从1位置开始
     * 奇数位置只存自己的值
     * 偶数位则有讲究
     * 2 -> 1~2
     * 4 -> 1~4
     * 6 -> 5~6
     * 8 -> 1~8
     * 间隔大的覆盖间隔小的，
     * 例如1~4,5~8为2个4的间隔，1~8是1个8的间隔，那么8位置存1~8的值，因为8间隔更大
     * 16间隔，32间隔也是如此，在一个位置上凑齐了一个间隔，就存这个间隔间的数的和，凑齐多个间隔去较大的
     * 存储计算：
     * 取该位置的二进制的值a，去掉最右侧1后得b，累加b~a的值，就是该位置要存的值
     * 前缀和查询计算：
     * 取该位置的二进制的值a，去掉最右侧1后得b，累加b~a的值
     * 然后b去掉最右侧的1，得c，累加c~b的值，依次类推，直至为0，累加的值就是该位置的前缀和
     * 区间范围查询：
     * 两个前缀和相减
     * 更新计算：
     * 目标位置为a，那么更新a位置，然后a加一个自己最右侧的1，得b，更新b位置的值，然后b也如此计算，更新，直至越界
     */
    class IndexTree {
        int[] tree;
        int N;

        public IndexTree(int size) {
            this.N = size;
            this.tree = new int[N + 1];
        }

        public int sum(int index) {
            int res = 0;
            while (index > 0) {
                res += tree[index];
                index -= index & -index;
            }
            return res;
        }

        public void add(int index, int d) {
            while (index <=  N) {
                tree[index] += d;
                index += index & - index;
            }
        }

    }

}
