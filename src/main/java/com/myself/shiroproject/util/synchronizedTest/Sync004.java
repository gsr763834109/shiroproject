package com.myself.shiroproject.util.synchronizedTest;

/**
 * @ClassName Sync004
 * @Description
 * @Author vici
 * @Date 2020/7/2 10:26
 * @Version V2.0
 **/
public class Sync004 {
    private String username = "bjsxt";
    private String password = "123";
    public synchronized void setValue(String username,String password){
        this.username = username;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.password = password;
        System.out.println("setValue最终结果:"+this.username+"-----"+this.password);
    }

    public synchronized void  getValue(){
        System.out.println("getValue方法得到"+this.username +"======"+this.password);
    }

    public static void main(String[] args) throws InterruptedException {
        final Sync004 dr = new Sync004();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                dr.setValue("z3", "456");
            }
        });
        t1.start();
        Thread.sleep(1000);
        dr.getValue();
    }
}
