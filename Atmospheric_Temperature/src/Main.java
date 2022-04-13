import java.util.Collection;
import java.util.Random;

public class Main {

    public static void main(String[] args){
        int nThreads = 8;
        Random rand = new Random();
        Sensor[] sensors = new Sensor[nThreads];
        int hours = 3;


        for(int h=0; h<hours; h++){
            for(int i = 0; i<nThreads; i++){
                sensors[i] = new Sensor(i);
            }
            for (int i = 0; i < nThreads; i++) {
                sensors[i].start();
            }

            try {

                //The serveants finishing up
                for (int i = 0; i < nThreads; i++) {
                    sensors[i].join();
                }
                System.out.println("Hour " +(h+1));
                System.out.println("Ten minute differences: ");
                System.out.println(Sensor.tenMin);

                Sensor.totalTemp.highest();
                System.out.println();
                Sensor.totalTemp.lowest();
                System.out.println();
                System.out.println();

                //System.out.println(Sensor.totalTemp.tempStored.get());
                sensors[0].reset();

            }
            catch (InterruptedException e) {

            }

        }
    }
}
