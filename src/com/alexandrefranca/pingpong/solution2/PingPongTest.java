package com.alexandrefranca.pingpong.solution2;

/*
* This example was taken from:
* https://www.digitalocean.com/community/tutorials/java-thread-wait-notify-and-notifyall-example
* */

import com.alexandrefranca.ThreadColor;

public class PingPongTest {

    public static void main(String[] args) {
        Ball msg = new Ball("Game Starts");

        new Thread(new Pong(msg, ThreadColor.ANSI_PURPLE),"pong-thread").start();

        new Thread(new Ping(msg, ThreadColor.ANSI_YELLOW), "ping-thread").start();

        System.out.println("All the threads are started");
    }

}