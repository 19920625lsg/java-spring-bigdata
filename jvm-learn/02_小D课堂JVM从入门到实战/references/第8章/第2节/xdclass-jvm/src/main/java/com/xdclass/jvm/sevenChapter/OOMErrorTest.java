package com.xdclass.jvm.sevenChapter;

public class OOMErrorTest {

    public static void main(String[] args) {
        String name = "xdclass";
        for (int i=0;i<100000000;i++) {
            name += name;
        }
        System.out.println(name);
    }
}
