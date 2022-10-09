package _03经典面试题目.未分类;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个数组arr和整数k，arr长度为N，如果有某些数出现次数超过了N/K，打印这些数，如果没有不打印
 *
 * Created by huangjunyi on 2022/10/6.
 */
public class _11PrintKMajor {

    public static void majorityElement(int[] arr, int k) {
        if (k < 2) {
            System.out.println("input invalid.");
            return;
        }

        /*
        如果k为3，则最多只有2个数超N/k
        如果k为4，则最多只有3个数超N/k
        因此候选人大小为k-1
        如果map中包含遍历到的数，则该数的HP加1
        如果候选人map没满，则把遍历到的数加入map中
        如果map满了，遍历到的数又不是map中任意一个，则该数不要，所有候选人HP减一
        HP减到0的候选人从map删除
         */
        Map<Integer, Integer> cand = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (cand.containsKey(arr[i])) {
                cand.put(arr[i], cand.get(arr[i]) + 1);
            } else {
                if (cand.size() == k - 1) {
                    for (Map.Entry<Integer, Integer> entry : cand.entrySet()) {
                        if (entry.getValue() == 1) {
                            cand.remove(entry.getKey());
                        } else {
                            cand.put(entry.getKey(), entry.getValue() - 1);
                        }
                    }
                } else {
                    cand.put(arr[i], 1);
                }
            }
        }

        /*
        最后还有验证选出的候选人
        是否个数超N/k
        只打印个数超N/k的数
         */
        Map<Integer, Integer> real = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (cand.containsKey(arr[i])) {
                if (real.containsKey(arr[i])) {
                    real.put(arr[i], real.get(arr[i]) + 1);
                } else {
                    real.put(arr[i], 1);
                }
            }
        }

        boolean hasPrint = false;
        for (Map.Entry<Integer, Integer> entry : cand.entrySet()) {
            if (real.get(entry.getKey()) > arr.length / k) {
                System.out.println(entry.getValue());
                hasPrint = true;
            }
        }

        if (!hasPrint) System.out.println("no such element");

    }

}
