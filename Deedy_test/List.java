public class SLList {


    // static, this claass can not access the method or variables of out SLList
    public static class IntNode {
         public int item;
         public IntNode next;
         public IntNode(int i, IntNode n) {
             item = i;
             next = n;
         }
    }

    // #7 需要实现双向链表
    


    private IntNode first; 

    private IntNode sentinel;
    private IntNode last; // speed up addLast
    private int size;

    // add
    public SLList() {
        first = null;
        size = 0;

        // here add sentinel ?

    }

    public SLList(int x) {
        first = new IntNode(x, null);
        size = 1;
    }

    /** Adds an item to the front of the list. */
    public void addFirst(int x) {
        first = new IntNode(x, first);
        size++;
    }    

    /** Retrieves the front item from the list. */
    public int getFirst() {
        return first.item;
    }

    /** Adds an item to the end of the list. */
    public void addLast(int x) {
        /* Your Code Here! */
        IntNode p = new IntNode(x, null);

        if(first == null)
            first = p;
        else {
            IntNode t = first;
            while(t.next != null) {
                t = t.next;
            }
            t.next = p;
        }

        size++;
    }

    public void addLast(int x) {
        last.next = new IntNode(x, null);
        last = last.next;
        size++;
    }
    // beloW two method, called overloaded
    // private static int size(IntNode p) {
    //     if (p.next == null)
    //         return 1;

    //     return 1 + size(p.next);
    // }
    /** Returns the number of items in the list using recursion. */
    public int size() {
        /* Your Code Here! */
        // return size(first);
        return size;
    }
}