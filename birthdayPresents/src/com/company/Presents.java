package com.company;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Presents {

    static AtomicInteger giftsInBag = new AtomicInteger(500000);
    static AtomicInteger giftsInChain = new AtomicInteger(0);
    static AtomicInteger writtenCards = new AtomicInteger(0);
    private Node head;
    //private Lock lock = new ReentrantLock();

    public Presents() {
        head = new Node(Integer.MIN_VALUE);
        head.next = new Node(Integer.MAX_VALUE);
    }

    private boolean validate(Node pred, Node curr) {
        return !pred.marked && !curr.marked && pred.next == curr;
    }

    public boolean contains(Node item) {
        int key = item.key;
        Node curr = head;
        while (curr.key < key)
            curr = curr.next;

        return curr.key == key && !curr.marked;
        }

    public boolean add(Node item) {
         int key = item.key;
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
                         if (curr.key == key) {
                             return false;
                         } else {
                             Node node = new Node(item.key);
                             node.next = curr;
                             pred.next = node;
                             giftsInChain.incrementAndGet();
                             giftsInBag.decrementAndGet();
                             return true;
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

    public boolean remove(Node item) {
         int key = item.key;
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
                             giftsInChain.decrementAndGet();
                             writtenCards.incrementAndGet();
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
}

class Node {
    //T item;
    int key;
    Node next;
    boolean marked;
    ReentrantLock lock = new ReentrantLock();
    public Node(int key){
        this.key=key;
    }

    public void lock(){
        lock.lock();
    }

    public void unlock(){
        lock.unlock();
    }

}
