package com.alexandrefranca.pingpong.solution1;

import com.alexandrefranca.ThreadColor;

public class PingPong1 {

    public static  Ball ball = new Ball(Boolean.TRUE);

    public static void main(String[] args) {

        System.out.println("Starting Ping Pong");

        new Thread(new Pinger(ball, ThreadColor.ANSI_YELLOW)).start();

        new Thread(new Ponger(ball, ThreadColor.ANSI_PURPLE)).start();


    }

}
