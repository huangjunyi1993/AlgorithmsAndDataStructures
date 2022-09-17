package _02进阶._24线段树;

/**
 * 线段树，使得对数组范围更新、增加、查询的时间复杂度变成O(logn)
 * Created by huangjunyi on 2022/9/10.
 */
public class SegmentTree01 {

    private int lenght;
    private int[] arr;
    private int[] sum;
    private int[] lazy;
    private int[] change;
    private boolean[] update;

    public SegmentTree01(int[] original) {
        //arr为原数组长度加1，0下标弃而不用
        int[] arr = new int[original.length + 1];
        for (int i = 0; i < original.length; i++) {
            arr[i + 1] = original[i];
        }
        lenght = original.length + 1;
        sum = new int[lenght << 2];
        lazy = new int[lenght << 2];
        change = new int[lenght << 2];
        update = new boolean[lenght << 2];
    }

    /**
     * 构建，初始化sum数组
     * @param l
     * @param r
     * @param rt
     */
    public void build(int l, int r, int rt) {
        if (l == r) {
            sum[rt] = arr[l];
            return;
        }
        int mid = (l + r) >> 1;
        build(l, mid, rt << 1);
        build(mid + 1, r, (rt << 1) | 1);
        pushUp(rt);
    }

    /**
     * add任务，指定范围的数都增加指定的值
     * @param L 指定范围的左边界
     * @param R 指定范围的右边界
     * @param C 指定增加的值
     * @param l 当前节点代表范围的左边界
     * @param r 当前节点代表范围的右边界
     * @param rt 当前节点下标
     */
    public void add(int L, int R, int C, int l, int r, int rt) {
        if (L <= l && r <= R) {
            sum[rt] += (r - l + 1) * C;
            lazy[rt] += C;
            return;
        }
        int mid = (l + r) >> 1;
        pushDown(rt, mid - l + 1, r - mid);
        if (L <= mid) {
            add(L, R, C, l, mid, rt << 1);
        }
        if (R > mid) {
            add(L, R, C, mid + 1, r, (rt << 1) | 1);
        }
        pushUp(rt);
    }

    /**
     * 更新任务，指定范围内的数都更新为指定的值
     * @param L 指定范围的左边界
     * @param R 指定范围的右边界
     * @param C 指定范围更新的值
     * @param l 当前节点代表范围的左边界
     * @param r 当前节点代表范围的右边界
     * @param rt 当前节点的下标
     */
    public void update(int L, int R, int C, int l, int r, int rt) {
        if (L <= l && r <= R) {
            change[rt] = C;
            update[rt] = true;
            lazy[rt] = 0;
            sum[rt] = C * (r - l + 1);
            return;
        }
        int mid = (l + r) >> 1;
        pushDown(rt, mid - l + 1, r - mid);
        if (L <= mid) {
            update(L, R, C, l, mid, rt << 1);
        }
        if (R > mid) {
            update(L, R, C, mid + 1, r, (rt << 1) | 1);
        }
        pushUp(rt);
    }

    /**
     * 查询任务，查询指定范围内的数的总和
     * @param L 指定范围的左边界
     * @param R 指定范围的右边界
     * @param l 当前节点代表范围的左边界
     * @param r 当前节点代表范围的右边界
     * @param rt 当前节点的下标
     * @return
     */
    public int query(int L, int R, int l, int r, int rt) {
        if (L <= l && r <= R) {
            return sum[rt];
        }
        int mid = (l + r) >> 1;
        pushDown(rt, mid - l + 1, r - l);
        int res = 0;
        if (L <= mid) {
            res += query(L, R, l, mid, rt << 1);
        }
        if (R > mid) {
            res += query(L, R, mid + 1, r, (rt << 1) | 1);
        }
        return res;
    }

    /**
     * 任务下发，把父节点积攒的懒更新和懒增加都下发到子结点
     * @param rt
     * @param ln
     * @param rn
     */
    private void pushDown(int rt, int ln, int rn) {
        if (update[rt]) {
            update[rt << 1] = true;
            update[(rt << 1) | 1] = true;
            change[rt << 1] = change[rt];
            change[(rt << 1) | 1] = change[rt];
            sum[rt << 1] = change[rt << 1] * ln;
            sum[(rt << 1) | 1] = change[(rt << 1) | 1] * rn;
            lazy[rt << 1] = 0;
            lazy[(rt << 1) | 1] = 0;
            update[rt] = false;
        }
        if (lazy[rt] != 0) {
            lazy[rt << 1] = lazy[rt];
            lazy[(rt << 1) | 1] = lazy[rt];
            sum[rt << 1] = lazy[rt] * ln;
            sum[(rt << 1) | 1] = lazy[rt] * rn;
            lazy[rt] = 0;
        }
    }

    /**
     * 父节点往左右子结点收集sum
     * @param rt
     */
    private void pushUp(int rt) {
        sum[rt] = sum[rt << 1] + sum[(rt << 1) | 1];
    }
}
