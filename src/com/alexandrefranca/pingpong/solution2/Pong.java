package com.alexandrefranca.pingpong.solution2;

public class Pong implements Runnable{
    
    private Ball ball;
    private String color;
    
    public Pong(Ball m, String color){
        this.ball =m;
        this.color = color;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        synchronized (ball) {
            try{
                //System.out.println(color+" waiting for Ping at:"+System.currentTimeMillis());
                ball.wait();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            //System.out.println(color+" got notified at:"+System.currentTimeMillis());
            //process the message now
            System.out.println(color+" ball message: "+ ball.getMsg());
        }
    }

}