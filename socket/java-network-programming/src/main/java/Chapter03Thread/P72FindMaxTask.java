/***********************************************************
 * @Description : 
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/9/27 07:47
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter03Thread;

import java.util.concurrent.Callable;

public class P72FindMaxTask implements Callable<Integer> {
    private int[] data;
    private int start;
    private int end;

    public P72FindMaxTask(int[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() {
        int max = Integer.MIN_VALUE;
        for (int i = start; i < end; i++) {
            if (data[i] > max) {
                max = data[i];
            }
        }
        return max;
    }
}
