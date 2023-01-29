package _05面试真题;

/**
 * 来自美团
 * () 分值为2
 * (()) 分值为3
 * ((())) 分值为4
 * 也就是说，每包裹一层，分数就是里面的分值+1
 * ()() 分值为2 * 2
 * (())() 分值为3 * 2
 * 也就是说，每连接一段，分数就是各部分相乘，以下是一个结合起来的例子
 * (()())()(()) -> (2 * 2 + 1) * 2 * 3 -> 30
 * 给定一个括号字符串str，已知str一定是正确的括号结合，不会有违规嵌套
 * 返回分数
 * Created by huangjunyi on 2023/1/18.
 */
public class Code14_ComputeExpressionValue {

    public static int sores(String s) {
        return compute(s.toCharArray(), 0)[0];
    }

    /**
     * 从表达式exp的index位置开始（exp[index...]），遇到右括号“)”就停，返回分数和计算到的位置，[分数, 计算到的位置]
     * @param exp 表达式
     * @param index 从表达式的index位置开始计算
     * @return [分数, 计算到的位置]
     */
    public static int[] compute(char[] exp, int index) {
        if (exp[index] == ')') return new int[]{1, index};
        int res = 1;
        while (index < exp.length && exp[index] != ')') {
            int[] subRes = compute(exp, index + 1);
            res *= subRes[0] + 1;
            index = subRes[1] + 1;
        }
        return new int[] {res, index};
    }

    public static void main(String[] args) {

        String str1 = "(()())()(())";
        System.out.println(sores(str1));

        // (()()) + (((()))) + ((())())
        // (()()) -> 2 * 2 + 1 -> 5
        // (((()))) -> 5
        // ((())()) -> ((2 + 1) * 2) + 1 -> 7
        // 所以下面的结果应该是175
        String str2 = "(()())(((())))((())())";
        System.out.println(sores(str2));

        // (()()()) + (()(()))
        // (()()()) -> 2 * 2 * 2 + 1 -> 9
        // (()(())) -> 2 * 3 + 1 -> 7
        // 所以下面的结果应该是63
        String str3 = "(()()())(()(()))";
        System.out.println(sores(str3));
    }

}
