package _04LeetCode精选TOP面试题;

import java.util.HashMap;

/**
 * Created by huangjunyi on 2022/11/2.
 */
public class _079LRU缓存 {
    class LRUCache {

        class Node {
            int value;
            int key;
            Node last;
            Node next;

            public Node(int key, int value) {
                this.value = value;
                this.key = key;
            }
        }

        class NodeList {
            Node head;
            Node tail;
            int size;

            public int size() {
                return size;
            }

            public NodeList() {
                head = null;
                tail = null;
            }

            public void add(Node node) {
                if (head == null) {
                    head = node;
                    tail = node;
                } else {
                    tail.next = node;
                    node.last = tail;
                    tail = node;
                }
                size++;
            }

            public Node removeHead() {
                if (head == null) return null;
                Node node = head;
                if (head == tail) {
                    head = null;
                    tail = null;
                } else {
                    head = head.next;
                    head.last = null;
                }
                size--;
                return node;
            }

            public void moveToTail(Node node) {
                if (node == null) return;
                if (tail == node) return;
                if (head == node) {
                    head = head.next;
                    head.last = null;
                } else {
                    node.last.next = node.next;
                    node.next.last = node.last;
                }
                tail.next = node;
                node.last = tail;
                node.next = null;
                tail = node;
            }

        }

        int capacity;
        HashMap<Integer, Node> map;
        NodeList nodeList;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>();
            nodeList = new NodeList();
        }

        public int get(int key) {
            if (!map.containsKey(key)) return -1;
            Node node = map.get(key);
            nodeList.moveToTail(node);
            return node.value;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                node.value = value;
                nodeList.moveToTail(node);
            } else {
                if (nodeList.size == capacity) {
                    Node node = nodeList.removeHead();
                    map.remove(node.key);
                }
                Node node = new Node(key, value);
                map.put(key, node);
                nodeList.add(node);
            }
        }
    }
}
