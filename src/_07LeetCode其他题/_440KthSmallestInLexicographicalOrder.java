package _07LeetCode其他题;

// O(logN)的解
public class _440KthSmallestInLexicographicalOrder {
	class Solution {
		public int[] offset = { 0, 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000 };

		public int[] number = { 0, 1, 11, 111, 1111, 11111, 111111, 1111111, 11111111, 111111111, 1111111111 };

		public int findKthNumber(int n, int k) {
			// 数字num，有几位，len位
			// 65237, 5位，len = 5
			int len = len(n);
			// 65237, 开头数字，6，first
			int first = n / offset[len];
			// 65237，左边有几个？
			int left = (first - 1) * number[len];

		/*
		65327
		left|mid|right
		left就是1开头、2、3、4、5开头，5位，几个
		11111 * 5
		mid，6开头，中间，有几个
		right,7、8、9开头，4位，几个
		 */

			int pick = 0;
			int already = 0;
			if (k <= left) {
				// k / a 向上取整-> (k + a - 1) / a
				// pick，向上取整找到k的开头的数字
				// 例如n是65237，k是43210，那么取到的pick就是4
				// 那么是4开头的第几位呢？4 - 33333
				// already就是33333
				pick = (k + number[len] - 1) / number[len];
				already = (pick - 1) * number[len];
				// 这里的(pick + 1) * offset[len] - 1，因为是左边，所以不受num约束
				// 那么这里如果是3开头，就39999
				return kth((pick + 1) * offset[len] - 1, len, k - already);
			}
			// mid，65327，1111 + 5257 + 1
			// 1111是6开头，4位，几个
			// 5257 + 1，是60000~65237，一共5238个数
			int mid = number[len - 1] + (n % offset[len]) + 1;
			if (k - left <= mid) {
				// 中间的受num约束，max传num（n）
				return kth(n, len, k - left);
			}
			// 在right侧
			k -= left + mid;
			len--;
			pick = (k + number[len] - 1) / number[len] + first;
			already = (pick - first - 1) * number[len];
			// 这里的(pick + 1) * offset[len] - 1，因为是左边，所以不受num约束
			// 那么这里如果是7开头，就7999
			return kth((pick + 1) * offset[len] - 1, len, k - already);
		}

		public int len(int n) {
			int len = 0;
			while (n != 0) {
				n /= 10;
				len++;
			}
			return len;
		}

		/**
		 * 比如kth(653, 3, k)，必须6开头，小于等于3位并且不超过653，字典序第k个的数是？
		 *
		 * kth(65327, 5, 23014)
		 * 那么单独看6是不是，如果k是1，那么1-1=0，那么6就是
		 * 不是，那么就看6后面，
		 * 0开头的小于等于4位的几个，60xxx
		 * 1开头的小于等于4位的几个，61xxx
		 * 2开头的小于等于4位的几个，62xxx
		 * 3开头的小于等于4位的几个，63xxx
		 * 4开头的小于等于4位的几个，64xxx
		 * 5开头的小于等于4位的几个，但是5又有一个断层
		 *
		 * 跟主逻辑一样，分left|mid|right
		 * left：60xxx~64xxx
		 * mid：65xxx
		 * right：67xxx~69xxx
		 * 然后len就是4（5-1）
		 *
		 * 如果k来到中间，那么继续分左中右
		 * 否则不需要再分左中右，并且max不需要再管
		 *
		 * @param max 不能超过max
		 * @param len 位数
		 * @param kth 字典序第k个
		 */
		public int kth(int max, int len, int kth) {
			// 中间范围还管不管的着！
			// 有任何一步，中间位置没命中，左或者右命中了，那以后就都管不着了！
			// 但是开始时，肯定是管的着的！
			boolean closeToMax = true;
			int ans = max / offset[len];
			while (--kth > 0) {
				max %= offset[len--];
				// pick，当前位是啥
				int pick = 0;
				if (!closeToMax) {
					// k来到左侧或者右侧，
					// 不需要再理会max，
					// 继续定位下去（每一位取什么数）
					pick = (kth - 1) / number[len];
					ans = ans * 10 + pick;
					kth -= pick * number[len];
				} else {
					int first = max / offset[len];
					int left = first * number[len];
					if (kth <= left) {
						closeToMax = false;
						pick = (kth - 1) / number[len];
						ans = ans * 10 + pick;
						kth -= pick * number[len];
						continue;
					}
					kth -= left;
					int mid = number[len - 1] + (max % offset[len]) + 1;
					if (kth <= mid) {
						ans = ans * 10 + first;
						continue;
					}
					closeToMax = false;
					kth -= mid;
					len--;
					pick = (kth + number[len] - 1) / number[len] + first;
					ans = ans * 10 + pick;
					kth -= (pick - first - 1) * number[len];
				}
			}
			return ans;
		}
	}
}
