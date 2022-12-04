package _02进阶._31有序表;

import java.util.ArrayList;
import java.util.List;

/**
 * 跳表
 * Created by huangjunyi on 2022/9/17.
 */
public class SkipListMap<K extends Comparable<K>, V> {

    public static class SkipListNode<K extends Comparable<K>, V> {
        private K key; // key 可比较
        private V value; // value
        private List<SkipListNode> nexts; // 不同层的后继节点们，level数组

        public SkipListNode(K key, V value) {
            this.key = key;
            this.value = value;
            nexts = new ArrayList<>();
        }

        /**
         * 比较当前节点key是否小于otherKey
         * @param otherKey
         * @return
         */
        public boolean isKeyLess(K otherKey) {
            return otherKey != null && (key == null || key.compareTo(otherKey) < 0);
        }

        /**
         * 比较当前节点key是否与otherKey相等
         * @param otherKey
         * @return
         */
        public boolean isKeyEquals(K otherKey) {
            return (key == null && otherKey == null) || (key != null || otherKey != null && key.compareTo(otherKey) == 0);
        }
    }

    private static final double PROBABILITY = 0.5; // 升层概率
    private int maxLevel; // 最大层高度
    private SkipListNode<K, V> head; // 头节点

    public SkipListMap() {
        head = new SkipListNode<>(null, null);
        head.nexts.add(null);
        maxLevel = 0;
    }

    /**
     * 添加 key-value
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        if (key == null) return;
        // pre 0层上小于key离key最近的最右节点
        SkipListNode<K, V> pre = findMostRightLessKeyNode(key);
        // pre节点在0层上的下一个节点
        SkipListNode<K, V> find = pre.nexts.get(0);
        if (find != null && find.isKeyEquals(key)) { // key是相等的？
            find.value = value; // 更新值
        } else { // key是不等的，要挂新节点
            SkipListNode<K, V> newNode = new SkipListNode<>(key, value);
            // 先随机掷骰子，看能冲到第几层
            int newNodeLevel = 0;
            while (Math.random() <= PROBABILITY) newNodeLevel++;
            // 大于之前所有链表的层数，那么头节点层数要升到一样高
            while (maxLevel < newNodeLevel) {
                head.nexts.add(null);
                maxLevel++;
            }
            // 初始化新节点的level数组
            for (int i = 0; i <= newNodeLevel; i++) {
                newNode.nexts.add(null);
            }
            // 从最左节点开始，从掷骰子的最高层开始，往下一层层挂上新节点
            int curLevel = maxLevel;
            SkipListNode<K, V> curLevelPre = head;
            while (curLevel >= 0) {
                // 找到当前层小于key离key最近的最右节点 curLevelPre
                curLevelPre = findMostRightLessKeyNodeInCurLevel(curLevelPre, key, curLevel);
                // 当前层比掷骰子的层数高，不进去，小于等于才进去挂节点
                if (curLevel <= newNodeLevel) {
                    // 新节点当前层挂curLevelPre在当前层的后继节点
                    newNode.nexts.set(curLevel, curLevelPre.nexts.get(curLevel));
                    // curLevelPre在当前层改为挂新节点
                    curLevelPre.nexts.set(curLevel, newNode);
                }
                // 切到下一层
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

    /**
     * 是否包含key
     * @param key
     * @return
     */
    public boolean containsKey(K key) {
        if (key == null) return false;
        // 找到第0层小于key离key最近的最右节点
        SkipListNode<K, V> pre = findMostRightLessKeyNode(key);
        // 第0层小于key离key最近的最右节点，第0层挂的后继节点find
        SkipListNode<K, V> find = pre.nexts.get(0);
        // 看find节点key是不是传入的参数key，是的话containsKey返回true
        return find != null && find.isKeyEquals(key);
    }

    public void remove(K key) {
        if (containsKey(key)) { // 包含key？

            int curLevel = maxLevel; // 最高层
            SkipListNode<K, V> curLevelPre = head; // 最左节点

            while (curLevel >= 0) { // 从最高层到第0层，一层层往下找，然后断连掉

                // 找到当前层小于key离key最近的最右节点 curLevelPre
                curLevelPre = findMostRightLessKeyNodeInCurLevel(curLevelPre, key, curLevel);
                // curLevelPre节点在当前层的后继节点
                SkipListNode<K, V> find = curLevelPre.nexts.get(curLevel);
                if (find != null && find.isKeyEquals(key)) { // 是不是要删除的？
                    curLevelPre.nexts.set(curLevel, find.nexts.get(curLevel)); // 断连，指向下一个
                }

                // 检查level层是否没节点了，是的话要消掉这一层
                if (curLevel != 0 && curLevelPre == head && curLevelPre.nexts.get(curLevel) == null) {
                    head.nexts.remove(curLevel);
                    maxLevel--;
                }

                // 切下一层
                curLevel--;

            }
        }
    }

    /**
     * 从最高层开始找，一直找到第0层小于key离key最近的最右节点
     * @param key
     * @return
     */
    private SkipListNode<K, V> findMostRightLessKeyNode(K key) {
        if (key == null) return null;
        SkipListNode<K, V> cur = head;
        int curLevel = maxLevel;
        while (curLevel >= 0) {
            cur = findMostRightLessKeyNodeInCurLevel(cur, key, curLevel--);
        }
        return cur;
    }

    /**
     * 找到当前层小于key离key最近的最右节点
     * @param cur
     * @param key
     * @param curLevel
     * @return
     */
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
