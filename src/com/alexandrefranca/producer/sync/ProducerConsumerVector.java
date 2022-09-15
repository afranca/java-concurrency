package com.alexandrefranca.producer.sync;

import java.util.Vector;

public class ProducerConsumerVector {

    public static void main(String[] args) {

        Vector<String> vector = new Vector<>(5);

        new Thread( new ConsumerVec(vector)).start();
        new Thread( new ConsumerVec(vector)).start();

        System.out.println(Thread.currentThread().getName() + " finished.");
    }
}

class ProducerVec implements Runnable{

    Vector vector;
    public ProducerVec(Vector v) {
        this.vector = v;
    }

    @Override
    public void run() {
        System.out.println("vector.size(): " +vector.size()+" |  vector.capacity(): "+vector.capacity());
        int index = 0;
        while(true){
            index = vector.size()+1;
            String product = "Product#"+index;
            System.out.println("Producing: "+product);
            vector.add(product);

            if (vector.size() == vector.capacity()){
                System.out.println("SLEEP: vector.size(): " +vector.size()+" |  vector.capacity(): "+vector.capacity());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

class ConsumerVec implements Runnable{
    Vector vector;
    public ConsumerVec(Vector v) {
        this.vector = v;
    }

    @Override
    public void run() {
        System.out.println("vector.size(): " +vector.size()+" |  vector.capacity(): "+vector.capacity());

        int index = 0;
        while(true){
            if (vector.size() < 1){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            index = vector.size()-1;
            String product = "Product#"+index;
            System.out.println("Consuming: "+product);
            vector.remove(index);
        }

    }
}