package _03经典面试题目.有序表;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * 每种工作有难度和报酬，规定如下：
 * class Job {
 * public int money;
 * public int hard;
 * }
 * 给定一个Job类型的数据jobarr，表示所有岗位，每个岗位的都可以提供任意份工作，选工作的标准是在难度不超过自身 能力值的情况下，
 * 选择报酬最高的岗位，给定一个int类型的数组arr，表示所有人的能力，返回int类型的数组，表示每个人按照标准选工作后所能获得的最高报酬
 * Created by huangjunyi on 2022/9/23.
 */
public class _01Job {

    class Job {
        public int money;
        public int hard;
    }

    public static int[] process(Job[] jobarr, int[] arr) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        //根据难度从小到大对工作进行排序，难度相同则报酬从大到小
        Arrays.sort(jobarr, (o1, o2) -> o1.hard - o2.hard != 0 ? o1.hard - o2.hard : o2.money - o1.money);
        map.put(jobarr[0].hard, jobarr[0].money);
        int preHard = jobarr[0].hard;
        int preMoney = jobarr[0].money;
        //只保留难度相等的工作中报酬最大的，并且随着难度增大报酬增大的工作（过滤垃圾数据），放入有序表中
        for (int i = 1; i < jobarr.length; i++) {
            if (jobarr[i].hard != preHard && jobarr[i].money > preMoney) {
                map.put(jobarr[i].hard, jobarr[i].money);
                preHard = jobarr[i].hard;
                preMoney = jobarr[i].money;
            }
        }
        //遍历每一个人，从有序表中找到小于等于该人能力值的工作中最接近该人能力值的key，取得报酬累加到结果中
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (map.floorKey(arr[i]) != null) {
                res[i] = map.get(map.floorKey(arr[i]));
            }
        }
        return res;
    }

}
