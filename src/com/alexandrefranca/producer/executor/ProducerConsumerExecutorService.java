package com.alexandrefranca.producer.executor;

import com.alexandrefranca.util.ThreadColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

import static com.alexandrefranca.producer.lock.ProducerConsumerLock.EOF;

public class ProducerConsumerExecutorService {
    public static final String EOF = "EOF";

    public static void main(String[] args) {

        List<String> buffer = new ArrayList<String>();
        ReentrantLock bufferLock = new ReentrantLock();

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Producer producer = new Producer(buffer, ThreadColor.ANSI_BLUE, bufferLock);
        Consumer consumer1 = new Consumer(buffer, ThreadColor.ANSI_RED, bufferLock);
        Consumer consumer2=  new Consumer(buffer, ThreadColor.ANSI_GREEN, bufferLock);

        executorService.execute(producer);
        executorService.execute(consumer1);
        executorService.execute(consumer2);

        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(ThreadColor.ANSI_YELLOW+"I'm being printed from the Callable");
                return  ThreadColor.ANSI_YELLOW+"This is the callable result";
            }
        });

        try{
            System.out.println(future.get());
        } catch (ExecutionException e){
            System.out.println("Something went wrong during execution");
        } catch (InterruptedException e){
            System.out.println("Thread was interrupted");
        }

        executorService.shutdown();


    }
}

class Producer implements Runnable{

    private List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;

    public Producer(List<String> buff, String color, ReentrantLock buffLock) {
        this.buffer = buff;
        this.color = color;
        this.bufferLock = buffLock;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int num=0; num < 10; num++){
            try {
                String product = "#" + num;
                System.out.println(color + "Producer: Storing <- " + product);
                bufferLock.lock();
                try {
                    buffer.add(product);
                } finally {
                    bufferLock.unlock();
                }

                Thread.sleep(random.nextInt(1000));
            } catch(InterruptedException e){
                System.out.println("Producer was interrupted");
            }
        }

        System.out.println(color + "Producer: Adding EOF and exiting...");
        bufferLock.lock();
        buffer.add("EOF");
        bufferLock.unlock();
    }
}

class Consumer implements Runnable{
    private List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;

    public Consumer(List<String> buff, String color, ReentrantLock buffLock) {
        this.buffer = buff;
        this.color = color;
        this.bufferLock = buffLock;
    }

    @Override
    public void run() {
        int counter = 0;
        while(true){
            if(bufferLock.tryLock()) {
                try {
                    if (buffer.isEmpty()) {
                        continue;
                    }
                    //System.out.println(color + "The counter = "+ counter);
                    counter = 0;
                    if (buffer.get(0).equals(EOF)) {
                        System.out.println(color + "Consumer: Exiting");
                        break;
                    } else {
                        System.out.println(color + "Consumer: Removed -> " + buffer.remove(0));
                    }
                } finally {
                    bufferLock.unlock();
                }
            } else {
                counter++;
            }
        }
    }
}