package com.jason.lock;

public class Deadlock {
    // 死锁案例
    public static void main(String[] args) {
        DeadLockObject deadLockObject =new DeadLockObject(new Object(),new Object());
        Thread1 thread1=new Thread1(deadLockObject);
        Thread2 thread2=new Thread2(deadLockObject);
        thread1.start();
        thread2.start();
    }


}
class DeadLockObject {
    Object object1;
    Object object2;

    public DeadLockObject(Object object1, Object object2) {
        this.object1 = object1;
        this.object2 = object2;
    }

}
class  Thread1 extends  Thread{
    private DeadLockObject deadLockObject;
    public Thread1(DeadLockObject object) {
        this.deadLockObject=object;
    }

    @Override
    public void run() {
        synchronized (deadLockObject.object1){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (deadLockObject.object2){
                System.out.println("我持有了当前锁---object1》》》》object2");
            }
        }
    }
}

class  Thread2 extends  Thread{
    private DeadLockObject deadLockObject;
    public Thread2(DeadLockObject object) {
        this.deadLockObject=object;
    }

    @Override
    public void run() {
        synchronized (deadLockObject.object2){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (deadLockObject.object1){
                System.out.println("我持有了当前锁---object2》》》》object1");
            }
        }
    }
}