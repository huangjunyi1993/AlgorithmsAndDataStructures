package _01基础._04栈和队列;

/**
 * 最小栈，min方法返回栈中的最小值
 */
public class Stack03 {

    private Stack01 dataStack;

    private Stack01 minStack;

    public Stack03(int size) {
        dataStack = new Stack01(size);
        minStack = new Stack01(size);
    }

    public void push(int num) {
        dataStack.push(num);
        //每次数据栈压入一个数，最小栈也压入一个数，该数为栈顶与当前压入数中较小的一个，保证栈顶总是最小值
        if (!minStack.empty() && minStack.peek() > num) minStack.push(num);
        else minStack.push(minStack.peek());
    }

    public int pop() {
        //同步把最小栈的栈顶数弹出
        minStack.pop();
        return dataStack.pop();
    }

    public int min() {
        //最小栈的栈顶就是栈中的最小值
        return minStack.peek();
    }

}
