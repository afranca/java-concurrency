package com.alexandrefranca.producer.sync;

import com.alexandrefranca.ThreadColor;

import java.util.ArrayList;
import java.util.List;

import static com.alexandrefranca.write_read.Constants.EOF;


public class ProducerConsumerArray {

    public static void main(String[] args) {

        List<String> buffer = new ArrayList<String>();

        new Thread( new Producer(buffer, ThreadColor.ANSI_BLUE)).start();
        new Thread( new Consumer(buffer, ThreadColor.ANSI_RED)).start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(Thread.currentThread().getName() + " finished.");
    }
}

class Producer implements Runnable{

    List<String> buffer;
    String color;

    public Producer(List<String> buff, String color) {
        this.buffer = buff;
        this.color = color;
    }

    @Override
    public void run() {
        //String[] nums = { "1", "2", "3", "4", "5"};
        //for(String num: nums) {
        for (int num=0; num < 500 ; num++){
            synchronized (buffer) {
                //System.out.println("Producer: Buffer size=" + buffer.size() + " | START Producing");
                String product = "#" + num;
                System.out.println(color + "Producer: Storing -> " + product);
                buffer.add(product);
            }
        }
        synchronized (buffer){
            System.out.println(color + "Adding EOF and exiting...");
            buffer.add("EOF");
        }
    }
}

class Consumer implements Runnable{
    List<String> buffer;
    String color;

    public Consumer(List<String> buff, String color) {
        this.buffer = buff;
        this.color = color;
    }

    @Override
    public void run() {

        while(true){
            synchronized (buffer){
                if (buffer.isEmpty()){
                    System.out.println(color + "Consumer: Buffer is Empty");
                    continue;
                }
                if(buffer.get(0).equals(EOF)) {
                    System.out.println(color + "Consumer: Exiting");
                    break;
                } else {
                    System.out.println(color + "Consumer: Removed " + buffer.remove(0));
                }
            }
        }

    }
}

class MyTestThread implements Runnable{
    Integer index;
    String[] array;


    public MyTestThread(String[] arr, Integer ind) {
        this.index = ind;
        this.array = arr;
    }

    @Override
    public void run() {
        System.out.println("T: Starting New Thread ");
        //System.out.println(index.hashCode());
        System.out.println(array[0]);
        array[0]="Inner Thread";
        System.out.println(array[0]);

        //System.out.println("T: Index   ="+index);
        //index++;
        //System.out.println("t: Index++ ="+index);
        //System.out.println(index.hashCode());
        System.out.println("T: Exiting New Thread");
    }
}