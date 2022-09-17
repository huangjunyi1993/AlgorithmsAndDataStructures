package _02进阶._31有序表;


/**
 * 改写的SB树
 * Created by huangjunyi on 2022/9/17.
 */
public class SizeBalancedTree01<K extends Comparable<K>, V> {

    public static class SizeBalancedTreeNode<K extends Comparable<K>, V> {
        private K key;
        private V value;
        private SizeBalancedTreeNode<K, V> left;
        private SizeBalancedTreeNode<K, V> right;
        private int size; //当前节点为头节点的子树的不同节点的个数
        private int all; //当前节点为头节点的子树的节点个数

        public SizeBalancedTreeNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.size = 1;
            this.all = 1;
        }
    }

    private SizeBalancedTreeNode<K, V> root;

    public void put(K key, V value) {
        if (key == null) return;
        SizeBalancedTreeNode<K, V> node = findLastIndex(key);
        if (node != null && node.key.compareTo(key) == 0) {
            node.value = value;
            node.all++;
        }
        else this.root = add(this.root, key, value);
    }

    /**
     * 小于key的节点的个数
     * @param key
     * @return
     */
    public int countLessKey(K key) {
        SizeBalancedTreeNode<K, V> cur = this.root;
        int res = 0;
        while (cur != null) {
            if (cur.key.compareTo(key) > 0) {
                cur = cur.left;
            }
            else if (cur.key.compareTo(key) == 0) {
                res += cur.left != null ? cur.left.all : 0;
                break;
            } else {
                res += cur.all - (cur.right != null ? cur.right.all : 0);
                cur = cur.right;
            }
        }
        return res;
    }

    private SizeBalancedTreeNode<K, V> add(SizeBalancedTreeNode<K, V> cur, K key, V value) {
        if (cur == null) {
            return new SizeBalancedTreeNode<>(key, value);
        }
        if (key.compareTo(cur.key) < 0) {
            cur.left = add(cur.left, key, value);
        } else {
            cur.right = add(cur.right, key, value);
        }
        cur.size++;
        cur.all++;
        return maintain(cur);
    }

    public void remove(K key) {
        if (key == null) return;
        SizeBalancedTreeNode<K, V> node = getNode(key);
        if (node != null && node.all > 1) {
            node.all--;
            return;
        }
        this.root = delete(this.root, key);
    }

    private SizeBalancedTreeNode<K, V> delete(SizeBalancedTreeNode<K, V> cur, K key) {
        if (cur == null) return null;
        cur.size--;
        cur.all--;
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
        return cur;
    }

    public V get(K key) {
        SizeBalancedTreeNode<K, V> node = getNode(key);
        if (node != null) return node.value;
        return null;
    }

    private SizeBalancedTreeNode<K, V> getNode(K key) {
        if (key == null) return null;
        SizeBalancedTreeNode<K, V> node = findLastIndex(key);
        if (node != null && node.key.compareTo(key) == 0) return node;
        return null;
    }

    private SizeBalancedTreeNode<K, V> maintain(SizeBalancedTreeNode<K, V> cur) {
        if (cur == null) return null;
        int curLeftSize = cur.left != null ? cur.left.size : 0;
        int curLeftLeftSize = cur.left != null && cur.left.left != null ? cur.left.left.size : 0;
        int curLeftRightSize = cur.left != null && cur.left.right != null ? cur.left.right.size : 0;
        int curRightSize = cur.right != null ?cur.right.size : 0;
        int curRightRightSize = cur.right != null && cur.right.right != null ? cur.right.right.size : 0;
        int curRightLeftSize = cur.right != null && cur.right.left != null ? cur.right.left.size : 0;
        if (curLeftLeftSize > curRightSize) {
            cur = rightRotate(cur);
            cur.right = maintain(cur.right);
            cur = maintain(cur);
        } else if (curLeftRightSize > curRightSize) {
            cur.left = leftRotate(cur.left);
            cur = rightRotate(cur);
            cur.left = maintain(cur.left);
            cur.right = maintain(cur.right);
            cur = maintain(cur);
        } else if (curRightRightSize > curLeftSize) {
            cur = leftRotate(cur);
            cur.left = maintain(cur.left);
            cur = maintain(cur);
        } else if (curRightLeftSize > curLeftSize) {
            cur.right = rightRotate(cur.right);
            cur = leftRotate(cur);
            cur.left = maintain(cur.left);
            cur.right = maintain(cur.right);
            cur = maintain(cur);
        }
        return cur;
    }

    private SizeBalancedTreeNode<K, V> leftRotate(SizeBalancedTreeNode<K, V> cur) {
        SizeBalancedTreeNode<K, V> right = cur.right;
        cur.right = right.left;
        right.left = cur;
        right.size = cur.size;
        cur.size = (cur.left != null ? cur.left.size : 0) + (cur.right != null ? cur.right.size : 0) + 1;
        return right;
    }

    private SizeBalancedTreeNode<K, V> rightRotate(SizeBalancedTreeNode<K, V> cur) {
        SizeBalancedTreeNode<K, V> left = cur.left;
        cur.left = left.right;
        left.right = cur;
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
