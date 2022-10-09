package _03经典面试题目.堆;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 题目见图：
 * _03经典面试题目/堆/PMSED.png
 *
 * Created by huangjunyi on 2022/10/9.
 */
public class _06SDEAndPM {

    private static class Program {
        int index;
        int pm;
        int start;
        int rank;
        int cost;

        public Program(int index, int pm, int start, int rank, int cost) {
            this.index = index;
            this.pm = pm;
            this.start = start;
            this.rank = rank;
            this.cost = cost;
        }
    }

    private static class PMComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            if (o1.rank != o2.rank) return o1.rank - o2.rank;
            if (o1.cost != o2.cost) return o1.cost - o2.cost;
            return o1.start - o2.start;
        }
    }

    private static class SDEComparator implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            if (o1.cost != o2.cost) return o1.cost - o2.cost;
            return o1.pm - o2.pm;
        }
    }

    private static class StartComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.start - o2.start;
        }
    }

    private static class BigHeap {
        List<PriorityQueue<Program>> pmHeaps;
        Program[] sdeHeap;
        int[] pmToSdeIndexs;
        int heapSize;
        SDEComparator sdeComparator;

        public BigHeap(int pms) {
            heapSize = 0;
            sdeHeap = new Program[pms];
            pmToSdeIndexs = new int[pms + 1];
            for (int i = 0; i < pmToSdeIndexs.length; i++) {
                pmToSdeIndexs[i] = -1;
            }
            pmHeaps = new ArrayList<>();
            for (int i = 0; i <= pms; i++) {
                pmHeaps.add(new PriorityQueue<>(new PMComparator()));
            }
            sdeComparator = new SDEComparator();
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        /**
         * 往大堆中加入一个项目
         * 加入到对应的项目经理的堆中
         * 项目经理堆有可能换头
         * 因此要相应更换程序员堆中的项目
         * 因为更换的项目有可能在程序员堆中发生变化，所以程序员堆要左堆调整
         * @param program
         */
        public void add(Program program) {
            pmHeaps.get(program.pm).add(program);
            Program head = pmHeaps.get(program.pm).peek();
            if (pmToSdeIndexs[program.pm] == -1) {
                sdeHeap[heapSize] = program;
                pmToSdeIndexs[program.pm] = heapSize;
                heapFloorUp(heapSize++);
            } else {
                sdeHeap[pmToSdeIndexs[program.pm]] = head;
                heapFloorUp(pmToSdeIndexs[program.pm]);
                heapFloorSink(pmToSdeIndexs[program.pm]);
            }
        }

        private void heapFloorSink(int curIndex) {
            int left = curIndex * 2 + 1;
            int right = curIndex * 2 + 2;
            while (left < heapSize) {
                int best = curIndex;
                if (sdeComparator.compare(sdeHeap[curIndex], sdeHeap[left]) > 0) best = left;
                if (right < heapSize && sdeComparator.compare(sdeHeap[best], sdeHeap[right]) > 0) best = right;
                if (best == curIndex) break;
                swap(curIndex, best);
                curIndex = best;
                left = curIndex * 2 + 1;
                right = curIndex * 2 + 2;
            }
        }

        private void swap(int a, int b) {
            int aIndex = pmToSdeIndexs[sdeHeap[a].pm];
            int bIndex = pmToSdeIndexs[sdeHeap[b].pm];
            pmToSdeIndexs[sdeHeap[a].pm] = bIndex;
            pmToSdeIndexs[sdeHeap[b].pm] = aIndex;
            Program tmp = sdeHeap[a];
            sdeHeap[a] = sdeHeap[b];
            sdeHeap[b] = tmp;
        }

        private void heapFloorUp(int curIndex) {
            while (curIndex != 0) {
                int parent = (curIndex - 1) / 2;
                if (sdeComparator.compare(sdeHeap[curIndex], sdeHeap[parent]) < 0) {
                    swap(curIndex, parent);
                    curIndex = parent;
                } else {
                    break;
                }
            }
        }

        /**
         * 从程序员堆弹出一个项目
         * 项目经理堆中对应的项目也有删除
         * 程序员堆做堆调整
         * @return
         */
        public Program poll() {
            Program program = sdeHeap[0];
            pmHeaps.get(program.pm).poll();
            sdeHeap[0] = sdeHeap[heapSize - 1];
            heapFloorSink(0);
            heapSize--;
            return program;
        }
    }

    public static int[] workFinish(int pms, int sdes, int[][] programs) {
        PriorityQueue<Program> startHeap = new PriorityQueue<>(new StartComparator());
        for (int i = 0; i < programs.length; i++) {
            int[] program = programs[i];
            startHeap.add(new Program(i, program[0], program[1], program[2], program[3]));
        }
        PriorityQueue<Integer> wakeHeap = new PriorityQueue<>();
        for (int i = 0; i < sdes; i++) {
            wakeHeap.add(1);
        }
        int[] res = new int[programs.length];
        BigHeap bigHeap = new BigHeap(pms);
        int finish = 0;
        while (finish != res.length) {
            int wakeTime = wakeHeap.poll();
            while (!startHeap.isEmpty() && startHeap.peek().start <= wakeTime) {
                bigHeap.add(startHeap.poll());
            }
            if (bigHeap.isEmpty()) {
                wakeHeap.add(startHeap.peek().start);
            } else {
                Program program = bigHeap.poll();
                res[program.index] = wakeTime + program.cost;
                wakeHeap.add(res[program.index]);
                finish++;
            }
        }
        return res;
    }

}



