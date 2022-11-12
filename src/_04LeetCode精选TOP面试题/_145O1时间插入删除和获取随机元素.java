package _04LeetCode精选TOP面试题;

import java.util.HashMap;

/**
 * Created by huangjunyi on 2022/11/12.
 */
public class _145O1时间插入删除和获取随机元素 {
    class RandomizedSet {

        HashMap<Integer, Integer> numIndexMap;
        HashMap<Integer, Integer> indexNumMap;
        int size;

        public RandomizedSet() {
            numIndexMap = new HashMap<>();
            indexNumMap = new HashMap<>();
            size = 0;
        }

        public boolean insert(int val) {
            if (numIndexMap.containsKey(val)) return false;
            numIndexMap.put(val, size); // 记录数都下标的关系
            indexNumMap.put(size, val); // 记录下标到数的关系
            size++;
            return true;
        }

        /**
         * 为了下标保证连续，最后一个数，覆盖要删除的位置
         * @param val
         * @return
         */
        public boolean remove(int val) {
            if (!numIndexMap.containsKey(val)) return false;
            Integer deleteNumIndex = numIndexMap.get(val);
            size--;
            Integer lastNum = indexNumMap.get(size);
            numIndexMap.put(lastNum, deleteNumIndex);
            numIndexMap.remove(val);
            indexNumMap.put(deleteNumIndex, lastNum);
            indexNumMap.remove(size);
            return true;
        }

        /**
         * 利用size取random作为index，返回对应num
         * @return
         */
        public int getRandom() {
            if (size == 0) return -1;
            return indexNumMap.get((int) (Math.random() * size));
        }
    }

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
}
