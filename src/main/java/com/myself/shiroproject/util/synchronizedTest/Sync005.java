package com.myself.shiroproject.util.synchronizedTest;

import sun.applet.Main;

/**
 * @ClassName Sync005
 * @Description
 * @Author vici
 * @Date 2020/7/2 10:53
 * @Version V2.0
 **/
public class Sync005 {



    static class Main{
        public int num = 10;
        public synchronized void cutnumMan(){
            try {
                num--;
                System.out.println("Main====" + num);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Sub extends Main{
        public synchronized void cutnummSub(){
            try {
                while (num > 0){
                    num--;
                    System.out.println("Sub--"+num);
                    Thread.sleep(1000);
                    this.cutnumMan();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Sub sub = new Sub();
                sub.cutnummSub();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Sub sub = new Sub();
                sub.cutnummSub();
            }
        });
        thread.start();
        thread2.start();

    }
}
