import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Temp {

    static AtomicInteger giftsInBag = new AtomicInteger(500000);
    static AtomicInteger tempStored = new AtomicInteger(0);
    static AtomicInteger writtenCards = new AtomicInteger(0);
    private Node head;

    public Temp() {
        head = new Node(Integer.MIN_VALUE);
        head.next = new Node(Integer.MAX_VALUE);
    }

    private boolean validate(Node pred, Node curr) {
        return !pred.marked && !curr.marked && pred.next == curr;
    }

    public boolean contains(Node item) {
        double key = item.key;
        Node curr = head;
        while (curr.key < key)
            curr = curr.next;

        return curr.key == key && !curr.marked;
    }

    public boolean add(Node item) {
        double key = item.key;
        while (true) {
            Node pred = head;
            Node curr = pred.next;
            while (curr.key < key) {
                pred = curr; curr = curr.next;
            }
            pred.lock();
            try {
                curr.lock();
                try {
                    if (validate(pred, curr)) {
                        Node node = new Node(item.key);
                        node.next = curr;
                        pred.next = node;
                        tempStored.incrementAndGet();
                        //giftsInBag.decrementAndGet();
                        return true;
                    }
                } finally {
                    curr.unlock();
                }
            } finally {
                pred.unlock();
            }
        }
    }

    public boolean remove(Node item) {
        double key = item.key;
        while (true) {
            Node pred = head;
            Node curr = head.next;
            while (curr.key < key) {
                pred = curr; curr = curr.next;
            }
            pred.lock();
            try {
                curr.lock();
                try {
                    if (validate(pred, curr)) {
                        if (curr.key == key) {
                            curr.marked = true;
                            pred.next = curr.next;
                            tempStored.decrementAndGet();
                            //writtenCards.incrementAndGet();
                            return true;
                        } else {
                            return false;
                        }
                    }
                } finally {
                    curr.unlock();
                }
            } finally {
                pred.unlock();
            }
        }
    }

    public Node highest() {
        //int key = item.key;
        System.out.println("The 5 highest:");
        while (true) {
            Node pred = head;
            Node curr = head.next;
            for(int i=0; i<480-5;i++){
                pred = curr; curr = curr.next;
            }
            Node temp = curr;
            for(int i=0; i<5;i++){
                System.out.print(curr.key+", ");
                pred = curr; curr = curr.next;
            }
            return temp;
        }
    }

    public Node lowest() {
        //int key = item.key;
        System.out.println("The 5 lowest:");
        while (true) {
            Node pred = head;
            Node curr = head.next;
            for(int i=0; i<5;i++){
                pred = curr; curr = curr.next;
                System.out.print(curr.key+", ");
            }
            Node temp = curr;
            return temp;
        }
    }
}

class Node {
    //T item;
    double key;
    Node next;
    boolean marked;
    ReentrantLock lock = new ReentrantLock();
    public Node(double key){
        this.key=key;
    }

    public void lock(){
        lock.lock();
    }

    public void unlock(){
        lock.unlock();
    }

}
