package _03经典面试题目.未分类;

/**
 * 括号有效配对是指:
 * 1)任何一个左括号都能找到和其正确配对的右括号
 * 2)任何一个右括号都能找到和其正确配对的左括号
 * 有效的: () (()) ()() (()()) 等
 * 无效的: () )(等
 * 问题一:怎么判断一个括号字符串有效?
 * 问题二:如果一个括号字符串无效，返回至少填几个字符能让其整体有效
 * 问题三: 最大嵌套层数（这里的括号字符串是有效的）
 * Created by huangjunyi on 2022/9/17.
 */
public class _01KuoHaoProblem {

    /**
     * 问题一
     * @param str
     * @return
     */
    public static boolean valid(String str) {
        char[] chars = str.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                count++;
            } else {
                //count == 0，并且当前是)，说明该字符串从0~i上多出一个)无法匹配，则无论如何不合法
                if (count == 0) return false;
                count--;
            }
        }
        //可以遍历完，说明没有前缀是多出)无法匹配的，则count==0就是合法，否则就是多出(
        return count == 0;
    }

    /**
     * 问题二
     * @param str
     * @return
     */
    public static int needParenteses(String str) {
        char[] chars = str.toCharArray();
        int count = 0;
        int need = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                count++;
            } else {
                //count == 0，并且当前是)，说明该字符串从0~i上多出一个)无法匹配，则需要一个(与之匹配，need加1
                if (count == 0) need++;
                else count--;
            }
        }
        //遍历完后，如果count不为零，则有count个(没有匹配
        return need + count;
    }

    /**
     * 问题三
     * @param str
     * @return
     */
    public static int deep(String str) {
        char[] chars = str.toCharArray();
        int count = 0;
        int max = 0;
        for (int i = 0; i < chars.length; i++) {
            /*
            因为括号串默认是有效的，所以遇到(就++，遇到)就--，
            count达到的最大值，就是最大嵌套层数
             */
            if (chars[i] == '(') {
                count++;
                max = Math.max(max, count);
            } else {
                count--;
            }
        }
        return count;
    }

}
