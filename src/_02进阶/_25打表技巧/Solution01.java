package _02进阶._25打表技巧;

/**
 * 小虎去买苹果，商店只提供两种类型的塑料袋，每种类型都有任意数量。
 * 1. 能装下 6 个苹果的袋子，
 * 2. 能装下 8 个苹果的袋子。
 * 小虎可以自由使用两种袋子来装苹果，但是小虎有强迫症，他要求自己使用的袋子数量必须最少，且使用的每个袋子必须装满。
 * 给定一个正整数 N，返回至少使用多少袋子。如果 N 无法让使用的每个袋子必须装满，返回 - 1
 * Created by huangjunyi on 2022/9/11.
 */
public class Solution01 {

    /**
     * 第一步：打印所有的解，寻找规律
     */
    public static void printAllResult() {
        for (int i = 0; i <= 100; i++) {
            int a = i / 8;
            int b = i % 8;
            if (b == 0) {
                //System.out.println(i + "  " + a + "  " + 0 + "  " + a);
                System.out.println(i + " " + a);
                continue;
            }
            if (b == 6) {
                System.out.println(i + " " + (a + 1));
                continue;
            }
            boolean flag = true;
            for (int j = 1; j <= a; j++) {
                if (((j * 8) + i % 8) % 6 == 0) {
                    System.out.println(i + " " + ((a - j) + ((j * 8) + i % 8) / 6));
                    flag = false;
                    break;
                }
            }
            if (flag) System.out.println(i + " " + (-1));
        }
    }

    /**
     0 0
     1 -1
     2 -1
     3 -1
     4 -1
     5 -1
     6 1
     7 -1
     8 1
     9 -1
     10 -1
     11 -1
     12 2
     13 -1
     14 2
     15 -1
     16 2
     17 -1

     18 3
     19 -1
     20 3
     21 -1
     22 3
     23 -1
     24 3
     25 -1

     26 4
     27 -1
     28 4
     29 -1
     30 4
     31 -1
     32 4
     33 -1

     34 5
     35 -1
     36 5
     37 -1
     38 5
     39 -1
     40 5
     41 -1

     42 6
     43 -1
     44 6
     45 -1
     46 6
     47 -1
     48 6
     49 -1

     50 7
     51 -1
     52 7
     53 -1
     54 7
     55 -1
     56 7
     57 -1

     58 8
     59 -1
     60 8
     61 -1
     62 8
     63 -1
     64 8
     65 -1
     * @param apple
     * @return
     */
    public static int minBages(int apple) {
        /*
        根据打表返回的数列，找出规律，按规律分情况返回结果
         */
        if (apple < 0) return -1;
        if ((apple & 1) != 0) return -1;
        if (apple < 18) {
            switch (apple) {
                case 0: return 0;
                case 6: return 1;
                case 8: return 1;
                case 12: return 2;
                case 14: return 2;
                case 16: return 2;
                default: return -1;
            }
        }
        return (apple - 18) / 8 + 3;
    }

    public static void main(String[] args) {
        printAllResult();
    }

}
