package _01基础._09二叉树;

import java.util.Stack;

/**
 * 不使用递归，实现二叉树的前序、中序、后续遍历
 */
public class Tree01 {

    /**
     * 二叉树非递归前序遍历
     * 1、用一个栈存放遍历到的结点
     * 2、每次弹出栈顶元素，并且弹出就打印
     * 3、如果有右结点，先压入右节点
     * 4、如果有左结点，再压入左结点
     * @param head
     */
    public static void pre(Node head) {
        if (head == null) return;

        Stack<Node> stack = new Stack<>();
        stack.push(head);

        while (!stack.isEmpty()) {
            Node pop = stack.pop(); //弹出就打印
            System.out.println(pop.value);

            if (pop.right != null) stack.push(pop.right); //先压入右
            if (pop.left != null) stack.push(pop.left); //再压入左
        }
    }

    /**
     * 二叉树非递归中序遍历
     * 1、用一个栈存放遍历到的结点
     * 2、先递归左子树，压入沿途经过的结点
     * 3、左子树遍历完，弹出栈顶元素打印
     * 4、取弹出结点的右结点，回到一，继续往左深度遍历
     * @param head
     */
    public static void in(Node head) {
        if (head == null) return;

        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                //深度遍历左子树
                stack.push(head);
                head = head.left;
            } else {
                //左子树遍历完，弹出栈顶元素打印
                Node pop = stack.pop();
                System.out.println(pop.value);
                //取弹出元素的右结点，继续下一轮遍历
                head = pop.right;
            }
        }
    }

    /**
     * 二叉树非递归实现后续遍历
     * 1、两个栈
     * 2、先压入头结点到栈1，然后遍历
     * 3、遍历过程中弹出栈顶元素，压入栈2
     * 4、从栈1弹出的元素，先压入它的左节点到栈1，再压入右结点到栈1
     * 5、下一轮遍历
     *
     * 6、上面遍历完后，栈1为空，此时栈2从栈顶到栈底结点顺序就是二叉树后续遍历的顺序，循环弹出栈2元素打印
     * @param head
     */
    public static void post01(Node head) {
        if (head == null) return;

        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();

        stack1.add(head);
        while (!stack1.isEmpty()) {
            Node pop = stack1.pop();
            stack2.push(pop); //从栈1弹出栈顶元素，压入栈2
            if (pop.left != null) stack1.push(pop.left); //先压入左到栈1
            if (pop.right != null) stack1.push(pop.right); //再压入右到栈1
        }

        while (!stack2.isEmpty()) System.out.println(stack2.pop().value);
    }

    /**
     * 二叉树非递归后续遍历：
     * 1、用head指针记录上次处理过的结点
     * 2、一颗子树中，先处理左结点，处理完拿head指针记一下
     * 3、下次遍历通过head指针知道左结点已经处理，处理右节点，处理完head指针记一下
     * 4、最后打印子树的头节点，并且head指针记一下，这颗子树就处理完
     * @param head
     */
    public static void post02(Node head) {
        if (head == null) return;

        Stack<Node> stack = new Stack<>();
        stack.push(head);
        Node help = null;
        while (!stack.isEmpty()) {
            help = stack.peek();
            //左子树没有处理过，处理左子树
            if (help.left != null && help.left != head && help.right != head) stack.push(help.left);
            //右字树没有处理过，处理右子树
            else if (help.right != null && help.right != head) stack.push(help.right);
            else {
                System.out.println(help.value); //左子树和右子树都处理完，打印help结点
                head = help; //head赋值为help，代表上次遍历打印过的结点
            }
        }
    }

    private static class Node {
        private int value;
        private Node left;
        private Node right;
    }

}
