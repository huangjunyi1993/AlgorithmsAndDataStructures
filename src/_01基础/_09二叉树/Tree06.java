package _01基础._09二叉树;

/**
 * 纸条对折N次，然后打印折痕的方向
 * 拿一张纸条，对折N次，然后从上往下，打印折痕的方向
 * 折痕向下，打印down，折痕向上，打印up
 */
public class Tree06 {

    public static void print(int n) {
        print(n, 1, true);
    }

    /**
     * 二叉树中序遍历的方式打印折痕
     * @param n 总共层数（对折次数)
     * @param curr 当前层数
     * @param down 是否是凹折痕
     */
    private static void print(int n, int curr, boolean down) {
        if (curr > n) return;
        print(n, curr + 1, true);
        System.out.println(down ? "down" : "up");
        print(n, curr + 1, false);
    }

}
