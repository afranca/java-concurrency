package com.alexandrefranca.pingpong.solution1;

public class Ponger implements Runnable{

    Ball ball;
    String color;

    public Ponger(Ball ball, String color) {
        this.ball = ball;
        this.color = color;
    }

    @Override
    public void run() {

        while(true){
            synchronized (this) {
                while (ball.getPongMustWait()) {
                    try {
                        System.out.println(color+"PongMustWait:"+ball.getPongMustWait() + " | "+ball);
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(color + "Pong");
                ball.setPongMustWait(Boolean.FALSE);
            }
        }

    }
}
