package _02进阶._35TSP;

/**
 * https://leetcode.cn/problems/can-i-win/description/
 * 在 "100 game" 这个游戏中，两名玩家轮流选择从 1 到 10 的任意整数，累计整数和，先使得累计整数和 达到或超过  100 的玩家，即为胜者。
 * 如果我们将游戏规则改为 “玩家 不能 重复使用整数” 呢？
 * 例如，两个玩家可以轮流从公共整数池中抽取从 1 到 15 的整数（不放回），直到累计整数和 >= 100。
 * 给定两个整数 maxChoosableInteger （整数池中可选择的最大数）和 desiredTotal（累计和），
 * 若先出手的玩家是否能稳赢则返回 true ，否则返回 false 。假设两位玩家游戏时都表现 最佳 。
 * Created by huangjunyi on 2022/12/10.
 */
public class _00CanIWin {
    class Solution1 {
        public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
            // 清洗无效参数
            if (desiredTotal == 0) {
                return true;
            }
            if ((maxChoosableInteger * (maxChoosableInteger + 1) >> 1) < desiredTotal) {
                return false;
            }
            // 初始化一个状态数组，status[i]表示的时第i号数字有没有被拿走
            // 拿走则为-1，没拿走就是i
            int[] status = new int[maxChoosableInteger + 1];
            for (int i = 1; i <= maxChoosableInteger; i++) {
                status[i] = i;
            }
            return process(status, desiredTotal);
        }

        /**
         * 当前还剩没拿的数记在status中，剩余rest要扣，当前的先手如果赢了，返回true，否则返回false
         * @param status 当前还剩没拿的数记在status中
         * @param rest 剩余rest要扣
         * @return 先手返回true，后手返回false
         */
        private boolean process(int[] status, int rest) {
            // 当前先手面对0或者负数，先手输了
            if (rest <= 0) return false;
            for (int i = 0; i < status.length; i++) {
                if (status[i] != -1) {
                    int num = status[i];
                    status[i] = -1;
                    // 接下来的先手，就是此时的后手，此时的后手输了，那么先手就赢了
                    if (!process(status, rest - num)) {
                        return true;
                    }
                    // 恢复现场
                    status[i] = num;
                }
            }
            return false;
        }
    }

    class Solution2 {
        public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
            // 清洗无效参数
            if (desiredTotal == 0) {
                return true;
            }
            if ((maxChoosableInteger * (maxChoosableInteger + 1) >> 1) < desiredTotal) {
                return false;
            }
            // 现在不用状态数组了，改用一个整形，第i表示的时第i号数字有没有被拿走
            // 拿走是1，没拿走是0
            int status = 0;
            return process(maxChoosableInteger, status, desiredTotal);
        }

        /**
         * 当前还剩没拿的数记在status中，剩余rest要扣，当前的先手如果赢了，返回true，否则返回false
         * @param choose 可以拿的数的范围 1~choose
         * @param status 当前还剩没拿的数记在status中
         * @param rest 剩余rest要扣
         * @return 先手返回true，后手返回false
         */
        private boolean process(int choose, int status, int rest) {
            // 当前先手面对0或者负数，先手输了
            if (rest <= 0) return false;
            for (int i = 1; i <= choose; i++) {
                if ((status & 1 << i) == 0) {
                    // 接下来的先手，就是此时的后手，此时的后手输了，那么先手就赢了
                    if (!process(choose, status | 1 << i, rest - i)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    class Solution3 {
        public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
            // 清洗无效参数
            if (desiredTotal == 0) {
                return true;
            }
            if ((maxChoosableInteger * (maxChoosableInteger + 1) >> 1) < desiredTotal) {
                return false;
            }
            // 继续优化，那一个dp表，记录status对应的答案，如果下次递归遇到相同的status，直接拿值
            // dp[status] status状态下，如果1表示先手赢，-1后手赢，0 没求过
            int status = 0;
            int[] dp = new int[1 << (maxChoosableInteger + 1)];
            return process(maxChoosableInteger, status, desiredTotal, dp);
        }

        /**
         * 当前还剩没拿的数记在status中，剩余rest要扣，当前的先手如果赢了，返回true，否则返回false
         * @param choose 可以拿的数的范围 1~choose
         * @param status 当前还剩没拿的数记在status中
         * @param rest 剩余rest要扣
         * @param dp 缓存表，dp[status] status状态对应的答案 != 0 表示以前已经求过了
         * @return 先手返回true，后手返回false
         */
        private boolean process(int choose, int status, int rest, int[] dp) {
            if (dp[status] != 0) {
                return dp[status] == 1;
            }
            // 当前先手面对0或者负数，先手输了
            if (rest <= 0) {
                dp[status] = -1;
                return false;
            }
            for (int i = 1; i <= choose; i++) {
                if ((status & 1 << i) == 0) {
                    // 接下来的先手，就是此时的后手，此时的后手输了，那么先手就赢了
                    if (!process(choose, status | 1 << i, rest - i, dp)) {
                        dp[status] = 1;
                        return true;
                    }
                }
            }
            dp[status] = -1;
            return false;
        }
    }
}
