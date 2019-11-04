/***********************************************************
 * @Description : 多线程寻找最大值
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/9/27 07:50
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter03Thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class P73MultiThreadMaxFinder {
    public static int max(int[] data) throws InterruptedException, ExecutionException {
        if (data.length == 1) {
            return data[0];
        } else if (data.length == 0) {
            throw new IllegalArgumentException();
        }

        // 将任务分解成两部分
        P72FindMaxTask task1 = new P72FindMaxTask(data, 0, data.length / 2);
        P72FindMaxTask task2 = new P72FindMaxTask(data, data.length / 2, data.length);

        // 创建两个线程的线程池
        ExecutorService service = Executors.newFixedThreadPool(2);

        Future<Integer> future1 = service.submit(task1);
        Future<Integer> future2 = service.submit(task2);
        // 线程池的shutdown()方法不会终止等待的工作线程，它只是通知线程池再没有更多的需要执行的任务了，
        // 一旦完成所有既有的任务，线程池就会自动关闭
        service.shutdown();
        return Math.max(future1.get(), future2.get());
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[] data = {2, 5, 3, 9, 8, 12, 56};
        int max = max(data);
        System.out.println(max);
    }
}
