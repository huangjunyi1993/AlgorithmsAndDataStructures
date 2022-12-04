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
        private int all; //当前节点为头节点的子树的所有节点的个数

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
            this.root = add(this.root, key, value, true);
        }
        else {
            this.root = add(this.root, key, value, false);
        }
    }

    /**
     * 小于key的节点的个数
     * @param key
     * @return
     */
    public int countLessKey(K key) {
        SizeBalancedTreeNode<K, V> cur = this.root;
        int res = 0; // 小于key的节点的个数
        // 从头节点一路滑下去，沿途收集答案
        while (cur != null) {
            if (cur.key.compareTo(key) > 0) {
                // 往左滑，不累加
                cur = cur.left;
            }
            else if (cur.key.compareTo(key) == 0) {
                // 等于key，累加左边的所有节点数
                res += cur.left != null ? cur.left.all : 0;
                break;
            } else {
                // 往右滑，累加当前树减去右子树后的所有节点数
                res += cur.all - (cur.right != null ? cur.right.all : 0);
                cur = cur.right;
            }
        }
        return res;
    }

    private SizeBalancedTreeNode<K, V> add(SizeBalancedTreeNode<K, V> cur, K key, V value, boolean contains) {
        if (cur == null) {
            return new SizeBalancedTreeNode<>(key, value);
        }
        cur.all++;
        if (key.compareTo(cur.key) < 0) {
            cur.left = add(cur.left, key, value, true);
        } else if (key.compareTo(cur.key) > 0){
            cur.right = add(cur.right, key, value, true);
        } else { // 添加了重复key，all++后直接返回，不用做平衡性调整
            return cur;
        }
        if (!contains) cur.size++; // 不是添加重复key，size++
        return maintain(cur);
    }

//    public void remove(K key) {
//        if (key == null) return;
//        SizeBalancedTreeNode<K, V> node = getNode(key);
//        if (node != null && node.all > 1) {
//            node.all--;
//            return;
//        }
//        this.root = delete(this.root, key);
//    }
//
//    private SizeBalancedTreeNode<K, V> delete(SizeBalancedTreeNode<K, V> cur, K key) {
//        if (cur == null) return null;
//        cur.size--;
//        cur.all--;
//        if (key.compareTo(cur.key) < 0) {
//            cur.left = delete(cur.left, key);
//        } else if (key.compareTo(cur.key) > 0) {
//            cur.right = delete(cur.right, key);
//        } else {
//            if (cur.left == null && cur.right == null) {
//                cur = null;
//            } else if (cur.left != null && cur.right == null) {
//                cur = cur.left;
//            } else if (cur.left == null && cur.right != null) {
//                cur = cur.right;
//            } else {
//                SizeBalancedTreeNode<K, V> successor = cur.right;
//                while (successor.left != null) successor = successor.left;
//                cur.right = delete(cur.right, successor.key);
//                successor.left = cur.left;
//                successor.right = cur.right;
//                successor.size = cur.size;
//                cur = successor;
//            }
//        }
//        return cur;
//    }

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

    private SizeBalancedTreeNode rightRotate(SizeBalancedTreeNode cur) {
        int same = cur.all - (cur.left != null ? cur.left.all : 0) - (cur.right != null ? cur.right.all : 0);
        SizeBalancedTreeNode leftNode = cur.left;
        cur.left = leftNode.right;
        leftNode.right = cur;
        // size平衡因子调整
        leftNode.size = cur.size;
        cur.size = (cur.left != null ? cur.left.size : 0) + (cur.right != null ? cur.right.size : 0) + 1;
        // all也要调整
        leftNode.all = cur.all;
        cur.all = (cur.left != null ? cur.left.all : 0) + (cur.right != null ? cur.right.all : 0) + same;
        return leftNode;
    }

    private SizeBalancedTreeNode leftRotate(SizeBalancedTreeNode cur) {
        int same = cur.all - (cur.left != null ? cur.left.all : 0) - (cur.right != null ? cur.right.all : 0);
        SizeBalancedTreeNode rightNode = cur.right;
        cur.right = rightNode.left;
        rightNode.left = cur;
        // size平衡因子调整
        rightNode.size = cur.size;
        cur.size = (cur.left != null ? cur.left.size : 0) + (cur.right != null ? cur.right.size : 0) + 1;
        // all也要调整
        rightNode.all = cur.all;
        cur.all = (cur.left != null ? cur.left.all : 0) + (cur.right != null ? cur.right.all : 0) + same;
        return rightNode;
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
