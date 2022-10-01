package com.alexandrefranca.pingpong.solution2;

import java.util.Random;

public class Ping implements Runnable {

    private Ball ball;
    private String color;
    
    public Ping(Ball ball, String color) {
        this.ball = ball;
        this.color = color;
    }

    @Override
    public void run() {
        while(true){
            synchronized (ball){
                while(ball.getMsg().equals("Ping")){
                    try {
                        ball.wait(); // wait for Pong
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                // This block is just to cause a delay
                // between Ping and Pong
                try {
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                ball.setMsg("Ping");
                System.out.println(color+ball.getMsg());
                ball.notifyAll();
            }
        }

    }

}