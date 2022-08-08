package _01基础._11贪心算法;

import java.util.Arrays;

/**
 * 会议安排问题
 * 一个会议室，若干场会议，会议室同一时间只能安排一场会议，
 * 每场会议都有开始时间和结束时间，安排会议日程，要求安排的场次最多
 */
public class Greed02 {

    public static int process(Meeting[] meetings) {
        if (meetings == null || meetings.length == 0) return 0;
        int result = 0;

        //根据结束时间早进行排序
        Arrays.sort(meetings, (o1, o2) -> o1.endTime - o2.endTime);
        int currentTime = 0;

        for (int i = 0; i < meetings.length; i++) {
            Meeting meeting = meetings[i];
            if (meeting.startTime <= currentTime) {
                result++;
                currentTime = meeting.endTime;
            }
        }

        return result;
    }

    public static class Meeting {
        private int startTime;
        private int endTime;
    }

}
