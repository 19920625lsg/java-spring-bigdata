package com.xdclass.jvm.sevenChapter;

public class JstackTest {

    static class Worker implements Runnable{

        @Override
        public void run() {
            while (true){
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new Worker());
        thread.start();
    }
}
