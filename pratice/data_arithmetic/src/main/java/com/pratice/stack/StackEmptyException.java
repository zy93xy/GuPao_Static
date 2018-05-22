package com.pratice.stack;

/**
 * @author zakyoung
 * @Title:
 * @Description: TODO 堆栈为空时出栈或取栈顶元素抛出此异常
 * @date 2018-05-01
 */
public class StackEmptyException extends  RuntimeException {
    public  StackEmptyException(String err) { super(err);}
}
