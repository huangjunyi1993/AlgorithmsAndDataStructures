package _01基础._14暴力递归;

/**
 * 汉诺塔问题
 * Created by huangjunyi on 2022/9/1.
 */
public class Recursive01 {

    public static void move(int level, String from, String to, String other) {
        if (level == 1) {
            // 只剩下一层，直接挪过去
            System.out.println(from + " -> " + to);
        } else {
            // 0~n-1 from => other
            move(level - 1, from, other, to);
            // n from => to
            System.out.println(from + " -> " + to);
            // 0~n-1 other => to
            move(level - 1, other, to, from);
        }
    }

}
