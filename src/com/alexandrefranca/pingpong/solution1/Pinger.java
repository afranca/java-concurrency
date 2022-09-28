package com.alexandrefranca.pingpong.solution1;

public class Pinger implements Runnable {

    //Ball ball;
    String color;
    boolean pongWait;

    public Pinger(boolean pongWait, String color) {
        //this.ball = ball;
        this.color = color;
        this.pongWait = pongWait;
    }

    @Override
    public void run() {

        while(!Thread.currentThread().isInterrupted()){
            synchronized (this) {
                while (!pongWait) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(color + "Ping");
                pongWait = false;
                System.out.println(color+"PongMustWait:"+pongWait);
            }
        }
    }
}
