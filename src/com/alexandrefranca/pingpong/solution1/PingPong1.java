package com.alexandrefranca.pingpong.solution1;

import com.alexandrefranca.ThreadColor;

public class PingPong1 {

    public Boolean pongWait = true;

    Runnable pinger = ()->{
        while(!Thread.currentThread().isInterrupted()) {
            synchronized (this) {
                while (!pongWait) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println(ThreadColor.ANSI_YELLOW+"Ping");
                pongWait = false;
                notifyAll();
            }
        }
    };

    Runnable ponger = ()->{
        while(!Thread.currentThread().isInterrupted()) {
            synchronized (this) {
                while (pongWait) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println(ThreadColor.ANSI_PURPLE+"Pong");
                pongWait = true;
                notifyAll();
            }
        }
    };

    public static void main(String[] args) throws InterruptedException{

        PingPong1 main = new PingPong1();
        System.out.println("Starting Ping Pong");

        Thread pingThread = new Thread(main.pinger);
        Thread pongThread = new Thread(main.ponger);

        pingThread.start();
        pongThread.start();

        //stop threads after 1 seconds
        Thread.sleep(1*1000);
        pingThread.interrupt();
        pongThread.interrupt();
    }



}
