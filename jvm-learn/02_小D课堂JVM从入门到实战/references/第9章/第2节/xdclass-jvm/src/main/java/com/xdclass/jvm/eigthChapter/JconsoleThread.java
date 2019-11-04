package com.xdclass.jvm.eigthChapter;

public class JconsoleThread {

    public static void main(String[] args) throws InterruptedException {

        class Toilet { // 厕所类
            int paperCount = 0; // 纸张

            public void pee() { // 尿尿方法
                try {
                    Thread.sleep(21000);// 研究表明，动物无论大小尿尿时间都在21秒左右
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        Toilet toilet = new Toilet();

        // 两乘客线程
        Thread[] passengers = new Thread[2];
        for (int i = 0; i < passengers.length; i++) {
            passengers[i] = new Thread(new Runnable() {
                public void run() {
                    synchronized (toilet) {
                        while (toilet.paperCount < 1) {
                            try {
                                toilet.wait(); // 条件不满足，等待
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                        toilet.paperCount--; // 使用一张纸
                        toilet.pee();
                    }
                }
            },"线程"+i);
        }

        // 乘务员线程
        Thread steward = new Thread(new Runnable() {
            public void run() {
                synchronized (toilet) {
                    toilet.paperCount += 10;// 增加十张纸
                    toilet.notifyAll();// 通知所有在此对象上等待的线程
                }
            }
        });

        passengers[0].start();
        passengers[1].start();

        // 确保已经执行了 run 方法
        Thread.sleep(100000);

        // 乘务员线程启动，救星来了
        steward.start();

        // 确保已经增加纸张并已通知
        Thread.sleep(100);

    }
}
