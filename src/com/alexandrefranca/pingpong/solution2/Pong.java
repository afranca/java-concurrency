package com.alexandrefranca.pingpong.solution2;

import java.util.Random;

public class Pong implements Runnable{
    
    private Ball ball;
    private String color;
    
    public Pong(Ball m, String color){
        this.ball =m;
        this.color = color;
    }

    @Override
    public void run() {

        while(true){
            synchronized(ball){
                while(ball.getMsg().equals("Pong")){
                    try {
                        ball.wait(); //wait for Ping
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                // This block is just to cause a delay
                // between Ping and Pong
                try {
                    Thread.sleep(new Random().nextInt(600));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                ball.setMsg("Pong");
                System.out.println(color+ball.getMsg());
                ball.notifyAll();
            }
        }
    }
}