package _03经典面试题目.数据结构设计;

import java.util.HashMap;
import java.util.Map;

/**
 * 一种消息接收并打印的结构设计,已知一个消息流会不断地吐出整数 1~N，但不一定按照顺序吐出。
 * 如果上次打印的数为 i， 那么当 i+1 出现时，请打印 i+1 及其之后接收过的并且连续的所有数，
 * 直到 1~N 全部接收 并打印完，请设计这种接收并打印的结构。
 * 初始时默认i==0
 *
 * Created by huangjunyi on 2022/10/4.
 */
public class _02MessageBox {

    private static class Node {
        String info;
        Node next;

        public Node(String info) {
            this.info = info;
        }
    }

    private static class MessageBox {

        private Map<Integer, Node> headMap;
        private Map<Integer, Node> tailMap;
        private int waitSeq;

        public MessageBox() {
            headMap = new HashMap<>();
            tailMap = new HashMap<>();
            waitSeq = 1;
        }

        public void received(int seq, String info) {

            /*
            先单独把新到的信息封装为node，单独放入headMap和tailMap
            然后在看tailMap是否有seq-1的节点
            headMap是否有seq+1节点
            把链表串联好
            修正headMap和tailMap

            如果seq正好是waitSeq，则可以打印信息
             */

            Node node = new Node(info);
            headMap.put(seq, node);
            headMap.put(seq, node);

            if (tailMap.containsKey(seq - 1)) {
                tailMap.get(seq - 1).next = node;
                tailMap.remove(seq - 1);
                headMap.remove(seq);
            }

            if (headMap.containsKey(seq + 1)) {
                node.next = headMap.get(seq + 1);
                headMap.remove(seq + 1);
                tailMap.remove(seq);
            }

            if (seq == waitSeq) print();

        }

        private void print() {
            /*
                连续打印信息，直到在遇到不连续的节点
                同时更新waitSeq
             */

            Node node = headMap.get(waitSeq);
            headMap.remove(waitSeq);
            while (node != null) {
                System.out.println(node.info);
                node = node.next;
                waitSeq++;
            }
            tailMap.remove(waitSeq - 1);
            System.out.println();
        }

    }

}
