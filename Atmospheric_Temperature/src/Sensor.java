import  java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.Random;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Sensor extends Thread{
    //ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    //executor.scheduleAtFixedRate(helloRunnable, 0, 3, SECONDS);
    static ArrayList<Double> tenMin = new ArrayList<Double>();
    static Temp totalTemp = new Temp();
    //static AtomicInteger high = new AtomicInteger(Integer.MIN_VALUE);
    //static AtomicInteger low = new AtomicInteger(Integer.MAX_VALUE);
    static AtomicReference<Double> high = new AtomicReference<Double>((double) Integer.MIN_VALUE);
    static AtomicReference<Double>  low = new AtomicReference<Double>((double) Integer.MAX_VALUE);
    Random rand = new Random();
    static PriorityBlockingQueue<Double> temperature = new PriorityBlockingQueue<Double>();
    private int id;
    public Sensor(int id){
        this.id =id;
    }

    public void run() {
//        final ScheduledFuture<?> tempHandle =
//                scheduler.scheduleAtFixedRate(takeTemp, 1, 1, SECONDS);
//        scheduler.schedule(new Runnable() {
//            public void run() { tempHandle.cancel(true); }
//        }, 60, SECONDS);
        for(int i=0; i<60; i++){
            //int num = rand.nextDouble(-100, 70+1);
            double num = rand.nextDouble(-100, 70);

            if(high.get()<num){
                high.set(num);
            }
            else if(low.get()>num) {
                low.set(num);
            }

            if(id==7 && ((i+1)%10) == 0){
                tenMin.add(high.get()-low.get());
                high.set((double) 0);
                low.set((double) 0);
            }

            Node temp = new Node(num);
            totalTemp.add(temp);
            try {
                currentThread().sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void reset() {
        tenMin = new ArrayList<Double>();
        totalTemp = new Temp();
        high = new AtomicReference<Double>((double) Integer.MIN_VALUE);
        low = new AtomicReference<Double>((double) Integer.MAX_VALUE);
        totalTemp.tempStored.set(0);
    }



//    final Runnable takeTemp = new Runnable() {
//        public void run() {
//            int num = rand.nextInt(-100, 70+1);
//            temperature.add(num);
//            System.out.println("taking temp");
//        }
//    };

}
