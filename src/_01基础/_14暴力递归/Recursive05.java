package _01基础._14暴力递归;

/**
 * 给定一个只由数字组成的字符串，1对应A，2对应B...
 * 将字符串的数据转化为字母，如11，可以转化为AA，也可以转化为K
 * 问：有多少种转化结果
 * Created by huangjunyi on 2022/9/2.
 */
public class Recursive05 {

    public static int getThransferCount(String str) {
        char[] chars = str.toCharArray();
        return process(chars, 0);
    }

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

}
