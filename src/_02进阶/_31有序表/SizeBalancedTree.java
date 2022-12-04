package _02进阶._31有序表;


/**
 * SB树
 * Created by huangjunyi on 2022/9/17.
 */
public class SizeBalancedTree<K extends Comparable<K>, V> {

    public static class SizeBalancedTreeNode<K extends Comparable<K>, V> {
        private K key; // key
        private V value; // value
        private SizeBalancedTreeNode<K, V> left; // 左孩子
        private SizeBalancedTreeNode<K, V> right; // 右孩子
        private int size; // 树节点个数

        public SizeBalancedTreeNode(K key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    private SizeBalancedTreeNode<K, V> root; // 树根节点

    public void put(K key, V value) {
        if (key == null) return;
        SizeBalancedTreeNode<K, V> node = findLastIndex(key);
        if (node != null && node.key.compareTo(key) == 0) node.value = value;
        else this.root = add(this.root, key, value);
    }

    private SizeBalancedTreeNode<K, V> add(SizeBalancedTreeNode<K, V> cur, K key, V value) {
        if (cur == null) {
            return new SizeBalancedTreeNode<>(key, value, 1);
        }
        if (key.compareTo(cur.key) < 0) {
            cur.left = add(cur.left, key, value);
        } else {
            cur.right = add(cur.right, key, value);
        }
        // 加完节点，沿途的节点size都++
        cur.size++;
        // 沿途都做平衡性检查
        return maintain(cur);
    }

    public void remove(K key) {
        if (key == null) return;
        this.root = delete(this.root, key);
    }

    private SizeBalancedTreeNode<K, V> delete(SizeBalancedTreeNode<K, V> cur, K key) {
        if (cur == null) return null;
        // 删的时候，沿途size都--
        cur.size--;
        if (key.compareTo(cur.key) < 0) {
            cur.left = delete(cur.left, key);
        } else if (key.compareTo(cur.key) > 0) {
            cur.right = delete(cur.right, key);
        } else {
            if (cur.left == null && cur.right == null) {
                cur = null;
            } else if (cur.left != null && cur.right == null) {
                cur = cur.left;
            } else if (cur.left == null && cur.right != null) {
                cur = cur.right;
            } else {
                SizeBalancedTreeNode<K, V> successor = cur.right;
                while (successor.left != null) successor = successor.left;
                cur.right = delete(cur.right, successor.key);
                successor.left = cur.left;
                successor.right = cur.right;
                successor.size = cur.size;
                cur = successor;
            }
        }
        // 删除不做平衡性检查和调整
        return cur;
    }

    public V get(K key) {
        if (key == null) return null;
        SizeBalancedTreeNode<K, V> node = findLastIndex(key);
        if (node != null && node.key.compareTo(key) == 0) return node.value;
        return null;
    }

    /**
     * 平衡性检查和调整
     * @param cur
     * @return
     */
    private SizeBalancedTreeNode<K, V> maintain(SizeBalancedTreeNode<K, V> cur) {
        if (cur == null) return null;
        int curLeftSize = cur.left != null ? cur.left.size : 0; // 左孩子size
        int curLeftLeftSize = cur.left != null && cur.left.left != null ? cur.left.left.size : 0; // 左左孩子大小
        int curLeftRightSize = cur.left != null && cur.left.right != null ? cur.left.right.size : 0; // 左右孩子大小
        int curRightSize = cur.right != null ?cur.right.size : 0; // 右孩子大小
        int curRightRightSize = cur.right != null && cur.right.right != null ? cur.right.right.size : 0; // 右右孩子大小
        int curRightLeftSize = cur.right != null && cur.right.left != null ? cur.right.left.size : 0; // 右左孩子大小
        if (curLeftLeftSize > curRightSize) { // LL型？
            cur = rightRotate(cur);
            // 换了孩子的节点，递归做平衡性调整
            cur.right = maintain(cur.right);
            cur = maintain(cur);
        } else if (curLeftRightSize > curRightSize) { // LR型？
            cur.left = leftRotate(cur.left);
            cur = rightRotate(cur);
            // 换了孩子的节点，递归做平衡性调整
            cur.left = maintain(cur.left);
            cur.right = maintain(cur.right);
            cur = maintain(cur);
        } else if (curRightRightSize > curLeftSize) { // RR型？
            cur = leftRotate(cur);
            // 换了孩子的节点，递归做平衡性调整
            cur.left = maintain(cur.left);
            cur = maintain(cur);
        } else if (curRightLeftSize > curLeftSize) {// RL型？
            cur.right = rightRotate(cur.right);
            cur = leftRotate(cur);
            // 换了孩子的节点，递归做平衡性调整
            cur.left = maintain(cur.left);
            cur.right = maintain(cur.right);
            cur = maintain(cur);
        }
        return cur;
    }

    /**
     * 左旋
     * @param cur
     * @return
     */
    private SizeBalancedTreeNode<K, V> leftRotate(SizeBalancedTreeNode<K, V> cur) {
        SizeBalancedTreeNode<K, V> right = cur.right;
        cur.right = right.left;
        right.left = cur;
        // size调整
        right.size = cur.size;
        cur.size = (cur.left != null ? cur.left.size : 0) + (cur.right != null ? cur.right.size : 0) + 1;
        return right;
    }

    /**
     * 右旋
     * @param cur
     * @return
     */
    private SizeBalancedTreeNode<K, V> rightRotate(SizeBalancedTreeNode<K, V> cur) {
        SizeBalancedTreeNode<K, V> left = cur.left;
        cur.left = left.right;
        left.right = cur;
        // size调整
        left.size = cur.size;
        cur.size = (cur.left != null ? cur.left.size : 0) + (cur.right != null ? cur.right.size : 0) + 1;
        return left;
    }

    private SizeBalancedTreeNode<K, V> findLastIndex(K key) {
        SizeBalancedTreeNode<K, V> pre = this.root;
        SizeBalancedTreeNode<K, V> cur = this.root;
        while (cur != null) {
            pre = cur;
            if (key.compareTo(cur.key) < 0) cur = cur.left;
            else if (key.compareTo(cur.key) > 0) cur = cur.right;
            else break;
        }
        return pre;
    }

    private SizeBalancedTreeNode<K, V> findLastNoSmallIndex(K key) {
        SizeBalancedTreeNode<K, V> pre = null;
        SizeBalancedTreeNode<K, V> cur = this.root;
        while (cur != null) {
            if (key.compareTo(cur.key) < 0) {
                pre = cur;
                cur = cur.left;
            } else if (key.compareTo(cur.key) > 0) {
                cur = cur.right;
            } else {
                pre = cur;
                break;
            }
        }
        return pre;
    }

    private SizeBalancedTreeNode<K, V> findLastNoBigIndex(K key) {
        SizeBalancedTreeNode<K, V> pre = null;
        SizeBalancedTreeNode<K, V> cur = this.root;
        while (cur != null) {
            if (key.compareTo(cur.key) < 0) {
                cur = cur.left;
            } else if (key.compareTo(cur.key) > 0) {
                pre = cur;
                cur = cur.right;
            } else {
                pre = cur;
                break;
            }
        }
        return pre;
    }

}
