package _02进阶._36后缀数组;

/**
 *
 * 给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
 * 求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
 * 说明: 请尽可能地优化你算法的时间和空间复杂度。
 *
 * 示例 1:
 * 输入:
 * nums1 = [3, 4, 6, 5]
 * nums2 = [9, 1, 2, 5, 8, 3]
 * k = 5
 * 输出:
 * [9, 8, 6, 5, 3]
 *
 * 示例 2:
 * 输入:
 * nums1 = [6, 7]
 * nums2 = [6, 0, 4]
 * k = 5
 * 输出:
 * [6, 7, 6, 0, 4]
 *
 * 示例 3:
 * 输入:
 * nums1 = [3, 9]
 * nums2 = [8, 9]
 * k = 3
 * 输出:
 * [9, 8, 9]
 *
 * Created by huangjunyi on 2022/10/22.
 */
public class CreateMaximumNumber {

    public static int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int N1 = nums1.length;
        int N2 = nums2.length;
        if (k < 0 || k > N1 + N2) return null;

        /*
        思路：

        比如k是5，那就是从num1和num2中挑5个数组成最大值
        那么就枚举
        num1挑5个，num2挑0，组成的最大值
        num1挑4个，num2挑1，组成的最大值
        num1挑3个，num2挑2，组成的最大值
        num1挑2个，num2挑3，组成的最大值
        num1挑1个，num2挑4，组成的最大值
        num1挑0个，num2挑5，组成的最大值
        这些最大值中挑最大

        生成一个N*N+1的dp1和dp2表，方便快速挑数
        dp[i][j]表示从i往后挑，挑j个数，挑出的时最大的方案，挑出的开头的数的下标
        那么比如要从num1中挑3个
        第一个数取dp[0][3]，假如得出是2，表示开头为下标2的数
        第二个数取dp[3][2]，假如得出是4，表示第二个数是下标为4的数
        第三个数取dp[5][1]

        然后从num1和num2挑出分表挑出2个数组后，就根据进行合并，
        合并规则是保证原数组中的顺序下，组成的数是最大

        正常的合并方法：
        [3,3,3,9]
        [3,3,3,2]
        合并后：[3,3,3,9,3,3,3,2]
        为了让9尽快出现，需要把第一个数组的3尽快刷完
        所以每次都两个指针分别PK，一样就同时后移，直到分出胜负，取胜方的为取出的第一个数
        比如第一轮比较：大家都是前面3个3，直到第四个数，9比2大，去第一个数组的3
        后面每次比较，都会遍历到9时第一个数组胜出，所以第一个数组会顺利的有序被刷完
        但是这个合并方式不是最优方案

        优化后的合并的方式，是通过后缀数组合并，在后缀数组中排名越大的，合并后越靠前
        因为后缀数组得出的排序结果，可以告诉我们两个下标PK谁赢
         */
        int[] res = new int[k];
        int[][] dp1 = getdp(nums1);
        int[][] dp2 = getdp(nums2);
        // 这里要处理边界条件，因为nums1或者nums2可能不够k个数
        for (int get1 = Math.max(0, k - N2); get1 <= Math.min(k, N1); get1++) {
            int[] pick1 = maxPick(nums1, dp1, get1);
            int[] pick2 = maxPick(nums2, dp2, k - get1);
            int[] merge = mergeBySuffixArray(pick1, pick2);
            res = moreThan(res, merge) ? res : merge;
        }
        return res;
    }

    /**
     * 比较两个方案哪个更大
     * @param pre
     * @param last
     * @return
     */
    public static boolean moreThan(int[] pre, int[] last) {
        int i = 0;
        int j = 0;
        while (i < pre.length && j < last.length && pre[i] == last[j]) {
            i++;
            j++;
        }
        return j == last.length || (i < pre.length && pre[i] > last[j]);
    }

    /**
     * 通过后缀数组进行merge
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] mergeBySuffixArray(int[] nums1, int[] nums2) {
        int size1 = nums1.length;
        int size2 = nums2.length;
        int[] nums = new int[size1 + 1 + size2];
        for (int i = 0; i < size1; i++) {
            // 因为两个数组中间放了个1做隔断，所以每个数都加2，保证隔断比其他的数都小（0 + 2 都 比 1 大）
            nums[i] = nums1[i] + 2;
        }
        // 两个数组中间放一个1做隔断，本来放0，但是0会被用于在生成后缀数组时做边界处理，所以用1
        nums[size1] = 1;
        for (int j = 0; j < size2; j++) {
            // 因为两个数组中间放了个1做隔断，所以每个数都加2，保证隔断比其他的数都小（0 + 2 都 比 1 大）
            nums[j + size1 + 1] = nums2[j] + 2;
        }
        // 通过DC3模板生成后缀数组
        DC3 dc3 = new DC3(nums, 11);
        int[] rank = dc3.rank;
        int[] ans = new int[size1 + size2];
        int i = 0;
        int j = 0;
        int r = 0;
        // 在后缀数组中排名越大的，合并后越靠前
        while (i < size1 && j < size2) {
            ans[r++] = rank[i] > rank[j + size1 + 1] ? nums1[i++] : nums2[j++];
        }
        while (i < size1) {
            ans[r++] = nums1[i++];
        }
        while (j < size2) {
            ans[r++] = nums2[j++];
        }
        return ans;
    }

    /**
     * 生成用于加速挑数的dp表
     * dp[i][j]:
     * 如果arr[i] > arr[dp[i + 1][j]]，dp[i][j] = i;
     * 如果arr[i] < arr[dp[i + 1][j]]，dp[i][j] = dp[i + 1][j];
     * 如果arr[i] == arr[dp[i + 1][j]]，dp[i][j] = i;
     * 相等填i，是因为这里有个小贪心，选了i，后面还能多拿一个和i一样的数，否则挑出的也就是dp[i+1][j]对应的方案，就不是最右方案
     * @param arr
     * @return
     */
    public static int[][] getdp(int[] arr) {
        int size = arr.length; // 0~N-1
        int pick = arr.length + 1; // 1 ~ N
        int[][] dp = new int[size][pick];
        // get 不从0开始，因为拿0个无意义
        // get 1
        for (int get = 1; get < pick; get++) { // 1 ~ N
            int maxIndex = size - get;
            // i~N-1
            for (int i = size - get; i >= 0; i--) {
                if (arr[i] >= arr[maxIndex]) {
                    maxIndex = i;
                }
                dp[i][get] = maxIndex;
            }
        }
        return dp;
    }

    /**
     * 从arr中挑选pick个数组成的最优结果，利用dp加速
     * @param arr
     * @param dp
     * @param pick
     * @return
     */
    public static int[] maxPick(int[] arr, int[][] dp, int pick) {
        int[] res = new int[pick];
        for (int resIndex = 0, dpRow = 0; pick > 0; pick--, resIndex++) {
            res[resIndex] = arr[dp[dpRow][pick]];
            dpRow = dp[dpRow][pick] + 1;
        }
        return res;
    }
}
