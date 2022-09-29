package com.alexandrefranca.producer.howtodoinjava;

import java.util.List;
/*
 * This example was changed from the original: https://howtodoinjava.com/java/multi-threading/wait-notify-and-notifyall-methods/
 *
 * */
class Consumer implements Runnable {
   private final List<Integer> taskQueue;
   private final String color;
 
   public Consumer(List<Integer> sharedQueue, String color){
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
            System.out.println(color+"Queue is empty " + Thread.currentThread().getName() + " is waiting , size: " + taskQueue.size());
            taskQueue.wait();
         }
         Thread.sleep(1000);
         int i = (Integer) taskQueue.remove(0);
         System.out.println(color+"Consumed: " + i);
         taskQueue.notifyAll();
      }
   }
}