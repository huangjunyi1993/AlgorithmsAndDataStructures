package _03经典面试题目.双指针;

import java.util.Arrays;

/**
 * 给定一个正数数组arr，代表若干人的体重
 * 再给定一个正数limit，表示所有船共同拥有的载重量
 * 每艘船最多坐两人，且不能超过载重
 * 想让所有的人同时过河，并且用最好的分配方法让船尽量少
 * 返回最少的船数
 * 测试链接 : https://leetcode.com/problems/boats-to-save-people/
 * Created by huangjunyi on 2022/12/24.
 */
public class _03BoatsToSavePeople {
    class Solution {
        public int numRescueBoats(int[] people, int limit) {
            /*
            左右指针
            如果两指针体重加一起超了，右指针左飘
            超的人自己坐一船，res++
            如果两指针体重加一起没超，左右指针中间靠1位
            两人坐一船，res++
             */
            Arrays.sort(people);
            int l = 0;
            int r = people.length - 1;
            int res = 0;
            while (l <= r) {
                int sum = l == r ? people[l] : people[l] + people[r];
                if (sum > limit) {
                    r--;
                } else {
                    l++;
                    r--;
                }
                res++;
            }
            return res;
        }
    }
}
