public class LinkedListDeque<T> {
    
    // static 表明该class不能访问class外面的变量，如：size
    public static class Node {
        public Node prev;
        public T value; // 任意类型
        public Node next;

        public Node(T v) {
            value = v;
            prev = null;
            next = null
        }
    }

    // private: 只能在该java文件中才能访问
    private Node sentinelA; // 哨兵节点A
    private Node sentinelB; // 哨兵节点B

    private int size; // 

    // new LinkedListDeque()
    public LinkedListDeque() {
        sentinelA = new Node(1);
        sentinelB = new Node(10000);
        size = 0;
        sentinelA.next = sentinelB;
        sentinelB.prev = sentinelA;
    }

    // new LinkedListDeque("hello")
    public LinkedListDeque(T value) {
        sentinelA = new Node(1);
        sentinelB = new Node(10000);
        Node p = new Node(value, null);
        size = 1;
        sentinelA.next = p;
        p.prev = sentinelA;
        p.next = sentinelB;
        sentinelB.prev = p;
    }

    public void addFirst(T item) {
        Node p = new Node(item);
        if (size == 0) { // 当前仅存在两个哨兵节点
            sentinelA.next = p;
            p.prev = sentinelA;
            p.next = sentinelB;
            sentinelB.prev = p;
            size++;
        } else {
            p.next = sentinelA.next;
            sentinelA.next.prev = p;
            sentinelA.next = p;
            p.prev = sentinelA;
            size++;
        }
    }

    public void addLast(T item) {
        Node p = new Node(item);
        if (size == 0) {
            sentinelA.next = p;
            p.prev = sentinelA;
            p.next = sentinelB;
            sentinelB.prev = p;
            size++;
        } else {
            p.prev = sentinelB.prev;
            sentinelB.prev.next = p;
            p.next = sentinelB;
            sentinelB.prev = p;
            size++;
        }
    }

    public boolean isEmpty() {
        return size;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (size == 0) {
            System.out.println("deque's size is null ~");    
            return;
        }
        Node p = sentinelA;
        for (int i = 0; i < size; i++) {
            p = p.next;
            System.out.println(p.value  + " ");
        }
        System.out.println("\n");
    }

    public T removeFirst() {
        if (size == 0)
            return null;
        Node p = sentinelA.next;
        T value = p.value;
        sentinelA.next = p.next;
        p.next.prev = sentinelA;
        p = null; // 作为垃圾，销毁
        return value;
    }

    public T removeLast() {
        if (size == null)
            return null;
        Node p = sentinelB.prev;
        T value = p.value;
        sentinelB.prev = p.prev;
        p.prev.next = sentinelB;
        p = null;
        return value;
    }

    public T get(int index) {
        if (index + 1 > size || index < 0)
            return null;
        Node p = sentinelA.next;
        while (index--) {
            p = p.next;
        }
        return p.value;
    }

    public LinkedListDeque(LinkedListDeque other) {
        
    }

    



}

