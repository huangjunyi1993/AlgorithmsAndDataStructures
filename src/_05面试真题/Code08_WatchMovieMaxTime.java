package _05面试真题;


import java.util.Arrays;

/**
 * 来自小红书
 * 一场电影开始和结束时间可以用一个小数组来表示["07:30","12:00"]
 * 已知有2000场电影开始和结束都在同一天，这一天从00:00开始到23:59结束
 * 一定要选3场完全不冲突的电影来观看，返回最大的观影时间
 * 如果无法选出3场完全不冲突的电影来观看，返回-1
 * Created by huangjunyi on 2023/1/18.
 */
public class Code08_WatchMovieMaxTime {

    public static int maxEnjoy(int[][] movies) {
        // 排序，开始时间从小到大，开始时间相同，结束时间从小到大
        Arrays.sort(movies, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        // 最晚结束的电影结束时间
        int max = 0;
        for (int[] movie : movies) {
            max = Math.max(max, movie[1]);
        }
        // 初始化dp表为-2，-2代表没求过答案
        int[][][] dp = new int[movies.length + 1][max + 1][4];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < (max + 1); j++) {
                for (int k = 0; k < 4; k++) {
                    dp[i][j][k] = -2;
                }
            }
        }
        return process(movies, 0, 0, 3, dp);
    }

    /**
     * 从index号电影开始挑选，现在来到time时间点，剩rest场电影没有挑选，返回最大的观影时长
     * @param movies 电影数组
     * @param index 电影号（数组下标）
     * @param time 时间点
     * @param rest 剩余待挑选的电影数
     * @param dp
     * @return 最大的观影时长（index号电影往后，挑选rest场电影，并且电影开始时间不早于time时间点）
     */
    private static int process(int[][] movies, int index, int time, int rest, int[][][] dp) {
        if (dp[index][time][rest] != -2) return dp[index][time][rest];
        if (index == movies.length) {
            dp[index][time][rest] = rest == 0 ? 0 : -1;
            return rest == 0 ? 0 : -1;
        }
        // index电影不要
        int res = process(movies, index + 1, time, rest, dp);
        if (movies[index][0] >= time && rest > 0) {
            // index电影要
            int next = process(movies, index + 1, movies[index][1], rest - 1, dp);
            if (next != - 1) {
                // PK
                res = Math.max(res, next + (movies[index][1] - movies[index][0]));
            }
        }
        dp[index][time][rest] = res;
        return res;
    }

    // 暴力方法，枚举前三场所有的可能全排列，用于测试
    public static int maxEnjoyTest(int[][] movies) {
        if (movies.length < 3) {
            return -1;
        }
        return processTest(movies, 0);
    }

    public static int processTest(int[][] movies, int index) {
        if (index == 3) {
            int start = 0;
            int watch = 0;
            for (int i = 0; i < 3; i++) {
                if (start > movies[i][0]) {
                    return -1;
                }
                watch += movies[i][1] - movies[i][0];
                start = movies[i][1];
            }
            return watch;
        } else {
            int ans = -1;
            for (int i = index; i < movies.length; i++) {
                swap(movies, index, i);
                ans = Math.max(ans, processTest(movies, index + 1));
                swap(movies, index, i);
            }
            return ans;
        }
    }

    public static void swap(int[][] movies, int i, int j) {
        int[] tmp = movies[i];
        movies[i] = movies[j];
        movies[j] = tmp;
    }

    // 为了测试
    public static int[][] randomMovies(int len, int time) {
        int[][] movies = new int[len][2];
        for (int i = 0; i < len; i++) {
            int a = (int) (Math.random() * time);
            int b = (int) (Math.random() * time);
            movies[i][0] = Math.min(a, b);
            movies[i][1] = Math.max(a, b);
        }
        return movies;
    }

    public static void main(String[] args) {
        int n = 10;
        int t = 20;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * n) + 1;
            int[][] movies = randomMovies(len, t);
            int ans1 = maxEnjoy(movies);
            int ans2 = maxEnjoyTest(movies);
            int ans3 = maxEnjoy(movies);
            if (ans1 != ans2 || ans1 != ans3) {
                for (int[] m : movies) {
                    System.out.println(m[0] + " , " + m[1]);
                }
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("出错了");
            }
        }
        System.out.println("测试结束");
    }

}
