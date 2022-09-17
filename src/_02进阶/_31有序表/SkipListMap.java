package _02进阶._31有序表;

import java.util.ArrayList;
import java.util.List;

/**
 * 跳表
 * Created by huangjunyi on 2022/9/17.
 */
public class SkipListMap<K extends Comparable<K>, V> {

    public static class SkipListNode<K extends Comparable<K>, V> {
        private K key;
        private V value;
        private List<SkipListNode> nexts;

        public SkipListNode(K key, V value) {
            this.key = key;
            this.value = value;
            nexts = new ArrayList<>();
        }

        public boolean isKeyLess(K otherKey) {
            return otherKey != null && (key == null || key.compareTo(otherKey) < 0);
        }

        public boolean isKeyEquals(K otherKey) {
            return (key == null && otherKey == null) || (key != null || otherKey != null && key.compareTo(otherKey) == 0);
        }
    }

    private static final double PROBABILITY = 0.5;
    private int maxLevel;
    private SkipListNode<K, V> head;

    public SkipListMap() {
        head = new SkipListNode<>(null, null);
        head.nexts.add(null);
        maxLevel = 0;
    }

    public void put(K key, V value) {
        if (key == null) return;
        SkipListNode<K, V> pre = findMostRightLessKeyNode(key);
        SkipListNode<K, V> find = pre.nexts.get(0);
        if (find != null && find.isKeyEquals(key)) {
            find.value = value;
        } else {
            SkipListNode<K, V> newNode = new SkipListNode<>(key, value);
            int newNodeLevel = 0;
            while (Math.random() <= PROBABILITY) newNodeLevel++;
            while (maxLevel < newNodeLevel) {
                head.nexts.add(null);
                maxLevel++;
            }
            for (int i = 0; i <= newNodeLevel; i++) {
                newNode.nexts.add(null);
            }
            int curLevel = maxLevel;
            SkipListNode<K, V> curLevelPre = head;
            while (curLevel >= 0) {
                curLevelPre = findMostRightLessKeyNodeInCurLevel(curLevelPre, key, curLevel);
                if (curLevel <= newNodeLevel) {
                    newNode.nexts.set(curLevel, curLevelPre.nexts.get(curLevel));
                    curLevelPre.nexts.set(curLevel, newNode);
                }
                curLevel--;
            }
        }
    }

    public V get(K key) {
        if (key == null) return null;
        SkipListNode<K, V> pre = findMostRightLessKeyNode(key);
        SkipListNode<K, V> find = pre.nexts.get(0);
        if (find != null && find.isKeyEquals(key)) return find.value;
        return null;
    }

    public boolean containsKey(K key) {
        if (key == null) return false;
        SkipListNode<K, V> pre = findMostRightLessKeyNode(key);
        SkipListNode<K, V> find = pre.nexts.get(0);
        return find != null && find.isKeyEquals(key);
    }

    public void remove(K key) {
        if (containsKey(key)) {
            int curLevel = maxLevel;
            SkipListNode<K, V> curLevelPre = head;
            while (curLevel >= 0) {
                curLevelPre = findMostRightLessKeyNodeInCurLevel(curLevelPre, key, curLevel);
                SkipListNode<K, V> find = curLevelPre.nexts.get(curLevel);
                if (find != null && find.isKeyEquals(key)) {
                    curLevelPre.nexts.set(curLevel, find.nexts.get(curLevel));
                }
                if (curLevel != 0 && curLevelPre == head && curLevelPre.nexts.get(curLevel) == null) {
                    head.nexts.remove(curLevel);
                    maxLevel--;
                }
                curLevel--;
            }
        }
    }

    private SkipListNode<K, V> findMostRightLessKeyNode(K key) {
        if (key == null) return null;
        SkipListNode<K, V> cur = head;
        int curLevel = maxLevel;
        while (curLevel >= 0) {
            cur = findMostRightLessKeyNodeInCurLevel(cur, key, curLevel--);
        }
        return cur;
    }

    private SkipListNode<K, V> findMostRightLessKeyNodeInCurLevel(SkipListNode<K, V> cur, K key, int curLevel) {
        SkipListNode<K, V> pre = null;
        while (cur != null) {
            if (cur.isKeyLess(key)) {
                pre = cur;
                cur = cur.nexts.get(curLevel);
            } else {
                break;
            }
        }
        return pre;
    }

}
