package _02进阶._20KMP算法;

/**
 * 给定两个字符串，判断是否互为旋转数
 * 例如123456，则123456是123456的旋转数，
 * 234561也是123456的旋转数
 * 345612也是123456的旋转数
 * Created by huangjunyi on 2022/9/5.
 */
public class KMP02 {

    public static boolean isRotationNumber(String str1, String str2) {

        str1 = str1 + str1;

        if (KMP01.getIndexOf(str1, str2) != -1) return true;
        return false;
    }

}
