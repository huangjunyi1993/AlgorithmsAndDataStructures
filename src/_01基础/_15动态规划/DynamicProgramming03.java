package _01基础._15动态规划;

/**
 * 给定一个只由数字组成的字符串，1对应A，2对应B...
 * 将字符串的数据转化为字母，如11，可以转化为AA，也可以转化为K
 * 问：有多少种转化结果
 */
public class DynamicProgramming03 {

    public static int getThransferCountByRecursive(String str) {
        char[] chars = str.toCharArray();
        return process(chars, 0);
    }

    /**
     * 暴力递归
     * @param chars 数字字符数组
     * @param i 当前位置
     * @return
     */
    private static int process(char[] chars, int i) {
        if (i == chars.length) return 1;
        int res = 0;
        if (chars[i] == '0') return res;
        res += process(chars, i + 1);
        //当前数字位1，则有两个分支：1.只把当前数字转换为字母，2.把当前数字和下一位合在一起转换为字母
        if (chars[i] == '1') {
            if (i + 1 < chars.length) {
                res += process(chars, i + 2);
            }
        }
        //当前数字位2，只有在下一位数字是0~6之间，才有两个分支
        if (chars[i] == '2') {
            if (i + 1 < chars.length && chars[i + 1] >='0' && chars[i + 1] <= '6') {
                res += process(chars, i + 2);
            }
        }
        //返回累加结果
        return res;
    }

    /**
     * 动态规划
     * @param str
     * @return
     */
    private static int getThransferCountByDp(String str) {
        char[] chars = str.toCharArray();
        int[] dp = new int[chars.length + 1];
        dp[chars.length] = 1;
        for (int i = chars.length - 1; i >= 0; i--) {
            int res = 0;
            if (chars[i] == '0') {
                dp[i] = 0;
                continue;
            }
            res += dp[i + 1];
            //当前数字位1，则有两个分支：1.只把当前数字转换为字母，2.把当前数字和下一位合在一起转换为字母
            if (chars[i] == '1') {
                if (i + 1 < chars.length) {
                    res += dp[i + 2];
                }
            }
            //当前数字位2，只有在下一位数字是0~6之间，才有两个分支
            if (chars[i] == '2') {
                if (i + 1 < chars.length && chars[i + 1] >='0' && chars[i + 1] <= '6') {
                    res += dp[i + 2];
                }
            }
            dp[i] = res;
        }
        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(getThransferCountByRecursive("11111"));
        System.out.println(getThransferCountByDp("11111"));
    }

}
