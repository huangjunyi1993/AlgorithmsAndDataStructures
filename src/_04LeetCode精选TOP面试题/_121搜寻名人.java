package _04LeetCode精选TOP面试题;

/**
 * 寻找明星
 * 明星是所有的人都认识他，但是他不认识任何人
 * 有可能没有明星
 *
 * 给定一个整形n，表示0~n-1号人
 *
 * 给定一个 boolean knows(int x, int i) 函数
 * 返回x是否认识i，认识返回true，不认识返回false
 *
 * 判断n号人中是否有明星，有返回编号，没有返回-1
 *
 * Created by huangjunyi on 2022/11/6.
 */
public class _121搜寻名人 {

    // 提交时不要提交这个函数，只提交下面的方法
    public static boolean knows(int x, int i) {
        return true;
    }

    public int findCelebrity(int n) {

        // 第一个for抓住有可能为明星的人
        int cand = 0;
        for (int i = 1; i < n; i++) {
            if (knows(cand, i)) {
                cand = i;
            }
        }

        // 第二个for循环，看明星编号cand，前面是否有他认识的人，有则表示他不是明星，返回false
        for (int i = 0; i < cand; i++) {
            if (knows(cand, i)) {
                return -1;
            }
        }

        // 第三个for循环，看是否所有人的都认识他1
        for (int i = 0; i < n; i++) {
            if (!knows(i, cand)) {
                return -1;
            }
        }

        return cand;
    }

}
