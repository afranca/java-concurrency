package com.alexandrefranca.starvation;

import com.alexandrefranca.ThreadColor;

public class Starvation {

    public static Object lock = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Worker(ThreadColor.ANSI_RED), "P10");
        Thread t2 = new Thread(new Worker(ThreadColor.ANSI_BLUE), "P08");
        Thread t3 = new Thread(new Worker(ThreadColor.ANSI_GREEN), "P06");
        Thread t4 = new Thread(new Worker(ThreadColor.ANSI_CYAN), "P04");
        Thread t5 = new Thread(new Worker(ThreadColor.ANSI_PURPLE), "P02");

        t1.setPriority(10);
        t2.setPriority(8);
        t3.setPriority(6);
        t4.setPriority(4);
        t5.setPriority(2);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }

    private static class Worker implements Runnable{
        String colour;
        int count;
        public Worker(String colour) {
            this.colour = colour;
        }

        @Override
        public void run() {
            for(int i=0; i<100; i++){
                synchronized (lock){
                    System.out.format(colour+"%s: runCount = %d\n",
                            Thread.currentThread().getName(),
                            count++);
                }
            }
        }
    }
}
