package _02进阶._24线段树;

import java.util.*;

/**
 * 给定一个矩形数组，求其中最大的重叠层数
 * Created by huangjunyi on 2022/9/11.
 */
public class SegmentTree04 {

    private static class Rectangle {
        public int up;
        public int down;
        public int left;
        public int right;

        public Rectangle(int up, int down, int left, int right) {
            this.up = up;
            this.down = down;
            this.left = left;
            this.right = right;
        }

    }

    public static int getMaxCover(Rectangle[] rectangles) {
        //按照底边从小到大排序
        Arrays.sort(rectangles, Comparator.comparingInt(o -> o.down));
        //一个按照左边从小打到排序的有序集合
        TreeSet<Rectangle> treeLeftSorted = new TreeSet<>(Comparator.comparingInt(o -> o.left));
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < rectangles.length; i++) {
            treeLeftSorted.add(rectangles[i]);
            //底边同一水平线的，放一批处理
            while (i + 1 < rectangles.length && rectangles[i + 1].down == rectangles[i].down) treeLeftSorted.add(rectangles[++i]);

            //从treeLeftSorted中删除顶边不和当前矩形的底边重合的矩形
            List<Rectangle> removes = new ArrayList<>();
            for (Rectangle rectangle : treeLeftSorted) {
                if (rectangle.up <= rectangles[i].down) removes.add(rectangle);
            }
            treeLeftSorted.removeAll(removes);

            //一个按照右边进行排序的集合
            TreeSet<Rectangle> treeRightSorted = new TreeSet<>(Comparator.comparingInt(o -> o.right));
            for (Rectangle rectangle : treeLeftSorted) {
                //删除treeRightSorted中右边界覆盖不到当前矩形的矩形
                List<Rectangle> removes1 = new ArrayList<>();
                for (Rectangle rectangle1 : treeRightSorted) {
                    if (rectangle1.right <= rectangle.left) removes1.add(rectangle1);
                }
                treeRightSorted.removeAll(removes1);

                //把矩形添加到treeRightSorted，此时treeRightSorted的大小就是当前矩形的重叠层数
                treeRightSorted.add(rectangle);
                max = Math.max(max, treeRightSorted.size());
            }
        }
        return max;
    }

}
