package _02进阶._24线段树;

/**
 * 线段树，使得对数组范围更新、增加、查询的时间复杂度变成O(logn)
 * Created by huangjunyi on 2022/9/10.
 */
public class SegmentTree01 {

    private int lenght;
    private int[] arr;
    private int[] sum; // 范围累加和信息
    private int[] lazy; // 范围懒增加信息
    private int[] change; // 范围修改信息
    private boolean[] update; // 范围修改标志

    public SegmentTree01(int[] original) {
        //arr为原数组长度加1，0下标弃而不用
        int[] arr = new int[original.length + 1];
        for (int i = 0; i < original.length; i++) {
            arr[i + 1] = original[i];
        }
        lenght = original.length + 1;
        // 4倍长度
        sum = new int[lenght << 2];
        lazy = new int[lenght << 2];
        change = new int[lenght << 2];
        update = new boolean[lenght << 2];
    }

    /**
     * 构建，初始化sum数组
     * @param l 原数组范围左边界
     * @param r 原数组范围右边界
     * @param rt 当前节点下标，从1开始
     */
    public void build(int l, int r, int rt) {
        if (l == r) {
            // base case，来到叶子节点
            sum[rt] = arr[l];
            return;
        }
        int mid = (l + r) >> 1;
        // 递归构建左子树
        build(l, mid, rt << 1);
        // 递归构建右子树
        build(mid + 1, r, (rt << 1) | 1);
        // 累加左右孩子的值
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
            // 任务把格子包住了，不用往下发了，更新sum数组和lazy数组中自己的信息
            sum[rt] += (r - l + 1) * C;
            lazy[rt] += C;
            return;
        }
        // 任务没保住当前这个格子，要下发任务
        int mid = (l + r) >> 1;
        // 先把之前的任务往下发一层
        pushDown(rt, mid - l + 1, r - mid);
        // 任务需要发给左边
        if (L <= mid) {
            add(L, R, C, l, mid, rt << 1);
        }
        // 任务需要发给右边
        if (R > mid) {
            add(L, R, C, mid + 1, r, (rt << 1) | 1);
        }
        // 左右都调对了，抓住左右孩子的累加和，更新自己的累加和
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
            // base case，任务范围把这个结点的范围包住了，不用下发任务了，更新chagne数组和update数组中自己的信息
            change[rt] = C;
            update[rt] = true;
            // 更新要把之前攒的lazy失效调
            lazy[rt] = 0;
            // 调整sum
            sum[rt] = C * (r - l + 1);
            return;
        }
        // 没包住，先下发之前的任务
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
            // 任务范围把当前节点范围全包了，不有往下查了，直接返回
            return sum[rt];
        }
        // 任务范围没有覆盖当前节点范围，需要往下查，但是查之前要把之前积攒的任务发下去
        int mid = (l + r) >> 1;
        pushDown(rt, mid - l + 1, r - l);
        // 任务发好了，再去左右两边查
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
     * 任务下发，把父节点积攒的懒更新和懒增加都下发到子节点
     * @param rt 父节点下标
     * @param ln 左节点的范围有几个数
     * @param rn 右节点的范围有几个数
     */
    private void pushDown(int rt, int ln, int rn) {
        // 先检查是否有update，因为update是要失效掉lazy的，
        // 如果自己既有update任务又有lazy任务，lazy肯定是后面到的
        // 所以先下发update任务，再下发lazy任务，表示在修改之后的值上做累加
        // 更新是要覆盖掉左右孩子的sum和lazy信息的
        if (update[rt]) {
            // 更新左右孩子的update和change信息
            update[rt << 1] = true;
            update[(rt << 1) | 1] = true;
            change[rt << 1] = change[rt];
            change[(rt << 1) | 1] = change[rt];
            // 覆盖掉左右孩子的sum和lazy信息
            sum[rt << 1] = change[rt << 1] * ln;
            sum[(rt << 1) | 1] = change[(rt << 1) | 1] * rn;
            lazy[rt << 1] = 0;
            lazy[(rt << 1) | 1] = 0;
            // 自己的update信息下发好了
            update[rt] = false;
        }
        // lazy不等于0，表示之前懒住了任务
        if (lazy[rt] != 0) {
            // 下发父节点的懒增加到下一层，更新下一层的lazy数组和sum数组
            lazy[rt << 1] = lazy[rt];
            lazy[(rt << 1) | 1] = lazy[rt];
            sum[rt << 1] = lazy[rt] * ln;
            sum[(rt << 1) | 1] = lazy[rt] * rn;
            // 当前父节点的lazy清空
            lazy[rt] = 0;
        }
    }

    /**
     * 父节点往左右子节点收集sum
     * @param rt 父节点下标
     */
    private void pushUp(int rt) {
        sum[rt] = sum[rt << 1] + sum[(rt << 1) | 1];
    }
}
