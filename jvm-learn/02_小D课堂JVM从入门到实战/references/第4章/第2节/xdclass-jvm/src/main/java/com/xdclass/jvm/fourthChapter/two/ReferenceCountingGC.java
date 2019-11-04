package com.xdclass.jvm.fourthChapter.two;

public class ReferenceCountingGC {

    public Object instance = null;
    private static final int num = 1024*1024;
    private byte[] bigSize = new byte[2*num];
    public static void main(String[] args) {
        ReferenceCountingGC objA= new ReferenceCountingGC();
        ReferenceCountingGC objB= new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;
        objA = null;
        objB = null;
        System.gc();
    }
}
