package _03经典面试题目.位运算;

import java.util.HashSet;

/**
 * 只由小写字母（a~z）组成的一批字符串，都放在字符类型的数组String[] arr中，
 * 如果其中某两个字符串，所含有的字符种类完全一样，就将两个字符串算作一类 比如：baacba和bac就算作一类
 * 虽然长度不一样，但是所含字符的种类完全一样（a、b、c） 返回arr中有多少类？
 * Created by huangjunyi on 2022/12/24.
 */
public class _03HowManyTypes {
    public static int types(String[] arr) {
        HashSet<Integer> types = new HashSet<>();
        for (String s : arr) {
            char[] chs = s.toCharArray();
            int type = 0;
            for (int i = 0; i < chs.length; i++) {
                type |= (1 << (chs[i] - 'a'));
            }
            types.add(type);
        }
        return types.size();
    }
}
