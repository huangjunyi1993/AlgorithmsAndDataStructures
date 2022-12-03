package _02进阶._20KMP算法;

/**
 * 给定两个字符串，判断是否互为旋转数
 * 旋转串是指字符串左边的某部分挪到字符串右边去
 * 例如123456，则123456是123456的旋转数，
 * 234561也是123456的旋转数
 * 345612也是123456的旋转数
 *
 * Created by huangjunyi on 2022/9/5.
 */
public class KMP02 {

    public static boolean isRotationNumber(String str1, String str2) {

        // str1和str2长度不相等，str2不可能是str1的旋转串
        if (str1.length() != str2.length()) return false;

        // 两个str1拼接起来，生成2倍的str1
        str1 = str1 + str1;

        // 然后在新字符串中看是否能匹配到str2，能匹配到则表示str2是str1的旋转数
        // 因为两个str1拼接，中间包含了所有可能的旋转数了
        if (KMP01.getIndexOf(str1, str2) != -1) return true;
        return false;
    }

}
