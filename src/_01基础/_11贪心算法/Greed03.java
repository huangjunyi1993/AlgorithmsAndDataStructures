package _01基础._11贪心算法;

/**
 * 街道放灯问题
 * 给定一个字符串，代表一条街道，字符串中只有"."或者"x"两种字符，
 * "."代表居民楼，需要照亮并且可以放灯，灯可以照亮当前位置，以及前面后后面一个位置
 * "x"代表墙，不需要照亮并且无法放灯
 * 如果放置灯才使得放置数量最少
 * 返回最少放灯数
 */
public class Greed03 {

    public static int process(String road) {
        char[] chars = road.toCharArray();

        int result = 0;
        int index = 0;

        while (index < road.length()) {
            //如果当前位置时墙，直接跳过
            if (chars[index] == 'x') index++;
            else {
                //如果当前位置是灯，必然会放灯
                result++;
                //没有下一个位置，跳出循环
                if (index + 1 == road.length()) break;
                else {
                    //下一个位置是墙，那么等必须放在当前位置，然后跳到下下个位置
                    if (chars[index + 1] == 'x') index += 2;
                    //否则等放到下个位置，然后跳到下下下个位置
                    else index += 3;
                }
            }
        }
        return result;
    }

}
