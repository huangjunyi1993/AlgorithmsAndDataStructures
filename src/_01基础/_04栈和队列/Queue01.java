package _01基础._04栈和队列;

/**
 * 数组实现队列
 */
public class Queue01 {

    private int[] arr;

    private int size;

    private int limit;

    private int pullIndex;

    private int pushIndex;

    public Queue01(int limit) {
        this.limit = limit;
        this.size = 0;
        this.pullIndex = 0;
        this.pushIndex = 0;
        arr = new int[limit];
    }

    public int pull() {
        //size等于0，代表队列已空
        if (size == 0) throw new RuntimeException("queue is empry");
        int element = arr[pullIndex++];
        //如果pullIndex等于limit，从新返回到0
        if (pullIndex == limit) pullIndex = 0;
        return element;
    }

    public void push(int num) {
        //size等于limit，代表队列已满
        if (size == limit) throw new RuntimeException("queue is full");
        arr[pushIndex++] =  num;
        //如果pushIndex等于limit，从新返回到0
        if (pushIndex == limit) pushIndex = 0;
    }

    public int size() {
        return size;
    }

}
