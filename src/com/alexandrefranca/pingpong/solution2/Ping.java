package com.alexandrefranca.pingpong.solution2;

public class Ping implements Runnable {

    private Ball ball;
    private String color;
    
    public Ping(Ball ball, String color) {
        this.ball = ball;
        this.color = color;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(color+" Ping");
        try {
            //Thread.sleep(1000);
            synchronized (ball) {
                //ball.setMsg(color+" Notifying Pong");
                //msg.notify();
                ball.notifyAll();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}