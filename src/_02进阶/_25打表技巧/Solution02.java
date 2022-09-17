package _02进阶._25打表技巧;

/**
 * 给定一个正整数N，表示有N份青草统一堆放在仓库里。
 * 有一只牛和一只羊，牛先吃，羊后吃，它俩轮流吃草。
 * 不管是牛还是羊，每一轮能吃的草量必须是：1，4，16，64…(4的某次方)。
 * 谁最先把草吃完，谁获胜。
 * 假设牛和羊都绝顶聪明，都想赢，都会做出理性的决定。
 * 根据唯一的参数N，返回谁会赢。
 * Created by huangjunyi on 2022/9/11.
 */
public class Solution02 {

    public static String winner1(int n) {
        /*
        后 先 后 先 先
        0  1  2  3  4
         */
        if (n < 5) {
           return n ==0 || n == 2 ? "后手" : "先手";
        }
        int base = 1;
        while (base <= n) {
            //n减去base，再递归调winner函数，如果返回后手赢，则当前函数返回先手赢，因为吃掉base份草后，当前玩家变后手
            if (winner1(n - base).equals("后手")) return "先手";
            if (base > n / 4) break;
            base *= 4;
        }
        return "后手";
    }

    /**
     后手
     先手
     后手
     先手
     先手

     后手
     先手
     后手
     先手
     先手
     * @param n
     * @return
     */
    public static String winner2(int n) {
        return (n % 5 == 0 || n % 5 == 2) ? "后手" : "先手";
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(winner1(i));
        }
    }

}
