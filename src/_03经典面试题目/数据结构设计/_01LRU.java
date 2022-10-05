package _03经典面试题目.数据结构设计;

import java.util.HashMap;

/**
 * LRU内存替换算法
 * Created by huangjunyi on 2022/10/1.
 */
public class _01LRU {

    private static class Node<K, V> {
        private Node last;
        private Node next;
        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private static class DoubleLinkedList<K, V> {

        private Node<K, V> head;
        private Node<K, V> tail;

        public DoubleLinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void add (Node<K, V> node) {
            if (node == null) return;
            if (head == null) head = tail = node;
            else {
                node.last = tail;
                tail.next = node;
                tail = node;
            }
        }

        public void moveToTail(Node<K, V> node) {
            if (node == tail) return;
            else if (node == head) {
                head = node.next;
                head.last = null;
            } else {
                node.last.next = node.next;
                node.next.last = node.last;
            }
            node.last = tail;
            node.next = null;
            tail.next = node;
            tail = node;
        }

        public Node<K, V> removeCurrHead() {
            if (head == null) return null;
            Node res = head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = res.next;
                res.next = null;
                head.last = null;
            }
            return res;
        }

    }

    private static class LRUCache<K, V> {

        private HashMap<K, Node<K, V>> keyNodeMap;
        private DoubleLinkedList<K, V> list;
        private int capacity;

        public LRUCache(int capacity) {
            keyNodeMap = new HashMap<>();
            list = new DoubleLinkedList<>();
            this.capacity = capacity;
        }

        public void put(K key, V value) {
            if (keyNodeMap.containsKey(key)) {
                Node<K, V> node = keyNodeMap.get(key);
                node.value = value;
                list.moveToTail(node);
            } else {
                if (keyNodeMap.size() == capacity) {
                    Node<K, V> node = list.removeCurrHead();
                    if (node != null) keyNodeMap.remove(node.key);
                    Node<K, V> newNode = new Node<>(key, value);
                    keyNodeMap.put(key, newNode);
                    list.add(newNode);
                }
            }
        }

    }
}
