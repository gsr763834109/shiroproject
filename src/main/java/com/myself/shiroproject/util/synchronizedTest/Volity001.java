package com.myself.shiroproject.util.synchronizedTest;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName Volity001
 * @Description
 * @Author vici
 * @Date 2020/7/2 16:48
 * @Version V2.0
 **/
public class Volity001 extends Thread{
    //早先解决公用变量的解决方法:变量上加锁，1线程访问变量操作时，没其他线程不能访问变量，效率低
    /**
     * volity的使用
     *
     *
     */

    //private static volatile int count;
    private static AtomicInteger count = new AtomicInteger(0);//Actomic原子类
    private static synchronized  void addcount(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i = 0;i<1000;i++){
            //count++;
            count.incrementAndGet();
        }
        System.out.println(currentThread().getName()+"-----"+count);
    }

    public void  run(){
        addcount();
    }


    public static void main(String[] args) {
        Volity001[] volity001s = new Volity001[100];
        Thread[] threads = new Thread[100];
        for (int i = 0;i<10;i++){
            volity001s[i] = new Volity001();
            threads[i] = new Thread(volity001s[i],"t"+i);
        }

        for(int i = 0;i<10;i++){
            threads[i].start();
        }
    }

}
