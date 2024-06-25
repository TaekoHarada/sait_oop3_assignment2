package utilities;

public class MyDLLNode<E> {
    private E data;
    private MyDLLNode<E> prev;
    private MyDLLNode<E> next;

    public MyDLLNode(E data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public MyDLLNode<E> getPrev() {
        return prev;
    }

    public void setPrev(MyDLLNode<E> prev) {
        this.prev = prev;
    }

    public MyDLLNode<E> getNext() {
        return next;
    }

    public void setNext(MyDLLNode<E> next) {
        this.next = next;
    }
}
