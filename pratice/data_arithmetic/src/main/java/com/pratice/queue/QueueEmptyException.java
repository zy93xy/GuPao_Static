package com.pratice.queue;

/**
 * @author zakyoung
 * @Title:
 * @Description: TODO QueueEmptyException 队列为空时出队或取队首元素抛出此异常
 * @date 2018-05-01
 */
public class QueueEmptyException extends RuntimeException{
    public QueueEmptyException(String err) {
        super(err);
    }
}
