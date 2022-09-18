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

    private static void print(int n, int curr, boolean down) {
        if (curr > n) return;
        print(n, curr + 1, true);
        System.out.println(down ? "down" : "up");
        print(n, curr + 1, false);
    }

}
