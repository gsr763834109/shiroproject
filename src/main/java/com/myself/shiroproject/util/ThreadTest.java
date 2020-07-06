package com.myself.shiroproject.util;

/**
 * @ClassName ThreadTest
 * @Description
 * @Author vici
 * @Date 2020/6/29 10:39
 * @Version V2.0
 **/
public class ThreadTest extends Thread{
    /**
     * 1、synchronized 获取的是对象锁，所以访问不同的两个方法时互不干扰的，使用static修饰方法和变量，表示锁定.class类,类一级别的锁（独占calss类）
     * 2、
     */
    private  int count = 6;

    
    //案例1，
    public  synchronized  void printNum(String tag){

        if(tag.equals("a")){
            count = 100;
            System.out.println("tag a, set count over!");
        }else{
            count = 200;
            System.out.println("tag b, set num over");
        }
        System.out.println("tag " + tag +",count = " + count);
    }


    public  void printNum2(String tag){
        System.out.println("tag" + tag);
    }

    public static void main(String[] args) {
        //两个不同的对象
        ThreadTest threadTest = new ThreadTest();
        ThreadTest threadTest2 = new ThreadTest();
        /*Thread t1 = new Thread(threadTest, "t1");
        Thread t2 = new Thread(threadTest, "t2");
        Thread t3 = new Thread(threadTest, "t3");
        Thread t4 = new Thread(threadTest, "t4");
        Thread t5 = new Thread(threadTest, "t5");
        Thread t6 = new Thread(threadTest, "t6");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();*/
        //案例1
       /* Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                threadTest.printNum("a");
            }
        });

        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                threadTest2.printNum("b");
            }
        });
        a.start();
        b.start();*/

    }

}
