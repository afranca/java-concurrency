package com.alexandrefranca.pingpong.howtodoinjava;

import com.alexandrefranca.ThreadColor;

import java.util.ArrayList;
import java.util.List;
/*
 * This example was changed from the original: https://howtodoinjava.com/java/multi-threading/wait-notify-and-notifyall-methods/
 *
 * */
public class PingPongWithWaitAndNotify {

   public static void main(String[] args) {
      List<String> taskQueue = new ArrayList<>();
      int MAX_CAPACITY = 1;
      Thread tProducer = new Thread(new Ping(taskQueue, MAX_CAPACITY, ThreadColor.ANSI_BLUE), "Pinger");
      Thread tConsumer = new Thread(new Pong(taskQueue, ThreadColor.ANSI_YELLOW), "Ponger");
      tProducer.start();
      tConsumer.start();
   }
}