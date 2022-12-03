package _05面试真题;

/**
 * 给定一个字符串数组，里面是创建数组和赋值的命令
 * 第一个字符串时数组创建的命令
 * 后面都是赋值的命令
 * 返回保存的命令的下标
 * 第一个命令不会报错
 * Created by huangjunyi on 2022/11/12.
 */
public class Code03_Array {
    public static int findError(String[] contents) {
        if (contents == null || contents.length == 0) return 0;
        String arrName = contents[0].substring(0, contents[0].indexOf("[")); // 获取数组名
        int arrLen = Integer.valueOf(contents[0].substring(contents[0].indexOf("[") + 1, contents[0].indexOf("]"))); // 获取数组长度
        int[] arr = new int[arrLen]; // 创建真实数组，一步步推演
        for (int i = 1; i < contents.length; i++) {

            // 按等号切分，取出两边的字符串
            String[] strs = contents[i].split("=");
            String leftStr = strs[0];
            String rightStr = strs[1];

            // 去掉数组名
            leftStr = leftStr.replaceAll(arrName, "");
            rightStr = rightStr.replaceAll(arrName, "");

            // 剥掉一层，在计算里面的值，看是要给哪个下标赋值
            leftStr = leftStr.substring(1, leftStr.length() - 1);

            // 计算两边真实的值
            Integer leftValue = value(arr, leftStr);
            Integer rightValue = value(arr, rightStr);

            // 返回null代表越界了，返回保存行号
            if (leftValue == null || rightValue == null) return i;

            // 要赋值下标的越界
            if (leftValue < 0 || leftValue >= arr.length) return i;

            // 执行命令
            arr[leftValue] = rightValue;
        }
        return 0;
    }

    /**
     * 接收形如[[[6]]]的串，返回所代表的值
     * @param arr 用于推演的真实数组
     * @param str 形如[[[6]]]的串
     * @return
     */
    private static Integer value(int[] arr, String str) {
        int num = Integer.valueOf(str.substring(str.lastIndexOf("[" + 1), str.indexOf("]"))); // 取出里面包裹的数
        int level = str.lastIndexOf("[") + 1; // 包了level层
        for (int i = 0; i < level; i++) {
            if (num < 0 || num >= arr.length) return null;
            num = arr[num];
        }
        return num;
    }

    public static void main(String[] args) {
        String[] contents = {
                "arr[7]",
                "arr[0]=6",
                "arr[1]=3",
                "arr[2]=1",
                "arr[3]=2",
                "arr[4]=4",
                "arr[5]=0",
                "arr[6]=5",
                "arr[arr[1]]=3",
                "arr[arr[arr[arr[5]]]]=arr[arr[arr[3]]]",
                "arr[arr[4]]=arr[arr[arr[0]]]",
                "arr[arr[1]] = 7",
                "arr[0] = arr[arr[arr[1]]]" };
        System.out.println(findError(contents));

    }
}
