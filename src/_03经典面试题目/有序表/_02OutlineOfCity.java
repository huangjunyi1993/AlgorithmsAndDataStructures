package _03经典面试题目.有序表;

import java.util.*;

/**
 * 给定一个 N×3 的矩阵 matrix，对于每一个长度为 3 的小数组 arr，都表示一个大楼的三个数据。
 * arr[0]表示大楼的左边界，arr[1]表示大楼的右边界，arr[2]表示大楼的高度(一定大于 0)。
 * 每座大楼的地基都在 X 轴上，大楼之间可能会有重叠，请返回整体的轮廓线数组。
 *
 * 【举例】 matrix ={{2,5,6}, {1,7,4}, {4,6,7}, {3,6,5}, {10,13,2}, {9,11,3}, {12,14,4},{10,12,5} }
 * 返回: {{1,2,4},{2,4,6}, {4,6,7}, {6,7,4}, {9,10,3}, {10,12,5}, {12,14,4}}
 *
 * Created by huangjunyi on 2022/10/7.
 */
public class _02OutlineOfCity {

    private static class Op {
        private int hight; // 高度
        private boolean isAdd; // true为崛起的高度，false为落下的高度
        private int x; // x轴点位

        public Op(int hight, boolean isAdd, int x) {
            this.hight = hight;
            this.isAdd = isAdd;
            this.x = x;
        }
    }

    /**
     * 获取代表城市轮廓线的数组
     * 步骤：
     * 1、根据matrix生成Op数组，并排序
     * 2、根据Op数组，生成高度次数映射表、x轴位置高度映射表，ops => hightTimesMap => xMaxHightMap
     * 3、根据x轴位置高度映射表，生成轮廓线数组，xMaxHightMap => res
     * @param matrix
     * @return
     */
    public static List<List<Integer>> getOutline(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return null;

        /*
        1、根据matrix生成Op数组，并排序
        排除根据x轴进行排序，如果x轴点位相同，生成的排前面，落下的排后面
         */
        int N = matrix.length;
        Op[] ops = new Op[N << 1];
        for (int i = 0; i < matrix.length; i++) {
            ops[i * 2] = new Op(matrix[i][2], true, matrix[i][0]);
            ops[i * 2 + 1] = new Op(matrix[i][2], false, matrix[i][1]);
        }
        Arrays.sort(ops, (o1, o2) -> {
            if (o1.x != o2.x) {
                return o1.x - o2.x;
            }
            if (o1.isAdd != o2.isAdd) {
                return o1.isAdd ? -1 : 1;
            }
            return 0;
        });

        /*
        2、根据Op数组，生成高度次数映射表、x轴位置高度映射表，ops => hightTimesMap => xMaxHightMap
        遍历ops数组
        如果Op的isAdd为true，代表是升起的高度
        在高度次数映射表找到，并加1，或者生成一条
        然后根据高度次数映射表，生成x轴高度映射表的记录
         */
        TreeMap<Integer, Integer> hightTimesMap = new TreeMap<>();
        TreeMap<Integer, Integer> xMaxHightMap = new TreeMap<>();
        for (int i = 0; i < ops.length; i++) {
            Op curOp = ops[i];
            if (curOp.isAdd) {
                if (hightTimesMap.containsKey(curOp.hight)) {
                    hightTimesMap.put(curOp.hight, hightTimesMap.get(curOp.hight) + 1);
                } else {
                    hightTimesMap.put(curOp.hight, 1);
                }
            } else {
                if (hightTimesMap.get(curOp.hight) == 1) {
                    hightTimesMap.remove(curOp.hight);
                } else {
                    hightTimesMap.put(curOp.hight, hightTimesMap.get(curOp.hight) - 1);
                }
            }
            if (hightTimesMap.isEmpty()) {
                xMaxHightMap.put(curOp.x, 0);
            } else {
                xMaxHightMap.put(curOp.x, hightTimesMap.lastKey());
            }
        }

        /*
        3、根据x轴位置高度映射表，生成轮廓线数组，xMaxHightMap => res
        记录开始位置start
        前一个高度preHight
        当高度不相等时，就可以结算
         */
        List<List<Integer>> res = new ArrayList<>();
        int start = 0;
        int preHight = 0;
        for (Map.Entry<Integer, Integer> entry : xMaxHightMap.entrySet()) {
            if (preHight != entry.getValue()) {
                if (preHight != 0) {
                    res.add(new ArrayList<>(Arrays.asList(start, entry.getKey(), preHight)));
                }
                start = entry.getKey();
                preHight = entry.getValue();
            }
        }
        return res;
    }

}
