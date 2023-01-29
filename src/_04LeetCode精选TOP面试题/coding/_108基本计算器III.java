package _04LeetCode精选TOP面试题.coding;

/**
 * 带括号的表达式
 *
 * 思路：
 *
 * 一个f函数，接收一个表达式，一个起始位置，表示从该位置往下算
 * 遇到右括号，或者表达式遍历完，则终止
 * 返回计算结果，和算到的位置
 *
 * 一开始，主函数调f(exp, 0)
 *
 * f函数，表达式中遇到(，就递归掉f函数
 *
 * f函数返回，上层的f函数拿到返回值，接着返回的位置往下算
 *
 * Created by huangjunyi on 2022/11/6.
 */
public class _108基本计算器III {
    public static int calculate(String str) {
        return 0;
    }
}
