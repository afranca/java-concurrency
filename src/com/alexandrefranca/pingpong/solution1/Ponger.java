package com.alexandrefranca.pingpong.solution1;

public class Ponger implements Runnable{

    //Ball ball;
    String color;
    boolean pongWait;

    public Ponger(boolean pongWait, String color) {
        this.pongWait = pongWait;
        this.color = color;
    }

    @Override
    public void run() {

        while(!Thread.currentThread().isInterrupted()){
            synchronized(this) {
                while (pongWait) {
                    try {
                        System.out.println(color+"PongMustWait:"+pongWait);
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(color + "Pong");
                pongWait = false;
            }
        }

    }
}
