package _05面试真题;

import java.util.LinkedList;

/**
 * 一张扑克有3个属性，每种属性有3种值（A、B、C）
 * 比如"AAA"，第一个属性值A，第二个属性值A，第三个属性值A
 * 比如"BCA"，第一个属性值B，第二个属性值C，第三个属性值A
 * 给定一个字符串类型的数组cards[]，每一个字符串代表一张扑克
 * 从中挑选三张扑克，每种属性达标的条件是：这个属性在三张扑克中全一样，或全不一样
 * 挑选的三张扑克达标的要求是：每种属性都满足上面的条件
 * 比如："ABC"、"CBC"、"BBC"
 * 第一张第一个属性为"A"、第二张第一个属性为"C"、第三张第一个属性为"B"，全不一样
 * 第一张第二个属性为"B"、第二张第二个属性为"B"、第三张第二个属性为"B"，全一样
 * 第一张第三个属性为"C"、第二张第三个属性为"C"、第三张第三个属性为"C"，全一样
 * 每种属性都满足在三张扑克中全一样，或全不一样，所以这三张扑克达标
 * 返回在cards[]中任意挑选三张扑克，达标的方法数
 * */
public class Code05_CardsProblem {

    public static int ways2(String[] cards) {
        // 把纸牌的属性转成数字，作为下标，统计不同纸牌出现的张数，一共有27种纸牌
        int[] count = new int[27];
        for (String card : cards) {
            char[] chs = card.toCharArray();
            int num = (chs[0] - 'A') * 9 + (chs[1] - 'A') * 3 + (chs[2] - 'A') * 1;
            count[num]++;
        }

        int ways = 0;
        // 统计3张牌全部相同的中方法数
        for (int n : count) {
            if (n > 2) {
                ways += n == 3 ? 1 : (n * (n - 1) * (n  - 2)) / 6;
            }
        }

        // 枚举所有牌的组合情况
        LinkedList<Integer> path = new LinkedList<>();
        for (int i = 0; i < count.length; i++) {
            if (count[i] == 0) continue;
            path.addLast(i);
            ways += process2(count, i + 1, path);
            path.pollLast();
        }
        return ways;
    }

    private static int process2(int[] count, int inedex, LinkedList<Integer> path) {
        // 判断是否合法，返回方法数
        if (path.size() == 3) return getWays2(count, path);
        int ways = 0;
        for (int i = inedex; i < count.length; i++) {
            if (count[i] == 0) continue;
            path.addLast(i);
            ways += process2(count, i + 1, path);
            path.pollLast();
        }
        return ways;
    }

    /**
     * 判断3张牌是否合法，并返回方法数
     * @param count
     * @param path
     * @return
     */
    private static int getWays2(int[] count, LinkedList<Integer> path) {
        int num1 = path.get(0);
        int num2 = path.get(1);
        int num3 = path.get(2);
        // 取出3张排的3个属性，进行判断
        for (int i = 9; i > 0; i /= 3) {
            int cur1 = num1 / i;
            int cur2 = num2 / i;
            int cur3 = num3 / i;
            num1 %= i;
            num2 %= i;
            num3 %= i;
            if ((cur1 != cur2 && cur1 != cur3 && cur2 != cur3) ||(cur1 == cur2 && cur2 == cur3)) continue;
            return 0;
        }
        num1 = path.get(0);
        num2 = path.get(1);
        num3 = path.get(2);
        return count[num1] * count[num2] * count[num3];
    }

    public static int ways1(String[] cards) {
        LinkedList<String> picks = new LinkedList<>();
        return process1(cards, 0, picks);
    }

    public static int process1(String[] cards, int index, LinkedList<String> picks) {
        if (picks.size() == 3) {
            return getWays1(picks);
        }
        if (index == cards.length) {
            return 0;
        }
        int ways = process1(cards, index + 1, picks);
        picks.addLast(cards[index]);
        ways += process1(cards, index + 1, picks);
        picks.pollLast();
        return ways;
    }

    public static int getWays1(LinkedList<String> picks) {
        char[] s1 = picks.get(0).toCharArray();
        char[] s2 = picks.get(1).toCharArray();
        char[] s3 = picks.get(2).toCharArray();
        for (int i = 0; i < 3; i++) {
            if ((s1[i] != s2[i] && s1[i] != s3[i] && s2[i] != s3[i]) || (s1[i] == s2[i] && s1[i] == s3[i])) {
                continue;
            }
            return 0;
        }
        return 1;
    }

    // for test
    public static String[] generateCards(int size) {
        int n = (int) (Math.random() * size) + 3;
        String[] ans = new String[n];
        for (int i = 0; i < n; i++) {
            char cha0 = (char) ((int) (Math.random() * 3) + 'A');
            char cha1 = (char) ((int) (Math.random() * 3) + 'A');
            char cha2 = (char) ((int) (Math.random() * 3) + 'A');
            ans[i] = String.valueOf(cha0) + String.valueOf(cha1) + String.valueOf(cha2);
        }
        return ans;
    }

    // for test
    public static void main(String[] args) {
        int size = 20;
        int testTime = 100000;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            String[] arr = generateCards(size);
            int ans1 = ways1(arr);
            int ans2 = ways2(arr);
            if (ans1 != ans2) {
                for (String str : arr) {
                    System.out.println(str);
                }
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("test finish");

        long start = 0;
        long end = 0;
        String[] arr = generateCards(10000000);
        System.out.println("arr size : " + arr.length + " runtime test begin");
        start = System.currentTimeMillis();
        ways2(arr);
        end = System.currentTimeMillis();
        System.out.println("run time : " + (end - start) + " ms");
        System.out.println("runtime test end");
    }
}
