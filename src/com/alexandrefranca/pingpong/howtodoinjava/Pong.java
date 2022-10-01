package com.alexandrefranca.pingpong.howtodoinjava;

import java.util.List;
/*
 * This example was changed from the original: https://howtodoinjava.com/java/multi-threading/wait-notify-and-notifyall-methods/
 *
 * */
class Pong implements Runnable {
   private final List<String> taskQueue;
   private final String color;
 
   public Pong(List<String> sharedQueue, String color){
      this.taskQueue = sharedQueue;
      this.color = color;
   }
 
   @Override
   public void run() {
      while (true) {
         try {
            consume();
         } catch (InterruptedException ex) {
            ex.printStackTrace();
         }
      }
   }
 
   private void consume() throws InterruptedException {
      synchronized (taskQueue) {
         while (taskQueue.isEmpty()) {
            //System.out.println(color+"Queue is empty " + Thread.currentThread().getName() + " is waiting , size: " + taskQueue.size());
            taskQueue.wait();
         }
         Thread.sleep(1000);
         taskQueue.remove(0); // Removes Ping and Prints out Pong
         System.out.println(color+": Pong");
         taskQueue.notifyAll();
      }
   }
}