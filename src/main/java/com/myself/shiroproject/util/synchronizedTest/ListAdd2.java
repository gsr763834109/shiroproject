package com.myself.shiroproject.util.synchronizedTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName ListAdd2
 * @Description
 * @Author vici
 * @Date 2020/7/3 13:21
 * @Version V2.0
 **/
public class ListAdd2 {

    //线程间通信
    //wait方法释放锁，notify不释放锁
    /**
     * 使用wait和notify有什么弊端么？
     * notify不释放锁，不能够做到实时。使用java。util包下的CountDownLatch
     */
    private volatile  static List list = new ArrayList();

    public void  add(){
        list.add("bjsxt");
    }

    public int size(){
        return list.size();
    }



    public static void main(String[] args) {

        final ListAdd2 list2 = new ListAdd2();
        final Object lock = new Object();

        CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //synchronized (lock) {
                        System.out.println("t1启动..");
                        for(int i = 0; i <10; i++){
                            list2.add();
                            System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素..");
                            Thread.sleep(1000);
                            if(list2.size() == 5){
                                System.out.println("已经发出通知..");
                                countDownLatch.countDown();
                                //lock.notify();
                            }
                        }
                   // }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                //synchronized (lock) {
                    System.out.println("t2启动..");
                    if(list2.size() != 5){
                        try {
                            //lock.wait();
                            countDownLatch.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("当前线程：" + Thread.currentThread().getName() + "收到通知线程停止..");
                    throw new RuntimeException();
               // }
            }
        }, "t2");
        t2.start();
        t1.start();
    }

}
