package _06LeetCode热题HOT100;

/**
 * https://leetcode.cn/problems/decode-string/?favorite=2cktkvj
 *
 * Created by huangjunyi on 2022/11/13.
 */
public class _394字符串解码 {
    class Solution {
        class Info {
            String res; // 返回的答案
            int end; // 算到什么位置

            public Info(String res, int end) {
                this.res = res;
                this.end = end;
            }
        }
        public String decodeString(String s) {
            char[] chs = s.toCharArray();
            Info info = process(chs, 0);
            return info.res;
        }

        /**
         * 从i位置开始往下计算，遇到终止位置或者右括号则返回
         *
         * 递归嵌套结构
         *
         * @param chs
         * @param i
         * @return
         */
        private Info process(char[] chs, int i) {
            StringBuffer res = new StringBuffer();
            int times = 0;
            while (!(i == chs.length || chs[i] == ']')) {
                if (chs[i] >= 'a' && chs[i] <= 'z') {
                    // 遇到字母，直接拼接
                    res.append(chs[i]);
                    i++;
                } else if (chs[i] >= '0' && chs[i] <= '9') {
                    // 遇到数字，记录子过程返回答案的拼接次数
                    times = times * 10 + (chs[i] - '0');
                    i++;
                } else {
                    // 遇到左括号，递归调用子过程，收集答案
                    Info subInfo = process(chs, i + 1);
                    // 子过程返回的答案，拼接times次
                    for (int time = 0; time < times; time++) {
                        res.append(subInfo.res);
                    }
                    times = 0;
                    i = subInfo.end + 1;
                }
            }
            return new Info(res.toString(), i);
        }
    }
}
