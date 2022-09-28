package com.alexandrefranca.pingpong.solution1;

import com.alexandrefranca.ThreadColor;

public class PingPong1 {

    //public Ball ball = new Ball(Boolean.TRUE);
    public boolean pongWait=true;

    public static void main(String[] args) throws InterruptedException{
        PingPong1 main = new PingPong1();

        System.out.println("Starting Ping Pong");

        Thread pingThread = new Thread(new Pinger(main.pongWait, ThreadColor.ANSI_YELLOW));
        Thread pongThread = new Thread(new Ponger(main.pongWait, ThreadColor.ANSI_PURPLE));

        pingThread.start();
        pongThread.start();

        //stop threads after 5 seconds
        Thread.sleep(5*1000);
        pingThread.interrupt();
        pongThread.interrupt();
    }



}
