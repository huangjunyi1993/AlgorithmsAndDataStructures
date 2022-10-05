package _03经典面试题目.动态规划;

/**
 * 给定一个正数N，表示你在纸上写下1~N所有的数字,返回在书写的过程中，一共写下了多少个1
 *
 * Created by huangjunyi on 2022/10/4.
 */
public class _03OneNum {

    public static int oneNum(int num) {
        // num小于0，不能写下任何1
        if (num < 1) return 0;
        // num长度
        int len = getLen(num);
        // num长度为1，只能下一个1
        if (len == 1) return 1;
        // 假如num=16325，tmp=10000
        int tmp = getTmp(len - 1);
        // 6326~16325，最高位的数
        int first = num / tmp;
        // 6326~16325，最高位填1，有几种情况
        int firstOneNum = first == 1 ? num % tmp + 1 : tmp;
        // 6326~16325，其他位为1，有几种情况
        int otherOneNum = first * (tmp / 10) * (len - 1);
        // 结果值 + 递归6325的结果
        return firstOneNum + otherOneNum + oneNum(num % tmp);
    }

    private static int getTmp(int len) {
        int tmp = 1;
        while (len != 0) {
            tmp *= 10;
            len--;
        }
        return tmp;
    }

    private static int getLen(int num) {
        int len = 0;
        while (num != 0) {
            num /= 10;
            len++;
        }
        return len;
    }

}
