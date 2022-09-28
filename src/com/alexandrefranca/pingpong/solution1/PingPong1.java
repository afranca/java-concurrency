package com.alexandrefranca.pingpong.solution1;

import com.alexandrefranca.ThreadColor;

public class PingPong1 {

    public static void main(String[] args) {
        System.out.println("Starting Ping Pong");

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    System.out.println(ThreadColor.ANSI_YELLOW+"Ping");
                    try {
                        wait();
                        notifyAll();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    System.out.println(ThreadColor.ANSI_PURPLE+"Pong");
                    try {
                        wait();
                        notifyAll();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();


        //System.out.println("Finished Ping Pong");

    }

}
