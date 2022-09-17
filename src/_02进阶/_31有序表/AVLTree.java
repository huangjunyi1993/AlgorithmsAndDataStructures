package _02进阶._31有序表;

/**
 * 完全平衡二叉树
 * Created by huangjunyi on 2022/9/16.
 */
public class AVLTree<K extends Comparable<K>, V> {

    public static class AVLTreeNode<K extends Comparable<K>, V> {
        private K key;
        private V value;
        private AVLTreeNode left;
        private AVLTreeNode right;
        private int high;
    }

    private AVLTreeNode<K, V> root;

    public void add(K key, V value) {
        if (key == null) return;
        this.root = add(this.root, key, value);
    }

    private AVLTreeNode<K, V> add(AVLTreeNode<K, V> cur, K key, V value) {
        if (cur == null) {
            AVLTreeNode<K, V> node = new AVLTreeNode<>();
            node.key = key;
            node.value = value;
            node.high = 1;
            return node;
        }
        if (cur.key.compareTo(key) > 0) {
            cur.left = add(cur.left, key, value);
        } else if (cur.key.compareTo(key) < 0) {
            cur.right = add(cur.right, key, value);
        } else {
            // nothing to do
        }
        cur.high = Math.max(cur.left != null ? cur.left.high : 0, cur.right != null ? cur.right.high : 0) + 1;
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

    private AVLTreeNode<K, V> delete(AVLTreeNode<K, V> cur, K key) {
        if (cur == null) return null;
        if (cur.key.compareTo(key) > 0) {
            cur.left = delete(cur.left, key);
        } else if (cur.key.compareTo(key) < 0) {
            cur.right = delete(cur.right, key);
        } else {
            if (cur.left == null && cur.right == null) {
                cur = null;
            } else if (cur.left != null && cur.right == null) {
                cur = cur.left;
            } else if (cur.left == null && cur.right != null) {
                cur = cur.right;
            } else {
                AVLTreeNode<K, V> successor = cur.right;
                while (successor.left != null) successor = successor.left;
                cur.right = delete(cur.right, successor.key);
                successor.left = cur.left;
                successor.right = cur.right;
                cur = successor;
            }
        }
        cur.high = Math.max(cur.left != null ? cur.left.high : 0, cur.right != null ? cur.right.high : 0) + 1;
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
        if (Math.abs(leftHigh - rightHith) > 0) {
            if (leftHigh > rightHith) {
                int leftleftHigh = cur.left.left != null ? cur.left.left.high : 0;
                int leftRightHigh = cur.left.right != null ? cur.left.right.high : 0;
                if (leftleftHigh >= leftRightHigh) {
                    cur = rightRotate(cur);
                } else {
                    cur.left = leftRotate(cur.left);
                    cur = rightRotate(cur);
                }
            } else {
                int rightRightHigh = cur.right.right != null ? cur.right.right.high : 0;
                int rightLeftHigh = cur.right.left != null ? cur.right.left.high : 0;
                if (rightRightHigh >= rightLeftHigh) {
                    cur = leftRotate(cur);
                } else {
                    cur.right = rightRotate(cur.right);
                    cur = leftRotate(cur);
                }
            }
        }
        return cur;
    }

    private AVLTreeNode leftRotate(AVLTreeNode<K, V> cur) {
        AVLTreeNode<K, V> curRight = cur.right;
        cur.right = curRight.left;
        curRight.left = cur;
        cur.high = Math.max(cur.left != null ? cur.left.high : 0, cur.right != null ? cur.right.high : 0) + 1;
        curRight.high = Math.max(curRight.left != null ? curRight.left.high : 0, curRight.right != null ? curRight.right.high : 0) + 1;
        return curRight;
    }

    private AVLTreeNode<K, V> rightRotate(AVLTreeNode<K, V> cur) {
        AVLTreeNode<K, V> curLeft = cur.left;
        cur.left = curLeft.right;
        curLeft.right = cur;
        cur.high = Math.max(cur.left != null ? cur.left.high : 0, cur.right != null ? cur.right.high : 0) + 1;
        curLeft.high = Math.max(curLeft.left != null ? curLeft.left.high : 0, curLeft.right != null ? curLeft.right.high : 0) + 1;
        return curLeft;
    }


}
