package _02进阶._22Manacher算法;

/**
 * 给定一个字符串，求出它的最长回文子串，利用Manacher算法求解
 *
 * Manacher算法：快速生成回文半径数组
 * Created by huangjunyi on 2022/9/10.
 */
public class Manacher01 {

    public static int getMaxSubPalindromeString(String str) {
        if (str == null || str.length() == 0) return 0;
        int r = -1; // 最大回文右边界，但是这里代表的时扩失败的位置
        int c = -1; // 最大回文中心点
        /*
        添加代表虚轴的分割符#，返回处理串
         */
        char[] chars = addSeparator(str);
        // 回文半径数组
        int[] rArr = new int[chars.length];
        // 最大回文半径
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < chars.length; i++) {
            /*
            i`的回文区域是否在L~R内，如果是i的回文半径取i`的值
            否则i的回文半径取i~R的长度
            rArr[2 * c - i]取得i`的回文半径
             */
            rArr[i] = r > i ? Math.min(rArr[2 * c - i], r - i) : 1;
            // 如果i`的回文区域与L压线，则i还可以尝试扩
            while (i + rArr[i] < chars.length && i - rArr[i] > -1) {
                if (chars[i + rArr[i]] == chars[i - rArr[i]]) rArr[i]++;
                else break;
            }
            if (i + rArr[i] > r) {
                r = i + rArr[i]; // 更新最大回文右边界
                c = i; // 更新最大回文中心点
            }
            max = Math.max(max, rArr[i]);
        }
        // 最大的回文半径-1，就是原串中最长回文串长度
        return max - 1;
    }

    private static char[] addSeparator(String str) {
        char[] chars = str.toCharArray();
        char[] newChars = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i < newChars.length; i++) {
            newChars[i] = (i & 1) == 0 ? '#' : chars[index++];
        }
        return newChars;
    }

    public static void main(String[] args) {
        System.out.println(getMaxSubPalindromeString("abcaaaaaacbasss"));
    }

}
