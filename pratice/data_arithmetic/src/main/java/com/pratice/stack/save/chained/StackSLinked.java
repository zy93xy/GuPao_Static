package com.pratice.stack.save.chained;

import com.pratice.list.SLNode;
import com.pratice.stack.Stack;
import com.pratice.stack.StackEmptyException;

/**
 * @author zakyoung
 * @Title:
 * @Description: TODO Stack 的链式存储实现
 * @date 2018-05-01
 */
public class StackSLinked implements Stack {

    private SLNode top; //链表首结点引用
    private int size; //栈的大小
    public StackSLinked() {
        top = null; size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size==0;
    }

    public void push(Object e) {
        SLNode q = new SLNode(e,top);
        top = q;//顶部赋值
        size++;
    }

    //栈顶元素出栈
    public Object pop() throws StackEmptyException {
        if (size<1)
            throw new StackEmptyException("错误，堆栈为空。");
        Object obj = top.getData();
        top = top.getNext();//顶部的下面一个成为top
        size--;
        return obj;
    }

    //取栈顶元素
    public Object peek() throws StackEmptyException {
        if (size<1)
            throw new StackEmptyException("错误，堆栈为空。");
        return top.getData();
    }
}
