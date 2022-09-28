package com.alexandrefranca.pingpong.solution1;

public class Pinger implements Runnable {

    Ball ball;
    String color;

    public Pinger(Ball ball, String color) {
        this.ball = ball;
        this.color = color;
    }

    @Override
    public void run() {

        while(true){
            synchronized (this) {
                while (!ball.getPongMustWait()) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(color + "Ping");
                ball.setPongMustWait(Boolean.FALSE);
                System.out.println(color+"PongMustWait:"+ball.getPongMustWait() + " | "+ball);
            }
        }
    }
}
