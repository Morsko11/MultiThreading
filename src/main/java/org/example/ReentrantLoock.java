package org.example;

import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

public class ReentrantLoock {
    public static void main(String[] args) {
        for (int i = 0; i < 6; i++) {
            Thread thread = new Thread(new Tread1(new Resurs(), new ReentrantLock()));
            thread.setName("Thread "+ i);
            thread.start();
        }
    }
}
class Resurs {
    int x = 0;

}
class Tread1 implements Runnable{
Resurs resurs ;
ReentrantLock reentrantLock;

    public Tread1(Resurs resurs, ReentrantLock reentrantLock) {
        this.resurs = resurs;
        this.reentrantLock = reentrantLock;
    }

    @Override
    public void run() {
        reentrantLock.lock();

        try{
            resurs.x=1;
            for (int i = 0; i < 5; i++) {
                System.out.println(resurs.x+" имя потока ->"+ Thread.currentThread().getName());

                resurs.x++;
                sleep(200);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            reentrantLock.unlock();
        }
    }
}
