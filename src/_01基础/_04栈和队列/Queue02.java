package _01基础._04栈和队列;

/**
 * 用栈实现队列
 */
public class Queue02 {

    private Stack01 stack1;

    private Stack01 stack2;

    private int size;

    private int limit;

    public Queue02(int capacity) {
        stack1 = new Stack01(capacity);
        stack2 = new Stack01(capacity);
        this.size = 0;
        this.limit = capacity;
    }

    public int pull() {
        if (size == 0) throw new RuntimeException("queue is empry");
        //从队列获取元素时，发现stack2为空，则把stack1中的所有元素倒入stack2中
        if (stack2.empty()) {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
        }
        size--;
        return stack2.pop();
    }

    public void push(int num) {
        if (size == limit) throw new RuntimeException("queue is full");
        stack1.push(num);
        size++;
    }

}
