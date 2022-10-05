package _03经典面试题目.动态规划;

/**
 * 给定一个只由 0(假)、1(真)、&(逻辑与)、|(逻辑或)和^(异或)五种字符组成 的字符串express，再给定一个布尔值 desired。
 * 返回express能有多少种组合 方式，可以达到desired的结果。
 *
 * express="1^0|0|1"，desired=false
 * 只有 1^((0|0)|1)和 1^(0|(0|1))的组合可以得到 false，返回 2。
 * express="1"，desired=false
 * 无组合则可以得到false，返回0
 * Created by huangjunyi on 2022/10/2.
 */
public class _013ExpressDesired {

    /**
     * 递归解法: 区间范围的尝试模型
     * @param express
     * @param desired
     * @return
     */
    public static int num1(String express, boolean desired) {
        if (express == null || express.length() == 0) return 0;
        char[] chars = express.toCharArray();
        //校验表达式是否合法
        if (!isValid(chars)) return 0;
        //获取0~len-1区间范围内的可能性
        return f(chars, desired, 0, chars.length - 1);
    }

    /**
     * 给定一个区间，返回该区间有多少种可能性
     * @param chars
     * @param desired
     * @param L
     * @param R
     * @return
     */
    private static int f(char[] chars, boolean desired, int L, int R) {
        //base case
        if (L == R) return chars[L] == '1' ? (desired ? 1 : 0) : (desired ? 0 : 1);
        int res = 0;
        /*
        遍历区间上的符号位
        根据符号位和desired的值，分表递归获取左右子区间的值
        然后相乘累加到结果值中
         */
        if (desired) {
            for (int i = L + 1; i < R; i += 2) {
                switch (chars[i]) {
                    case '&':
                        res += f(chars, true, L, i - 1) * f(chars, true, i + 1, R);
                        break;
                    case '|':
                        res += f(chars, true, L, i - 1) * f(chars, true, i + 1, R);
                        res += f(chars, true, L, i - 1) * f(chars, false, i + 1, R);
                        res += f(chars, false, L, i - 1) * f(chars, true, i + 1, R);
                        break;
                    case '^':
                        res += f(chars, true, L, i - 1) * f(chars, false, i + 1, R);
                        res += f(chars, false, L, i - 1) * f(chars, true, i + 1, R);
                        break;
                    default:
                }
            }
        } else {
            for (int i = L + 1; i < R; i += 2) {
                switch (chars[i]) {
                    case '&':
                        res += f(chars, true, L, i - 1) * f(chars, false, i + 1, R);
                        res += f(chars, false, L, i - 1) * f(chars, true, i + 1, R);
                        res += f(chars, false, L, i - 1) * f(chars, false, i + 1, R);
                        break;
                    case '|':
                        res += f(chars, false, L, i - 1) * f(chars, false, i + 1, R);
                        break;
                    case '^':
                        res += f(chars, true, L, i - 1) * f(chars, true, i + 1, R);
                        res += f(chars, false, L, i - 1) * f(chars, false, i + 1, R);
                        break;
                    default:
                }
            }
        }
        return res;
    }

    /**
     * 校验表达式是否符合规范
     *
     * 1、校验字符串是否长度为奇数
     * 2、偶数位上是否为0或1
     * 3、奇数为上是否为符号位
     * @param chars
     * @return
     */
    private static boolean isValid(char[] chars) {
        if ((chars.length & 1) == 0) return false;
        for (int i = 0; i < chars.length; i += 2) if (chars[i] != '0' && chars[i] != '1') return false;
        for (int i = 1; i < chars.length; i += 2) if (chars[i] != '&' && chars[i] != '|' && chars[i] != '^') return false;
        return true;
    }

    /**
     * 动态规划解法
     * @param express
     * @param desired
     * @return
     */
    public static int dp(String express, boolean desired) {
        char[] exp = express.toCharArray();
        int N = exp.length;
        /*
        true表
        tMap[row][col]表示express从row到col，有多少中划分方式使得结果为true
         */
        int[][] tMap = new int[N][N];
        /*
        false表
        fMap[row][col]表示express从row到col，有多少中划分方式使得结果为false
         */
        int[][] fMap = new int[N][N];

        /*
        根据递归的base-case
        填好两张表的对角线
         */
        for (int i = 0; i < N; i += 2) {
            tMap[i][i] = (exp[i] == '1' ? 1 : 0);
            fMap[i][i] = (exp[i] == '1' ? 0 : 1);
        }

        for (int row = N - 3; row >= 0; row -= 2) {
            for (int col = row + 2; col < N; col += 2) {
                // 遍历区间内的所有符号位，结果累加到tMap[row][col]和fMap[row][col]
                for (int i = row + 1; i < col; i += 2) {
                    // 根据递归的依赖关系进行填表
                    switch (exp[i]) {
                        case '&':
                            tMap[row][col] += tMap[row][i - 1] * tMap[i + 1][col];
                            break;
                        case '|':
                            tMap[row][col] += tMap[row][i - 1] * tMap[i + 1][col];
                            tMap[row][col] += tMap[row][i - 1] * fMap[i + 1][col];
                            tMap[row][col] += fMap[row][i - 1] * tMap[i + 1][col];
                            break;
                        case '^':
                            tMap[row][col] += tMap[row][i - 1] * fMap[i + 1][col];
                            tMap[row][col] += fMap[row][i - 1] * tMap[i + 1][col];
                            break;
                        default:
                    }
                    switch (exp[i]) {
                        case '&':
                            fMap[row][col] += fMap[row][i - 1] * fMap[i + 1][col];
                            fMap[row][col] += tMap[row][i - 1] * fMap[i + 1][col];
                            fMap[row][col] += fMap[row][i - 1] * tMap[i + 1][col];
                            break;
                        case '|':
                            fMap[row][col] += fMap[row][i - 1] * fMap[i + 1][col];
                            break;
                        case '^':
                            fMap[row][col] += tMap[row][i - 1] * tMap[i + 1][col];
                            fMap[row][col] += fMap[row][i - 1] * fMap[i + 1][col];
                            break;
                        default:
                    }
                }
            }
        }

        return desired ? tMap[0][N - 1] : fMap[0][N - 1];
    }

}
