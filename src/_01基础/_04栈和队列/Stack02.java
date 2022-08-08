package _01基础._04栈和队列;

/**
 * 用队列实现栈
 */
public class Stack02 {

    private Queue01 q1;

    private Queue01 q2;

    public Stack02(int size) {
        q1 = new Queue01(size);
        q2 = new Queue01(size);
    }

    public int pop() {
        //把q1中的元素放入q2，直到q1剩下一个元素
        while (q1.size() > 1) {
            q2.push(q1.pull());
        }
        int num = q1.pull();

        //q1和q2的引用互相交换
        Queue01 temp = q1;
        q1 = q2;
        q2 = temp;

        return num;
    }

    public void push(int num) {
        q1.push(num);
    }

}
