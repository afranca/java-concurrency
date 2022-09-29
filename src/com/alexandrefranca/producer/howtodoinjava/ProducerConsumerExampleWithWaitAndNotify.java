package com.alexandrefranca.producer.howtodoinjava;

import com.alexandrefranca.ThreadColor;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerExampleWithWaitAndNotify {

   public static void main(String[] args) {
      List<Integer> taskQueue = new ArrayList<Integer>();
      int MAX_CAPACITY = 5;
      Thread tProducer = new Thread(new Producer(taskQueue, MAX_CAPACITY, ThreadColor.ANSI_BLUE), "Producer");
      Thread tConsumer = new Thread(new Consumer(taskQueue, ThreadColor.ANSI_YELLOW), "Consumer");
      tProducer.start();
      tConsumer.start();
   }
}