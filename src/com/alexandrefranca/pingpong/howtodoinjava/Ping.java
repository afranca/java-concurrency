package com.alexandrefranca.pingpong.howtodoinjava;

import java.util.List;

/*
* This example was changed from the original: https://howtodoinjava.com/java/multi-threading/wait-notify-and-notifyall-methods/
*
* */

class Ping implements Runnable {
   private final List<String> taskQueue;
   private final int MAX_CAPACITY;
   private final String color;
 
   public Ping(List<String> sharedQueue, int size, String color) {
      this.taskQueue = sharedQueue;
      this.MAX_CAPACITY = size;
      this.color = color;
   }
 
   @Override
   public void run(){
      int counter = 0;
      while (true) {
         try{
            ping();
         } catch (InterruptedException ex) {
            ex.printStackTrace();
         }
      }
   }
 
   private void ping() throws InterruptedException {
      synchronized (taskQueue) {
         while (taskQueue.size() == MAX_CAPACITY) {
            //System.out.println(color+"Queue is full " + Thread.currentThread().getName()
            //        + " is waiting , size: " + taskQueue.size());
            taskQueue.wait();
         }
       
         Thread.sleep(1000);
         taskQueue.add("Ping");
         System.out.println(color+": Ping");
         taskQueue.notifyAll();
      }
   }
}