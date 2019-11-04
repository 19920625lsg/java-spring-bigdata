# 第06章_NIO非阻塞模型

> NIO 即 `NonBlocking IO`

## 6.1 NIO概述

### BIO中的阻塞

+ `serverSocket.accept()`
+ `InputStream.read()`和`OutputStream.wrrite()`
+ 无法在同一个线程里处理多个Stream I/O

### NIO与BIO的不同

+ 使用Channel代替BIO中的Stream
+ 使用Selector监控多条Channel
+ 可以在一个线程里处理多个Channel I/O

## 6.2 Buffer简析

略

## 6.4 Channel简析

### 几个重要的Channel

+ FileChannel
+ ServerSocketChannel
+ SocketChannel

## 6.5~6.7 多方法实现本地文件拷贝

```java
package com.huawei.l00379880.mythread.Chapter06NIO;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/***********************************************************
 * @note      : 多方法实现本地文件拷贝
 * @author    : l00379880 梁山广
 * @version   : V1.0 at 2019/9/16 8:44
 ***********************************************************/

/**
 * 文件拷贝接口类
 */
interface FileCopy {
    /**
     * Buffer缓冲区的大小
     */
    int BUFFER_SIZE = 1024;

    /**
     * 把文件从位置src拷贝到目标位置dst
     *
     * @param src 起始位置
     * @param dst 目标位置
     */
    void copyFile(File src, File dst);

    /**
     * 所有的资源类都要实现Closeable接口
     * JDK8中的interface也可以写具体的函数了
     *
     * @param closeable 资源管理关闭接口
     */
    static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}


/**
 * 无缓冲区的文件拷贝，最传统的方法，一个字节一个字节地拷贝
 */
class NoBufferStreamCopy implements FileCopy {

    @Override
    public void copyFile(File src, File dst) {
        InputStream fin = null;
        OutputStream fout = null;
        try {
            fin = new FileInputStream(src);
            fout = new FileOutputStream(dst);
            int result;
            // 文件读完返回-1
            while ((result = fin.read()) != 1) {
                // 读多少就往外写多少
                fout.write(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 不管是正常结束还是异常退出都要释放资源
            FileCopy.close(fin);
            FileCopy.close(fout);
        }
    }

    @Override
    public String toString() {
        return "NoBufferStreamCopy";
    }
}

/**
 * 含缓冲区的文件拷贝
 */
class BufferedStreamCopy implements FileCopy {

    @Override
    public void copyFile(File src, File dst) {
        InputStream fin = null;
        OutputStream fout = null;
        try {
            // 把文件流加入缓冲区
            fin = new BufferedInputStream(new FileInputStream(src));
            fout = new BufferedOutputStream(new FileOutputStream(dst));
            // 设置缓冲区的大小
            byte[] buffer = new byte[FileCopy.BUFFER_SIZE];
            int result;
            // 文件读完返回-1,没读完返回本次读取到的字节数
            while ((result = fin.read(buffer)) != -1) {
                // 读多少就往外写多少
                fout.write(buffer, 0, result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            FileCopy.close(fin);
            FileCopy.close(fout);
        }
    }

    @Override
    public String toString() {
        return "BufferedStreamCopy";
    }
}

/**
 * 基于NIO缓存地文件拷贝
 */
class NioBufferCopy implements FileCopy {

    @Override
    public void copyFile(File src, File dst) {
        FileChannel fin = null;
        FileChannel fout = null;
        try {
            // 获取文件流管道
            fin = new FileInputStream(src).getChannel();
            fout = new FileOutputStream(dst).getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(FileCopy.BUFFER_SIZE);
            while (fin.read(buffer) != -1) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    fout.write(buffer);
                }
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            FileCopy.close(fin);
            FileCopy.close(fout);
        }
    }

    @Override
    public String toString() {
        return "NioBufferCopy";
    }
}

class NioTransferCopy implements FileCopy {

    @Override
    public void copyFile(File src, File dst) {
        FileChannel fin = null;
        FileChannel fout = null;
        try {
            // 获取文件流管道
            fin = new FileInputStream(src).getChannel();
            fout = new FileOutputStream(dst).getChannel();
            // 已经传送的字节数
            long transferred = 0L;
            // 直接在两个通道中间进行文件传输,省去了中间暂存者buffer
            long size = fin.size();
            while (transferred != size) {
                // 不断累计传送的字节数
                transferred += fin.transferTo(0, size, fout);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            FileCopy.close(fin);
            FileCopy.close(fout);
        }
    }

    @Override
    public String toString() {
        return "NioTransferCopy";
    }
}

public class FileCopyDemo {

    /**
     * 进行50轮测试
     */
    private static final int ROUNDS = 10;

    /**
     * 每种方法执行5次进行性能测试
     *
     * @param copyTest 文件拷贝的接口
     * @param src      源文件
     * @param dst      目标文件
     */
    private static void benchmark(FileCopy copyTest, File src, File dst) {
        long elapsed = 0L;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < ROUNDS; i++) {
            copyTest.copyFile(src, dst);
            // 删除拷贝完的目标文件
            elapsed += System.currentTimeMillis() - startTime;
            dst.delete();
        }
        // 指定实现类的耗时
        System.out.println(copyTest + "耗时: " + elapsed);
    }

    public static void main(String[] args) {
        String srcPath = "E:/图片/合影.jpg";
        String dstPath = "E:/图片/合影_copy.jpg";
        File src = new File(srcPath);
        File dst = new File(dstPath);
        System.out.println("开始文件拷贝方法的性能测试啦！");
        FileCopy noBufferStreamCopy = new NoBufferStreamCopy();
        benchmark(noBufferStreamCopy, src, dst);
        FileCopy bufferedStreamCopy = new BufferedStreamCopy();
        benchmark(bufferedStreamCopy, src, dst);
        FileCopy nioBufferCopy = new NioBufferCopy();
        benchmark(nioBufferCopy, src, dst);
        FileCopy nioTransferCopy = new NioTransferCopy();
        benchmark(nioTransferCopy, src, dst);
    }
}
```

输出如下：

```text
开始文件拷贝方法的性能测试啦！
NoBufferStreamCopy耗时: 175
BufferedStreamCopy耗时: 1328
NioBufferCopy耗时: 6462
NioTransferCopy耗时: 518
```

结论：在中小文件时，NIO的优势较大，在大文件时各种文件拷贝方式差不多.(JDK版本不断更新，各种文件操作的底层实现基本都是基于同一种最搞高效的方式了)

## 6.8 Selector与Channel

### Selector与Channel的关系

![Selector与Channel的关系](https://img.mukewang.com/szimg/5d808f3c000154c512800720.jpg)

### Channel的状态变化

![Channel的状态变化](https://img.mukewang.com/szimg/5d8091590001bafe12800720.jpg)

### 在Selector上注册Channel

![Selector上注册Channel](https://img.mukewang.com/szimg/5d80927a0001bafd12800720.jpg)
