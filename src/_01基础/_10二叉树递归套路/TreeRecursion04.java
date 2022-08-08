package _01基础._10二叉树递归套路;

import java.util.List;

/**
 * 开心派对问题：
 * 一个公司开派对，可以决定某个员工来或者不来
 * 如果一个员工来，则直接下级不能来
 * 
 * 每个员工都有自己的快乐值，0个或者若干个直接下级
 * 
 * 求派对的最大快乐值（所有来参加派对的员工的快乐值之和）
 */
public class TreeRecursion04 {
    
    public static int findMaxHappy(Employee boss) {
        Info info = process(boss);
        return Math.max(info.maxHappy1, info.maxHappy2);
    }

    private static Info process(Employee employee) {
        if (employee.nexts.isEmpty()) {
            Info info = new Info();
            info.maxHappy1 = employee.happy;
            info.maxHappy2 = 0;
            return info;
        }

        int maxHappy1 = 0; //该员工来时，子树的最大快乐值
        int maxHappy2 = 0; //该员工不来，子树的最大快乐值

        for (Employee next : employee.nexts) {
            Info nextInfo = process(next);
            maxHappy1 += nextInfo.maxHappy2; //当前员工来，直接下级不能来，当前员工的快乐值+直接下级不来时最大快乐值
            maxHappy2 += Math.max(nextInfo.maxHappy1, nextInfo.maxHappy2); //当前员工不来，则取直接下级来时和不来的最大快乐值中的最大值
        }

        Info info = new Info();
        info.maxHappy1 = maxHappy1;
        info.maxHappy2 = maxHappy2;
        return info;
    }

    private static class Info {
        private int maxHappy1; //该员工来时，子树的最大快乐值
        private int maxHappy2; //该员工不来，子树的最大快乐值
    }
    
    private static class Employee {
        private int happy;
        private List<Employee> nexts;
    }
    
}
