package _04LeetCode精选TOP面试题.数学;

/**
 * https://leetcode.cn/problems/count-primes/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/5.
 */
public class  _099计数质数 {
    class Solution {
        public int countPrimes(int n) {
            /*
            从2遍历到根号n
            如果已经被标记过不是质数，则不处理
            如果没有被标记过不是质数，则处理
            比如遍历到x，处理逻辑是把把2x，3x，4x...都标记为不是质数
            但是2不需要处理
            只要初始化的质数数量，初始化为n/2，就可以，相当于已经砍一半，砍的就是那些偶数

            然后因为处理2时已经处理过所有偶数，所以只需从3开始遍历，遍历所有奇数

            然后每次处理去标记不是质数时，只需从x*x开始标，步长是2x，因为中间的已经被标记过
            例如遍历到3，则从9开始，然后是15，...，中间的6，12都在处理2的时候被标记过
             */
            if (n < 3) return 0;
            // true 不是质数，false 是质数或者没标记过
            boolean[] f = new boolean[n];
            int count = n / 2; // count，质数数量，扣除所有偶数（2除外）
            for (int i = 3; i * i < n; i += 2) {
                if (f[i]) continue;
                for (int j = i * i; j < n; j += 2 * i) {
                    // 乘出来的j，都是非质数，没标记为非质数的，要标记并扣减count
                    if (!f[j]) {
                        f[j] = true;
                        // 发现一个非质数，质数数量减一
                        count--;
                    }
                }
            }
            return count;
        }
    }
}
