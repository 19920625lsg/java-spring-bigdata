package com.xdclass.jvm.eigthChapter;

import java.util.ArrayList;
import java.util.List;

public class JconsoleMemory {

    public byte[] b1 = new byte[1024*512];

    public static void main(String[] args) throws InterruptedException {
        System.out.println("main thread start");
        Thread.sleep(10000);
        allocate(10000);
    }

    private static void allocate(int n) {
        List<JconsoleMemory> jconsoleMemoryList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            try {
               Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            jconsoleMemoryList.add(new JconsoleMemory());
        }
    }
}
