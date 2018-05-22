package com.pratice.list;

/**
 * @author zakyoung
 * @Title:
 * @Description: TODO 单链表结点定义
 * @date 2018-05-01
 */
public class SLNode implements Node {

    private Object element;
    private SLNode next;

    public SLNode() {
        this(null,null);
    }

    public SLNode(Object ele, SLNode next){
        this.element = ele;
        this.next = next;
    }

    public SLNode getNext(){
        return next;
    }
    public void setNext(SLNode next){
        this.next = next;
    }
    // ======重写接口的方法======
    public Object getData() {
        return element;
    }

    public void setData(Object obj) {
        this.element = obj;
    }
}
