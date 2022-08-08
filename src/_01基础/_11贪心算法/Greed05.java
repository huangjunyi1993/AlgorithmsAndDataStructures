package _01基础._11贪心算法;

import java.util.PriorityQueue;

/**
 * 一个人做项目
 * 给定一个整形数组costArr，代表项目启动资金，
 * 一个整形数组profitsArr，代表项目利润
 * 一个整形k表示最多只能做k个项目，
 * 一个整形w表示初始资金
 */
public class Greed05 {

    public static int process(int[] costArr, int[] profitsArr, int k, int w) {
        //按照花费排序的小跟堆
        PriorityQueue<Project> minCostProjects = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        //按照利润排序的大根堆
        PriorityQueue<Project> maxProfitsProjects = new PriorityQueue<>((o1, o2) -> o2.profits - o1.profits);
        //组装成项目放入小根堆
        for (int i = 0; i < costArr.length; i++) {
            Project project = new Project();
            project.cost = costArr[i];
            project.profits = profitsArr[i];
            minCostProjects.add(project);
        }

        for (int i = 0; i < k; i++) {
            //把启动资金小于等于当前资金的项目，全部放入大根堆
            while (!minCostProjects.isEmpty() && w >= minCostProjects.peek().cost) maxProfitsProjects.add(minCostProjects.poll());
            //没有项目可做，提前结束
            if (maxProfitsProjects.isEmpty()) break;
            //从大根堆中挑出利润最大的项目
            w += maxProfitsProjects.poll().profits;
        }
        return w;
    }

    public static class Project {
        private int cost; //项目花费
        private int profits; //项目利润
    }

}
