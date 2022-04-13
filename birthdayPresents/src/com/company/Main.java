package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
	// about 40 min
        long startTime = System.currentTimeMillis();

        int nThreads = 4;
        //500000
        int nPresents = 500000;
        int nCards= 0;
        Servants[] servants = new Servants[nThreads];
        LinkedList<Node> list = new LinkedList<Node>();

        for(int i = 0; i<nThreads; i++){
            servants[i] = new Servants();
        }

        for(int i = 0; i<nPresents; i++){
            list.add(new Node(i));
        }

        //System.out.println(Servants.listOfCards.size());

        //Servants.listOfCards.addAll(list);
        Collections.shuffle(list);
        Servants.bag.addAll(list);
        Collections.shuffle(list);
        Servants.listOfCards.addAll(list);

        //System.out.println(Servants.listOfCards.size());

        for (int i = 0; i < nThreads; i++) {
            servants[i].start();
        }

        try {

            //The serveants finishing up
            for (int i = 0; i < nThreads; i++) {
                servants[i].join();
            }



            //System.out.println(Presents.writtenCards.get());

            if(Presents.writtenCards.get() == nPresents){
                System.out.println("All thank you cards have been written");
            }
            else {
                System.out.println("Not all thank you cards have been written");
            }

            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;
            //elapsedTime = elapsedTime/1000;
            System.out.println("Execution time = : "+  elapsedTime+"ms, ");
        }
        catch (InterruptedException e) {

        }

    }
}
