package _02进阶._23Morris遍历;

/**
 * Morris遍历求一棵树离头结点最近的叶结点的高度
 * Created by huangjunyi on 2022/9/10.
 */
public class Morris03 {

    private static class Node {
        Node left;
        Node right;
        int value;
    }

    public static int getMinHigh(Node head) {
        if (head == null) return 0;
        Node cur = head;
        Node mostRight = null;
        int minHigh = Integer.MAX_VALUE;
        int leftRightBoardsize = -1;
        int curLevel = 0;
        while (cur != null) {
            mostRight = cur.left;
            leftRightBoardsize = 1;
            if (mostRight != null) {
                //当前结点有左树
                while (mostRight.right != null && mostRight.right != cur) {
                    //寻找当前结点的左树的最右结点的同时记录左树右边界的结点数
                    mostRight = mostRight.right;
                    leftRightBoardsize++;
                }
                if (mostRight.right == null) {
                    //把左树最右结点的右指针修改为指向当前结点，cur指针往左节点移动
                    mostRight.right = cur;
                    curLevel++;
                    cur = cur.left;
                    continue;
                } else {
                    //发现左树最右结点是子节点，清算一下，更新minHigh
                    if (mostRight.left == null) {
                        minHigh = Math.min(minHigh, curLevel);
                    }
                    //curLevel当前是左树最右结点的高度，减去leftRightBoardsize左树右边界结点数，得出当前结点高度
                    curLevel -= leftRightBoardsize;
                    mostRight.right = null;
                }
            } else {
                //当前结点没有左树
                curLevel++;
            }
            cur = cur.right;
        }
        return minHigh;
    }

}
