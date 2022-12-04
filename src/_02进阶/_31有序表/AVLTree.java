package _02进阶._31有序表;

/**
 * 完全平衡二叉树
 * Created by huangjunyi on 2022/9/16.
 */
public class AVLTree<K extends Comparable<K>, V> {

    public static class AVLTreeNode<K extends Comparable<K>, V> {
        private K key; // key
        private V value; // value
        private AVLTreeNode left; // 左孩子
        private AVLTreeNode right; // 右孩子
        private int high; // 树高度
    }

    // 头节点
    private AVLTreeNode<K, V> root;

    /**
     * 添加节点
     * @param key
     * @param value
     */
    public void add(K key, V value) {
        if (key == null) return;
        // add方法带返回值，因为有可能换头部，换了要接住新头
        this.root = add(this.root, key, value);
    }

    /**
     * 添加节点
     * @param cur
     * @param key
     * @param value
     * @return
     */
    private AVLTreeNode<K, V> add(AVLTreeNode<K, V> cur, K key, V value) {
        if (cur == null) {
            // 当前节点null，建出新节点返回
            AVLTreeNode<K, V> node = new AVLTreeNode<>();
            node.key = key;
            node.value = value;
            node.high = 1;
            return node;
        }
        if (cur.key.compareTo(key) > 0) {
            // 当前节点key大于加入的key，往左树挂，有可能换头，所以cur.left接住
            cur.left = add(cur.left, key, value);
        } else if (cur.key.compareTo(key) < 0) {
            // 当前节点key小于加入的key，往右树挂，有可能换头，所以cur.right接住
            cur.right = add(cur.right, key, value);
        } else {
            // nothing to do
        }
        // 调整高度
        cur.high = Math.max(cur.left != null ? cur.left.high : 0, cur.right != null ? cur.right.high : 0) + 1;
        // 检查平衡性，如果平衡性被破坏了，调整
        cur = maintain(cur);
        return cur;
    }

    public V get(K key) {
        if (key == null) return null;
        AVLTreeNode<K, V> node = findLastIndex(key);
        if (node != null && node.key.compareTo(key) == 0) return node.value;
        return null;
    }

    private AVLTreeNode<K, V> findLastIndex(K key) {
        AVLTreeNode<K, V> pre = root;
        AVLTreeNode<K, V> cur = root;
        while (cur != null) {
            pre = cur;
            if (cur.key.compareTo(key) > 0) cur = cur.left;
            else if (cur.key.compareTo(key) < 0) cur = cur.right;
            else break;
        }
        return pre;
    }

