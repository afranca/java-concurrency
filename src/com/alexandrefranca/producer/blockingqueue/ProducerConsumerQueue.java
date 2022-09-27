package com.alexandrefranca.producer.blockingqueue;

import com.alexandrefranca.ThreadColor;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.alexandrefranca.producer.blockingqueue.ProducerConsumerQueue.EOF;

public class ProducerConsumerQueue {
    public static final String EOF = "EOF";

    public static void main(String[] args) {

        ArrayBlockingQueue<String> buffer = new ArrayBlockingQueue<String>(5);

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Producer producer = new Producer(buffer, ThreadColor.ANSI_BLUE);
        Consumer consumer1 = new Consumer(buffer, ThreadColor.ANSI_RED);
        Consumer consumer2=  new Consumer(buffer, ThreadColor.ANSI_GREEN);

        executorService.execute(producer);
        executorService.execute(consumer1);
        executorService.execute(consumer2);

        /*
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
         */

        executorService.shutdown();


    }
}

class Producer implements Runnable{

    private ArrayBlockingQueue<String> buffer;
    private String color;


    public Producer(ArrayBlockingQueue<String> buff, String color) {
        this.buffer = buff;
        this.color = color;

    }

    @Override
    public void run() {
        Random random = new Random();
        for (int num=0; num < 5; num++){
            try {
                String product = "#" + num;
                System.out.println(color + "Producer: Storing <- " + product);
                buffer.put(product);

                Thread.sleep(random.nextInt(1000));
            } catch(InterruptedException e){
                System.out.println(color + "Producer was interrupted");
            }
        }

        System.out.println(color + "Producer: Adding EOF and exiting...");
        try {
            buffer.put(EOF);
        } catch (InterruptedException e) {
            System.out.println(color + "Producer: PUT InterruptedException --> "+e);
            throw new RuntimeException(e);
        }
    }
}

class Consumer implements Runnable{
    private ArrayBlockingQueue<String> buffer;
    private String color;


    public Consumer(ArrayBlockingQueue<String> buff, String color) {
        this.buffer = buff;
        this.color = color;
    }

    @Override
    public void run() {
        while(true){
            synchronized (buffer) {
                try {
                    if (buffer.isEmpty()) {
                        continue;
                    }
                    if (buffer.peek().equals(EOF)) {
                        System.out.println(color + "Consumer: Exiting");
                        break;
                    } else {
                        System.out.println(color + "Consumer: Removed -> " + buffer.take());
                    }
                } catch (InterruptedException e) {
                    System.out.println(color + "Consumer: InterruptedException occurred");
                }
            }
        }
    }
}