/***************************************
 *
 *
 * 左神代码 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
 *
 *
 ***************************************/
class Code01_SDEandPM {

    public static class Program {
        public int index;
        public int pm;
        public int start;
        public int rank;
        public int cost;

        public Program(int index, int pmNum, int begin, int rank, int cost) {
            this.index = index;
            this.pm = pmNum;
            this.start = begin;
            this.rank = rank;
            this.cost = cost;
        }
    }

    public static class PmLoveRule implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            if (o1.rank != o2.rank) {
                return o1.rank - o2.rank;
            } else if (o1.cost != o2.cost) {
                return o1.cost - o2.cost;
            } else {
                return o1.start - o2.start;
            }
        }

    }

    // 大黑盒
    // 每一个pm，有自己的堆(PmLoveRule)
    // 每一个pm的堆里有堆顶，所有的堆顶会再组成一个，程序员堆(程序员喜好)
    // void add(...)  项目  pop()
    public static class BigQueues {
        //  PriorityQueue<Program> pmQ = pmQueues.get(i);
        private List<PriorityQueue<Program>> pmQueues;
        // 程序员堆（一个，程序员共享池）
        private Program[] sdeHeap;
        // indexes[i] -> i号pm的堆顶项目，在sde堆中处在啥位置
        private int[] indexes;
        private int heapsize; // 程序员堆的大小

        public BigQueues(int pmNum) {
            heapsize = 0;
            sdeHeap = new Program[pmNum];
            indexes = new int[pmNum + 1];
            for (int i = 0; i <= pmNum; i++) {
                indexes[i] = -1;
            }
            pmQueues = new ArrayList<>();
            // i  pmQueues.get(i)
            for (int i = 0; i <= pmNum; i++) {
                pmQueues.add(new PriorityQueue<Program>(new PmLoveRule()));
            }
        }

        // 当前是否有项目可以弹出被程序员做
        public boolean isEmpty() {
            return heapsize == 0;
        }

        // 某一个项目加入了，黑盒里
        public void add(Program program) {
            PriorityQueue<Program> pmHeap = pmQueues.get(program.pm);
            pmHeap.add(program);
            // 有可能当前的项目，成了此时pm最喜欢的项目，换堆顶，调整sde堆中的项目
            Program head = pmHeap.peek(); // 现在的堆顶
            // 之前pm在sde堆中的自己的堆顶，sde？
            int heapindex = indexes[head.pm];
            if (heapindex == -1) { // 之前没堆顶,
                sdeHeap[heapsize] = head;
                indexes[head.pm] = heapsize;
                heapInsert(heapsize++);
            } else { // 加此时的program之前，我有老堆顶
                sdeHeap[heapindex] = head;
                heapInsert(heapindex);
                heapify(heapindex);
            }
        }

        // 程序员挑项目，返回挑选的项目
        public Program pop() {
            Program head = sdeHeap[0];
            PriorityQueue<Program> queue = pmQueues.get(head.pm);
            queue.poll();
            if (queue.isEmpty()) { // 此时的pm手上没有项目了
                swap(0, heapsize - 1);
                sdeHeap[--heapsize] = null;
                indexes[head.pm] = -1;
            } else {
                sdeHeap[0] = queue.peek();
            }
            heapify(0);
            return head;
        }

        private void heapInsert(int index) {
            while (index != 0) {
                int parent = (index - 1) / 2;
                if (sdeLoveRule(sdeHeap[parent], sdeHeap[index]) > 0) {
                    swap(parent, index);
                    index = parent;
                } else {
                    break;
                }
            }
        }

        private void heapify(int index) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            int best = index;
            while (left < heapsize) {
                if (sdeLoveRule(sdeHeap[left], sdeHeap[index]) < 0) {
                    best = left;
                }
                if (right < heapsize && sdeLoveRule(sdeHeap[right], sdeHeap[best]) < 0) {
                    best = right;
                }
                if (best == index) {
                    break;
                }
                swap(best, index);
                index = best;
                left = index * 2 + 1;
                right = index * 2 + 2;
            }
        }

        private void swap(int index1, int index2) {
            Program p1 = sdeHeap[index1];
            Program p2 = sdeHeap[index2];
            sdeHeap[index1] = p2;
            sdeHeap[index2] = p1;
            indexes[p1.pm] = index2;
            indexes[p2.pm] = index1;
        }

        private int sdeLoveRule(Program p1, Program p2) {
            if (p1.cost != p2.cost) {
                return p1.cost - p2.cost;
            } else {
                return p1.pm - p2.pm;
            }
        }

    }

    public static class StartRule implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.start - o2.start;
        }

    }

    public static int[] workFinish(int pms, int sdes, int[][] programs) {
        // 所有被锁住的项目（3，6，9）   time=0
        PriorityQueue<Program> startQueue  = new PriorityQueue<Program>(new StartRule());
        for (int i = 0; i < programs.length; i++) {
            Program program = new Program(
                    i, programs[i][0], programs[i][1], programs[i][2], programs[i][3]);
            startQueue.add(program);
        }
        // 所有的项目，在最开始的时候，都在start堆中，被锁住
        //
        //
        PriorityQueue<Integer> wakeQueue = new PriorityQueue<Integer>();
        for (int i = 0; i < sdes; i++) {
            wakeQueue.add(1);
        }
        // add   pop   isEmpty
        BigQueues bigQueues = new BigQueues(pms);
        int finish = 0; // 目前完成项目的数量
        int[] ans = new int[programs.length];
        while (finish != ans.length) { // 没有得到所有的答案就继续
            // 最早醒来的程序员的时间, 也是总的推进时间点
            int sdeWakeTime = wakeQueue.poll();
            while (!startQueue.isEmpty()) {
                if (startQueue.peek().start > sdeWakeTime) {
                    break;
                }
                bigQueues.add(startQueue.poll());
            }
            //
            if (bigQueues.isEmpty()) { // 当前时间点并无项目可做
                wakeQueue.add(startQueue.peek().start);
            } else { // 当前时间点有项目可做
                Program program = bigQueues.pop();
                ans[program.index] = sdeWakeTime + program.cost;
                wakeQueue.add(ans[program.index]);
                finish++;
            }
        }
        return ans;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void main(String[] args) {
        int pms = 2;
        int sde = 2;
        int[][] programs = { { 1, 1, 1, 2 }, { 1, 2, 1, 1 }, { 1, 3, 2, 2 }, { 2, 1, 1, 2 }, { 2, 3, 5, 5 } };
        int[] ans = workFinish(pms, sde, programs);
        printArray(ans);
    }

}