    public AVLTreeNode<K, V> findLastNoSmallIndex(K key) {
        AVLTreeNode<K, V> pre = null;
        AVLTreeNode<K, V> cur = root;
        while (cur != null) {
            if (cur.key.compareTo(key) == 0) {
                pre = cur;
                break;
            } else if (cur.key.compareTo(key) > 0) {
                pre = cur;
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return pre;
    }

    public AVLTreeNode<K, V> findLastNoBigIndex(K key) {
        AVLTreeNode<K, V> pre = null;
        AVLTreeNode<K, V> cur = root;
        while (cur != null) {
            if (cur.key.compareTo(key) == 0) {
                pre = cur;
                break;
            } else if (cur.key.compareTo(key) > 0) {
                cur = cur.left;
            } else {
                pre = cur;
                cur = cur.right;
            }
        }
        return pre;
    }

    public void put(K key, V value) {
        if (key == null) return;
        AVLTreeNode<K, V> node = findLastIndex(key);
        if (node != null && node.key.compareTo(key) == 0) node.value = value;
        else add(key, value);
    }

    public void remove(K key) {
        if (key == null) return;
        this.root = delete(this.root, key);
    }

    /**
     * 删除节点
     * @param cur
     * @param key
     * @return
     */
    private AVLTreeNode<K, V> delete(AVLTreeNode<K, V> cur, K key) {
        // 没有命中，返回null，不删
        if (cur == null) return null;
        if (cur.key.compareTo(key) > 0) {
            // 当前节点key比要删的key大，走左边
            cur.left = delete(cur.left, key);
        } else if (cur.key.compareTo(key) < 0) {
            // 当前节点key比要删的key小，走右边
            cur.right = delete(cur.right, key);
        } else {
            // 命中了，要删这个节点
            if (cur.left == null && cur.right == null) { // 无左无右，直接删除
                cur = null;
            } else if (cur.left != null && cur.right == null) { // 有左无右，左孩子接替
                cur = cur.left;
            } else if (cur.left == null && cur.right != null) { // 无左有右，右孩子接替
                cur = cur.right;
            } else { // 有左有右，后继节点接替
                // 寻找后继节点，右树上的最左节点
                AVLTreeNode<K, V> successor = cur.right;
                while (successor.left != null) successor = successor.left;
                // 现在找到了后继节点，先在右树上，把后继节点删除
                cur.right = delete(cur.right, successor.key);
                // 后继节点接住删除节点的左右孩子
                successor.left = cur.left;
                successor.right = cur.right;
                // 后继节点接替当前节点
                cur = successor;
            }
        }
        // 高度调整
        cur.high = Math.max(cur.left != null ? cur.left.high : 0, cur.right != null ? cur.right.high : 0) + 1;
        // 平衡性检查
        return maintain(cur);
    }

    public boolean containsKey(K key) {
        if (key == null) return false;
        AVLTreeNode<K, V> node = findLastIndex(key);
        if (node != null && node.key.compareTo(key) == 0) return true;
        return false;
    }

    private AVLTreeNode<K, V> maintain(AVLTreeNode<K, V> cur) {
        if (cur == null) return null;
        int leftHigh = cur.left != null ? cur.left.high : 0;
        int rightHith = cur.right != null ? cur.right.high: 0;
        if (Math.abs(leftHigh - rightHith) > 0) { // 左右树高差大于1？
            if (leftHigh > rightHith) { // 左树高？
                int leftleftHigh = cur.left.left != null ? cur.left.left.high : 0;
                int leftRightHigh = cur.left.right != null ? cur.left.right.high : 0;
                if (leftleftHigh >= leftRightHigh) { // LL型
                    cur = rightRotate(cur); // 一次右旋即可
                } else { // LR型
                    cur.left = leftRotate(cur.left); // 先左旋
                    cur = rightRotate(cur); // 再右旋
                }
            } else { // 右树高？
                int rightRightHigh = cur.right.right != null ? cur.right.right.high : 0;
                int rightLeftHigh = cur.right.left != null ? cur.right.left.high : 0;
                if (rightRightHigh >= rightLeftHigh) { // RR型
                    cur = leftRotate(cur); // 一次左旋即可
                } else { // RL型
                    cur.right = rightRotate(cur.right); // 先右旋
                    cur = leftRotate(cur); // 再左旋
                }
            }
        }
        return cur;
    }

    /**
     * 左旋
     */
    private AVLTreeNode leftRotate(AVLTreeNode<K, V> cur) {
        AVLTreeNode<K, V> curRight = cur.right;
        cur.right = curRight.left;
        curRight.left = cur;
        cur.high = Math.max(cur.left != null ? cur.left.high : 0, cur.right != null ? cur.right.high : 0) + 1;
        curRight.high = Math.max(curRight.left != null ? curRight.left.high : 0, curRight.right != null ? curRight.right.high : 0) + 1;
        return curRight;
    }

    /**
     * 右旋
     */
    private AVLTreeNode<K, V> rightRotate(AVLTreeNode<K, V> cur) {
        AVLTreeNode<K, V> curLeft = cur.left; // curLeft 左孩子
        cur.left = curLeft.right; // 左指针，指向左孩子的右
        curLeft.right = cur; // 左孩子的右，指向自己
        // 自己调整高度
        cur.high = Math.max(cur.left != null ? cur.left.high : 0, cur.right != null ? cur.right.high : 0) + 1;
        // 原孩子调整高度
        curLeft.high = Math.max(curLeft.left != null ? curLeft.left.high : 0, curLeft.right != null ? curLeft.right.high : 0) + 1;
        // 返回新头部
        return curLeft;
    }


}
