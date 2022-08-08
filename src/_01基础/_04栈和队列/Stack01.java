package _01基础._04栈和队列;

/**
 * 数组实现栈
 */
public class Stack01 {

    private int[] arr;

    private int size;

    private int index = -1;

    public Stack01(int size) {
        this.size = size;
        arr = new int[size];
    }

    public int pop() {
        if (index == -1) throw new RuntimeException("stack is empty");
        return arr[index--];
    }

    public void push(int num) {
        if (index == size - 1) throw new RuntimeException("stack is full");
        arr[++index] = num;
    }

    public boolean empty() {
        return index == -1;
    }

    public int peek() {
        if (index == -1) throw new RuntimeException("stack is empty");
        return arr[index];
    }
}
