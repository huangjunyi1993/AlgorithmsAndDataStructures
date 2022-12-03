package _02进阶._23Morris遍历;

/**
 * Morris遍历求一棵树离头节点最近的叶节点的高度
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
        int curLevel = 0; // 当前节点层数
        while (cur != null) {
            mostRight = cur.left;
            leftRightBoardsize = 1;
            if (mostRight != null) {
                //当前节点有左树
                while (mostRight.right != null && mostRight.right != cur) {
                    // 寻找当前节点的左树的最右节点的同时记录左树右边界的节点数
                    mostRight = mostRight.right;
                    leftRightBoardsize++;
                }
                if (mostRight.right == null) {
                    // 进入这里，表示第一次来到当前节点
                    // 把左树最右节点的右指针修改为指向当前节点，cur指针往左节点移动
                    mostRight.right = cur;
                    curLevel++;
                    cur = cur.left;
                    continue;
                } else {
                    // 进入这里，表示第二次来到当前节点
                    // 看看当前节点的左子树的最右节点的左节点是否为空，为空代表它是叶子节点
                    // 发现左树最右节点是子节点，清算一下，更新minHigh
                    if (mostRight.left == null) {
                        minHigh = Math.min(minHigh, curLevel);
                    }
                    // curLevel当前是左树最右节点的高度，减去leftRightBoardsize左树右边界节点数，得出当前节点高度
                    curLevel -= leftRightBoardsize;
                    // 恢复当前节点的左子树的最右节点的右指针
                    mostRight.right = null;
                }
            } else {
                //当前节点没有左树，单纯层数++
                curLevel++;
            }
            cur = cur.right;
        }
        return minHigh;
    }

}
