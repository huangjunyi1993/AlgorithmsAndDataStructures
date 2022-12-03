package _02进阶._20KMP算法;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定两颗二叉树，t1和t2，判断在t1中是否包含和t2结构相同的子树
 * 注意：子树是指以某个节点为头，往下包含了所有的子节点，而不是一部分
 *
 * 解法：
 * 1、把两个树进行先序序列化
 * 2、转化为KMP匹配问题
 *
 * Created by huangjunyi on 2022/9/5.
 */
public class KMP03 {

    private static class Node {
        String value;
        Node left;
        Node right;
    }

    public static boolean contains(Node head1, Node head2) {
        if (head2 == null) return true;
        if (head1 == null) return false;

        List<String> list1 = preSerial(head1); // 先序序列化树1
        List<String> list2 = preSerial(head2); // 先序序列化树2
        String[] arr = list1.toArray(new String[0]); // 转为字符串数组，把数组看成字符串
        String[] match = list2.toArray(new String[0]); // 匹配串
        if (arr == null || match == null || arr.length < 1 || arr.length < match.length) return false;

        int[] next = getNext(match); // 生成next数组
        int x = 0; // 字符原串指针
        int y = 0; // 匹配串指针

        // KMP的匹配流程
        while (x < arr.length && y < match.length) {
            if (equals(arr[x], match[y])) {
                x++;
                y++;
            } else if (next[y] != -1) {
                y = next[y];
            } else {
                x++;
            }
        }

        // 如果匹配串指针推到了越界位置，匹配成功，表示str2是str1的旋转数
        return y == match.length;
    }

    private static int[] getNext(String[] match) {
        if (match.length == 1) return new int[]{-1};
        int[] next = new int[match.length];
        next[0] = -1;
        next[1] = 0;
        int cn = 0;
        int i = 2;
        while (i < match.length) {
            if (equals(match[cn], match[i - 1])) {
                next[i++] = ++cn;
            } else if (next[cn] != -1) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return new int[0];
    }

    private static boolean equals(String str1, String str2) {
        if (str1 == null && str2 == null) return true;
        else if (str1 == null || str2 == null) return false;
        else return str1.equals(str2);
    }

    private static List<String> preSerial(Node node) {
        List<String> res = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            res.add(pop == null ? null : pop.value);
            if (pop != null) {
                stack.push(pop.right);
                stack.push(pop.left);
            }
        }
        return res;
    }


}
