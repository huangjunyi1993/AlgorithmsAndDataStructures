package _01基础._10二叉树递归套路;

/**
 * 给定一颗二叉树，判断其是否是完全二叉树
 *
 * 四种情况：
 * 1.整棵树是满二叉树
 * 2.左子树是完全二叉树，右子树是满二叉树，左子树比右子树高度高1
 * 3.左子树是满二叉树，右子树是满二叉树，左子树比右子树高度高1
 * 4.左子树是满二叉树，右子树是完全二叉树，左右子树高度相同
 */
public class TreeRecursion07 {

    public static boolean isCBT(Node head) {
        Info info = process(head);
        return info.isCBT;
    }

    private static Info process(Node node) {

        // base caes：空树，是满二叉树，是完全二叉树，树高0
        if (node == null) {
            Info info = new Info();
            info.isCBT = true;
            info.isFull = true;
            info.height = 0;
            return info;
        }

        // 从左右子树收集信息
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        // 根据从左右子树收集回来的信息，计算height和isFull
        Info info = new Info();
        info.height = leftInfo.height + 1 + rightInfo.height;
        info.isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;

        // 根据从左右子树收集回来的信息，计算isCBT
        if (info.isFull) {
            info.isCBT = true; //满足条件1
        } else {
            if (leftInfo.isCBT && rightInfo.isCBT) {

                if ((leftInfo.isCBT && rightInfo.isFull && leftInfo.height - rightInfo.height == 1) //满足条件2
                        || (leftInfo.isFull && rightInfo.isFull && leftInfo.height - rightInfo.height == 1) //满足条件3
                        || (leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height)) { //满足条件4
                    info.isCBT = true;
                }

            }
        }

        return info;
    }

    private static class Info {
        private boolean isFull; //当前子树是否是满二叉树
        private boolean isCBT; //当前子树是否是完全二叉树
        private int height; //当前子树高度
    }

    private static class Node {
        private int value;
        private Node left;
        private Node right;
    }

}
