package _01基础._07前缀树与计数排序与桶排序;

/**
 * 计数排序
 */
public class CountSort01 {

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) return;

        //找出数组中最大的数
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }

        //创建一个辅助数组
        int[] help = new int[max + 1];

        //遍历待排序的数组，值作为help数组的下标，help数组中的值+1
        for (int i = 0; i < arr.length; i++) {
            help[arr[i]]++;
        }

        //根据help数组，把数拷贝回原数组
        int index = 0;
        for (int i = 0; i < help.length; i++) {
            for (int j = 0; j < help[i]; j++) {
                arr[index++] = i;
            }
        }

    }

}
