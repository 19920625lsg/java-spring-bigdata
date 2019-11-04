public class CPULockTest {

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread(()->{
            synchronized (lock1){
                try {
                    System.out.println(Thread.currentThread().getName()+"得到lock1");
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2){
                    System.out.println(Thread.currentThread().getName()+"得到lock2");
                }
            }
        },"线程1").start();

        new Thread(()->{
            synchronized (lock2){
                try {
                    System.out.println(Thread.currentThread().getName()+"得到lock2");
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1){
                    System.out.println(Thread.currentThread().getName()+"得到lock1");
                }
            }
        },"线程2").start();
    }
}