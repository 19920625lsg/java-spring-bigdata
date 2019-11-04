/***********************************************************
 * @Description : 文件拷贝的四种方式
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/9/17 23:14
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter06NIO;

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
    private static final int ROUNDS = 5;

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
        String srcPath = "/Users/liangshanguang/Program/old/video/239829.mp4";
        String dstPath = "/Users/liangshanguang/Program/old/video/239829_copy.mp4";
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

/**
 * 开始文件拷贝方法的性能测试啦！
 * NoBufferStreamCopy耗时: 135
 * BufferedStreamCopy耗时: 830
 * NioBufferCopy耗时: 3910
 * NioTransferCopy耗时: 227
 */
