package com.myself.shiroproject.util.synchronizedTest;

/**
 * @ClassName Sync007
 * @Description
 * @Author vici
 * @Date 2020/7/2 14:26
 * @Version V2.0
 **/
public class Sync007 {
    /**
     * 锁对象改变问题
     */
    private String lock = "lock";

    private void method(){
        synchronized (lock) {
            try {
                System.out.println("当前线程 : "  + Thread.currentThread().getName() + "开始");
                //lock = "change lock";
                Thread.sleep(2000);
                System.out.println("当前线程 : "  + Thread.currentThread().getName() + "结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final Sync007 changeLock = new Sync007();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                changeLock.method();
            }
        },"t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                changeLock.method();
            }
        },"t2");
        t1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
