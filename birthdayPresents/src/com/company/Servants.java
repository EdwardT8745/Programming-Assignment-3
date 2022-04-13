package com.company;

import  java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.Random;

public class Servants extends Thread{
    static int numCards = 500000;
    static Presents chain = new Presents();
    static boolean thankYouCards[] = new boolean[numCards];
    static ConcurrentLinkedQueue<Node> bag = new ConcurrentLinkedQueue<Node>();
    static LinkedBlockingQueue<Node> listOfCards = new LinkedBlockingQueue<Node>();
    Random rand = new Random();


    //static Node head;
    //static Node tail;

    ///static private Lock lock = new ReentrantLock();


    public void run() {
        while(Presents.writtenCards.get() < numCards){

            //add present to chain
            if(bag.size() > 0){
                Node temp = bag.poll();
                //System.out.println(temp.key);
                //gift in bag
                chain.add(temp);
//                if(!chain.contains(temp)){
//                    //bag[num] = true;
//                    chain.add(temp);
//                }
            }

            //remove present from chain
            if(Presents.giftsInChain.get() > 0){
                //int num = rand.nextInt(listOfCards.size());
                //int num = rand.nextInt(listOfCards.size());
                //System.out.println("remove "+ num+ " gift");
                //Node temp = new Node(num);
                Node temp = listOfCards.poll();
                //
                if(temp !=null ){
                    if(chain.remove(temp)){
                        //thankYouCards[num] = true;
                        //chain.remove(temp);
                        //listOfCards.remove(temp);
                    }
                    else{
                        listOfCards.add(temp);
                    }
                }

            }



        }

    }
}
