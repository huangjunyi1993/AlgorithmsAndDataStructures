package _04LeetCode精选TOP面试题;


/**
 * https://leetcode.cn/problems/wiggle-sort-ii/?favorite=2ckc81c
 *
 * 步骤：
 * 1、荷兰国旗问题寻找中位数，并把数组partition好
 * 此时数组[L1,L2,L3,...,R1,R2,R3]右边的都比左边大
 * 2、如果是偶数长度：
 *  2.1、利用完美洗牌把数组洗成[R1,L1,R2.L2...]
 *  2.2、交换位置
 *   如果是奇数长度：
 *  0位置不动，后面的数进行完美洗牌
 *
 * Created by huangjunyi on 2022/11/11.
 */
public class _133摆动排序II {
    class Solution {
        public void wiggleSort(int[] nums) {
            if (nums == null || nums.length < 2) return;
            int N = nums.length;
            findMidNum(nums, 0, N - 1);
            if ((N & 1) == 0) {
                shuffle(nums, 0, N - 1);
                reverse(nums, 0, N - 1);
            } else {
                shuffle(nums, 1, N - 1);
            }
        }

        private void findMidNum(int[] nums, int l, int r) {
            int mid = l + ((r - l) >> 1);
            while (l < r) {
                int pivot = nums[l];
                int[] range = partition(nums, l, r, pivot);
                if (mid >= range[0] && mid <= range[1]) return;
                else if (mid < range[0]) r = range[0] - 1;
                else l = range[1] + 1;
            }
        }

        private int[] partition(int[] nums, int l, int r, int pivot) {
            int index = l;
            while (index <= r) {
                if (nums[index] < pivot) {
                    swap(nums, l, index);
                    l++;
                    index++;
                } else if (nums[index] > pivot) {
                    swap(nums, index, r);
                    r--;
                } else {
                    index++;
                }
            }
            return new int[]{l, r};
        }

        private void swap(int[] nums, int l, int r) {
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
        }

        private void shuffle(int[] arr, int L, int R) {

        /*
        假设有一个公式，可以根据当前下标和数组长度，计算出迁移后的下标

        那么洗牌最简单的就是下标循环迁移
        但是有时候有可能会有多个环，因为空间复杂度必须O(1)，
        所以不能用二外空间记录状态，因此无法确定都有哪些起始点，哪些起始点已经迁移完

        但是如果一个数组长度是 3^k - 1(k >= 1)，那么洗牌时，环的起始点就是1、3、9、27...
        所以就把数组分批进行迁移
        每次迁移最接近数组长度的 3^k - 1

        那是根据题意，要这么做，还需要把右方的 (3^k - 1) / 2 个数扭到左边，
        把左边(3^k - 1) / 2 个数之后的数扭到右边
        扭的方法就是，两边分别逆序，然后整体逆序

        然后迁移完成的数永不再动
        再对剩下的子数组用同样方法迁移
         */

            while (R - L + 1 > 0) {
                int S = R - L + 1;
                int base = 3;
                int k = 1;
            /*
            计算最接近数组长度的(3^k - 1)
            这里的数组长度是S，正确来讲应该是剩余没有迁移的数的个数
            记录k，表示有k个起始点 => (3^k - 1) 有k个起始点
             */
                while (base <= (S + 1) / 3) {
                    base *= 3;
                    k++;
                }
                int half = (base - 1) / 2;
                int mid = (L + R) / 2;
                // 数组扭转
                rotate(arr, L + half, mid, mid + half);
                // 下标循环迁移
                cycles(arr, L, base - 1, k);
                L = L + base - 1;
            }
        }

        private void cycles(int[] arr, int start, int len, int k) {
            for (int i = 0, trigger = 1; i < k; i++, trigger *= 3) {
                int pre = arr[start + trigger - 1];
                int cur = modifyIndex(trigger, len);
                while (cur != trigger) {
                    int tmp = arr[start + cur - 1];
                    arr[start + cur - 1] = pre;
                    pre = tmp;
                    cur = modifyIndex(cur, len);
                }
                arr[start + cur - 1] =  pre;
            }
        }

        /**
         * 下标计算公式
         * @param i 当前下标
         * @param len 子数组长度
         * @return
         */
        private int modifyIndex(int i, int len) {
            if (i <= len / 2) {
                return 2 * i;
            } else {
                return 2 * (i - (len / 2)) - 1;
            }
        }

        private void rotate(int[] arr, int L, int M, int R) {
            reverse(arr, L, M);
            reverse(arr, M + 1, R);
            reverse(arr, L, R);
        }

        private void reverse(int[] arr, int l, int r) {
            while (l < r) {
                int tmp = arr[l];
                arr[l] = arr[r];
                arr[r] = tmp;
                l++;
                r--;
            }
        }
    }
}
