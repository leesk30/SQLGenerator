package org.lee;

import org.testng.annotations.Test;

public class TempTest {

    private static class testThread extends Thread{

        private boolean signal = true;

        public void stopIt(){
            signal = false;
        }

        @Override
        public void run() {
            int i =0;
            while (signal){
                i++;
                System.out.println(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Test
    public void testForThreadSignal() throws InterruptedException {
        testThread t = new testThread();
        t.start();
        Thread.sleep(1000);
        t.stopIt();
        t.join();
    }
}
