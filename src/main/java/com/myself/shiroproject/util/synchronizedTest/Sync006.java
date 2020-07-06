package com.myself.shiroproject.util.synchronizedTest;

import sun.rmi.runtime.Log;

/**
 * @ClassName Sync006
 * @Description
 * @Author vici
 * @Date 2020/7/2 13:17
 * @Version V2.0
 **/
public class Sync006 {
    private int  num = 0;

    //
    public void  operation(){
      while(true){
          try {
              num++;
              Thread.sleep(100);
              System.out.println(Thread.currentThread().getName() + " , num = " + num);
              if(num == 10){
                  Integer.parseInt("a");
                  /*throw new RuntimeException();*/
              }
          } catch (InterruptedException e) {
              e.printStackTrace();
              continue;
          }
      }
    }


    public static void main(String[] args) {
        final Sync006 se = new Sync006();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                se.operation();
            }
        },"t1");
        t1.start();
    }


}
